package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Region;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WRegion;
import java.util.stream.Stream;

//TODO implement
public class UpdatableRegion extends Region implements
    IUpdatableContainer<WRegion, Region> {

  private final transient WRegion wrapped;

  public UpdatableRegion(BWReplayCounter bwCounter, WRegion wrapped) {
    super(bwCounter, wrapped.getX(), wrapped.getY());
    this.wrapped = wrapped;
  }

  @Override
  public WRegion getWrappedSCInstance() {
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
  public Region getContainer() {
    return this;
  }

  @Override
  public Stream<? extends AContainer> getReferencedContainers(int currentFrame) {
    return Stream.empty();
  }

  @Override
  public void update(UpdateManager updateManager, UpdateStrategy updateStrategy) {
    updateManager.update(this, updateStrategy);
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, int depth, int currentFrame) {
    return updateStrategy.shouldBeUpdated(this, deltaOfUpdate(currentFrame), depth);
  }

}
