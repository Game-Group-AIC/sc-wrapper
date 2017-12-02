package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.facades.IUpdateManager;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WChokePoint;
import java.util.stream.Stream;

//TODO implement
public class UpdatableChokePoint extends ChokePoint implements
    IUpdatableContainer<WChokePoint, ChokePoint> {

  private final transient WChokePoint wrapped;

  public UpdatableChokePoint(BWCounter bwCounter, WChokePoint wrapped) {
    super(bwCounter);
    this.wrapped = wrapped;
  }

  @Override
  public WChokePoint getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public Stream<? extends AContainer> update(UpdateManager internalUpdaterFacade) {
    return Stream.empty();
  }

  @Override
  public ChokePoint getContainer() {
    return this;
  }

  @Override
  public ChokePoint getCopyOfContainer(BWReplayCounter bwReplayCounter) {
    this.bwCounter = bwReplayCounter;
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
