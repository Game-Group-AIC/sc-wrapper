package gg.fel.cvut.cz.facades.managers;


import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.facades.IContainerRegister;
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
public class ContainerRegister<T extends Wrapper<?>, L extends AContainer, U extends IUpdatableContainer<T, L>> implements
    IContainerRegister<T, L, U> {

  private final WrapInstanceToUpdatableContainerStrategy<T, L, U> wrapInstanceToUpdatableContainerStrategy;
  private final ReentrantReadWriteLock lockForMaps = new ReentrantReadWriteLock(true);

  //to store data
  private final Map<T, U> register = new HashMap<>();

  @Override
  public Optional<U> getWrappedInstance(T wrappedInstance) {

    //try to find container
    try {
      lockForMaps.readLock().lock();
      U container = register.get(wrappedInstance);
      if (container != null) {
        return Optional.of(container);
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
      return Optional.of(updatableContainer);
    } finally {
      lockForMaps.writeLock().unlock();
    }
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
