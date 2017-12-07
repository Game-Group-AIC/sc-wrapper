package gg.fel.cvut.cz.data.updatable;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.facades.IUpdateManager;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import gg.fel.cvut.cz.wrappers.WUnit;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdatableTilePosition extends TilePosition implements
    IUpdatableContainer<WTilePosition, TilePosition> {

  private transient final WTilePosition wrapped;
  private static transient final int[][] neighboursDiffs = {{0, 1}, {1, 1}, {1, 0}, {-1, 0}, {-1, -1},
      {0, -1}, {1, -1}, {-1, 1}};

  public UpdatableTilePosition(BWReplayCounter bwCounter, WTilePosition wrapped) {
    super(bwCounter);
    this.wrapped = wrapped;
    x.addProperty(wrapped.getX(), 0);
    y.addProperty(wrapped.getY(), 0);
    toHash = ImmutableSet.of(x, y);
  }

  @Override
  public WTilePosition getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public Stream<? extends AContainer> update(UpdateManager internalUpdaterFacade) {
    try {
      lock.writeLock().lock();

      int currentFrame = bwCounter.getCurrentFrame();
      //updates
      if (groundHeight.propertyHasNotBeenAdded() && internalUpdaterFacade.getWrappedGame()
          .isPresent()) {
        groundHeight.addProperty(internalUpdaterFacade.getWrappedGame().get().getScInstance()
            .getGroundHeight(wrapped.getScInstance()), 0);
      }
      if (internalUpdaterFacade.getWrappedGame()
          .isPresent()) {
        unitsOnTile.addProperty(
            ImmutableSet.copyOf(internalUpdaterFacade.getWrappedGame().get().getScInstance()
                .getUnitsOnTile(wrapped.getScInstance())
                .stream()
                .map(WUnit::getOrCreateWrapper)
                .map(internalUpdaterFacade::getDataContainer)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet())), currentFrame);
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
                .collect(Collectors.toSet())), 0);
      }
      //updated in frame
      updatedInFrame = currentFrame;

      //collect containers
      return Stream.of(unitsOnTile.getValueInFrame(currentFrame),
          neighbours.getValueInFrame(currentFrame),
          position.getValueInFrame(currentFrame).map(ImmutableSet::of)).filter(Optional::isPresent)
          .map(Optional::get)
          .flatMap(Collection::stream);
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public TilePosition getContainer() {
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
