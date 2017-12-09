package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WPlayer;
import java.util.stream.Stream;

//TODO implement
public class UpdatablePlayer extends Player implements
    IUpdatableContainer<WPlayer, Player> {

  private final transient WPlayer wrapped;

  public UpdatablePlayer(BWReplayCounter bwCounter, WPlayer wrapped) {
    super(bwCounter, wrapped.getId());
    this.wrapped = wrapped;
  }

  @Override
  public WPlayer getWrappedSCInstance() {
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
  public Player getContainer() {
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
    return updateStrategy
        .shouldBeUpdated(this, deltaOfUpdate(currentFrame), depth);
  }

}
