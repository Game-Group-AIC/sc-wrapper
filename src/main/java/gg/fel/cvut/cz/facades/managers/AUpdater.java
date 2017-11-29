package gg.fel.cvut.cz.facades.managers;


import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.facades.IUpdater;
import gg.fel.cvut.cz.wrappers.Wrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

/**
 * Template for SC:BW type updater
 */
@AllArgsConstructor
public class AUpdater<T extends Wrapper<?>, L extends AContainer, U extends IUpdatableContainer<T, L>> implements
    IUpdater<T, L> {

  private final WrapInstanceToUpdatableContainerStrategy<T, L, U> wrapInstanceToUpdatableContainerStrategy;
  private final ReentrantReadWriteLock lockForMaps = new ReentrantReadWriteLock(true);
  private final UpdaterFacade updaterFacade;

  //to store data
  private final Map<T, U> register = new HashMap<>();

  //register to keep track of last updates
  private final Map<U, Integer> updates = new HashMap<>();

  @Override
  public Optional<L> getWrappedInstance(T wrappedInstance) {

    //try to find container
    try {
      lockForMaps.readLock().lock();
      U container = register.get(wrappedInstance);
      if (container != null) {
        return Optional.ofNullable(container.getContainer());
      }
    } finally {
      lockForMaps.readLock().unlock();
    }

    //container does not exists, it will be created
    try {
      lockForMaps.writeLock().lock();
      U updatableContainer = wrapInstanceToUpdatableContainerStrategy
          .wrapInstanceToUpdatableContainer(wrappedInstance);
      register.put(wrappedInstance, updatableContainer);
      return Optional.of(updatableContainer.getContainer());
    } finally {
      lockForMaps.writeLock().unlock();
    }
  }

  @Override
  public Stream<? extends AContainer> update(L containerToUpdate, int currentFrame) {
    U toUpdate;
    try {
      lockForMaps.writeLock().lock();
      if (containerToUpdate instanceof IUpdatableContainer) {
        toUpdate = register.get(((IUpdatableContainer) containerToUpdate).getWrappedSCInstance());
        if (toUpdate != null && (!updates.containsKey(toUpdate)
            || updates.get(toUpdate) < currentFrame)) {
          Stream<? extends AContainer> stream = toUpdate.update(updaterFacade);
          updates.put(toUpdate, currentFrame);
          return stream;
        }
      }
    } finally {
      lockForMaps.writeLock().unlock();
    }
    return Stream.empty();
  }

  @Override
  public Optional<T> getBWInstance(L container) {
    try {
      lockForMaps.readLock().lock();
      if (container instanceof IUpdatableContainer) {
        return Optional
            .ofNullable(register.get(((IUpdatableContainer) container).getWrappedSCInstance()))
            .map(IUpdatableContainer::getWrappedSCInstance);
      }
      return Optional.empty();
    } finally {
      lockForMaps.readLock().unlock();
    }
  }

  @Override
  public int getDeltaUpdate(L containerToUpdate, int currentFrame) {
    if (getBWInstance(containerToUpdate).isPresent()) {
      try {
        lockForMaps.readLock().lock();
        Optional<Integer> lastUpdate = Optional.ofNullable(updates.get(containerToUpdate));
        return lastUpdate.map(integer -> integer >= currentFrame ? 0 : currentFrame - integer)
            .orElse(Integer.MAX_VALUE);
      } finally {
        lockForMaps.readLock().unlock();
      }
    }
    return 0;
  }

  @Override
  public Stream<L> getAllContainers() {
    try {
      lockForMaps.readLock().lock();
      return register.values().stream().map(IUpdatableContainer::getContainer);
    } finally {
      lockForMaps.readLock().unlock();
    }
  }

  /**
   * Template for strategy to wrap instance to updatable container
   */
  protected interface WrapInstanceToUpdatableContainerStrategy<T extends Wrapper<?>, L extends AContainer, U extends IUpdatableContainer<T, L>> {

    U wrapInstanceToUpdatableContainer(T instance);
  }
}
