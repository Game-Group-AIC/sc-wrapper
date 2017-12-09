package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.UpgradeType;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WUpgradeType;
import java.util.stream.Stream;

//TODO implement
public class UpdatableUpgradeType extends UpgradeType implements
    IUpdatableContainer<WUpgradeType, UpgradeType> {

  private final transient WUpgradeType wrapped;

  public UpdatableUpgradeType(BWReplayCounter bwCounter, WUpgradeType wrapped) {
    super(bwCounter, wrapped.getType());
    this.wrapped = wrapped;
  }

  @Override
  public WUpgradeType getWrappedSCInstance() {
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
  public UpgradeType getContainer() {
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
