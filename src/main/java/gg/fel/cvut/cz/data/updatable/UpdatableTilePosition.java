package gg.fel.cvut.cz.data.updatable;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdatableTilePosition extends TilePosition implements
    IUpdatableContainer<WTilePosition, TilePosition> {

  private transient final WTilePosition wrapped;
  private static transient final int[][] neighboursDiffs = {{0, 1}, {1, 1}, {1, 0}, {-1, 0},
      {-1, -1},
      {0, -1}, {1, -1}, {-1, 1}};

  public UpdatableTilePosition(BWReplayCounter bwCounter, WTilePosition wrapped) {
    super(bwCounter, wrapped.getX(), wrapped.getY());
    this.wrapped = wrapped;
  }

  @Override
  public WTilePosition getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public void update(UpdateManager internalUpdaterFacade, int currentFrame) {
    try {
      lock.writeLock().lock();

      //updates
      if (groundHeight.propertyHasNotBeenAdded() && internalUpdaterFacade.getGame()
          .isPresent()) {
        groundHeight.addProperty(
            internalUpdaterFacade.getGame().get().getWrappedSCInstance().getScInstance()
                .getGroundHeight(wrapped.getScInstance()), 0);
      }
      if (position.propertyHasNotBeenAdded()) {
        Optional<Position> position = internalUpdaterFacade
            .getDataContainer(WPosition.getOrCreateWrapper(wrapped.getScInstance().toPosition()));
        position.ifPresent(o -> this.position.addProperty(o, 0));
      }
      if (neighbours.propertyHasNotBeenAdded()) {
        neighbours.addProperty(ImmutableSet.copyOf(
            Stream.of(neighboursDiffs)
                .map(ints -> new bwapi.TilePosition(wrapped.getX() + ints[0],
                    wrapped.getY() + ints[1]))
                .filter(bwapi.TilePosition::isValid)
                .map(WTilePosition::getOrCreateWrapper)
                .map(internalUpdaterFacade::getDataContainer)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(tilePosition -> !tilePosition.equals(this))
                .collect(Collectors.toSet())), 0);
      }

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
  public TilePosition getContainer() {
    return this;
  }

  @Override
  public Stream<? extends AContainer> getReferencedContainers(int currentFrame) {
    return Stream.of(neighbours.getValueInFrame(currentFrame),
        position.getValueInFrame(currentFrame).map(ImmutableSet::of)).filter(Optional::isPresent)
        .map(Optional::get)
        .flatMap(Collection::stream);
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, int depth, int currentFrame) {
    return updateStrategy.shouldBeUpdated(this, deltaOfUpdate(currentFrame), depth);
  }
}
