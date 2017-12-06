package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.UpgradeType;
import gg.fel.cvut.cz.facades.IUpdateManager;
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
  public Stream<? extends AContainer> update(UpdateManager internalUpdaterFacade) {
    return Stream.empty();
  }

  @Override
  public UpgradeType getContainer() {
    return this;
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, IUpdateManager updaterFacade,
      int depth) {
    return updateStrategy.shouldBeUpdated(this, updaterFacade.getDeltaUpdate(this), depth);
  }

  @Override
  public void update(UpdateStrategy updateStrategy, IUpdateManager updaterFacade, int depth,
      int currentFrame) {
    updaterFacade.update(this, updateStrategy, depth, currentFrame);
  }
}
