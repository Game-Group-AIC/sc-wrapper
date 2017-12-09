package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.WalkPosition;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WWalkPosition;
import java.util.stream.Stream;

public class UpdatableWalkPosition extends WalkPosition implements
    IUpdatableContainer<WWalkPosition, WalkPosition> {

  private transient final WWalkPosition wrapped;

  public UpdatableWalkPosition(BWReplayCounter bwCounter, WWalkPosition wrapped) {
    super(bwCounter, wrapped.getX(), wrapped.getY());
    this.wrapped = wrapped;
  }

  @Override
  public WWalkPosition getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public WalkPosition getContainer() {
    return this;
  }

  @Override
  public Stream<? extends AContainer> getReferencedContainers(int currentFrame) {
    return null;
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, int depth, int currentFrame) {
    return false;
  }

  @Override
  public void update(UpdateManager updateManager, int currentFrame) {

  }

  @Override
  public void update(UpdateManager updateManager, UpdateStrategy updateStrategy) {

  }
}
