package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Unit;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WUnit;
import java.util.stream.Stream;

//TODO implement
public class UpdatableUnit extends Unit implements IUpdatableContainer<WUnit, Unit> {

  private final transient WUnit wrapped;

  public UpdatableUnit(BWReplayCounter bwCounter, WUnit wrapped) {
    super(bwCounter, wrapped.getId());
    this.wrapped = wrapped;
  }

  @Override
  public WUnit getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public void update(UpdateManager internalUpdaterFacade, int currentFrame) {
    try {
      lock.writeLock().lock();

      //updated in frame
      updatedInFrame = currentFrame;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public void update(UpdateManager updateManager, UpdateStrategy updateStrategy) {
    updateManager.update(this, updateStrategy);
  }

  @Override
  public Unit getContainer() {
    return this;
  }

  @Override
  public Stream<? extends AContainer> getReferencedContainers(int currentFrame) {
    return Stream.empty();
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, int depth, int currentFrame) {
    return updateStrategy.shouldBeUpdated(this, deltaOfUpdate(currentFrame), depth);
  }

}
