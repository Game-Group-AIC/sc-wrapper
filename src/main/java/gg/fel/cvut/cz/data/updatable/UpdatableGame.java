package gg.fel.cvut.cz.data.updatable;

import bwta.BWTA;
import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Game;
import gg.fel.cvut.cz.enums.GameTypeEnum;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.WChokePoint;
import gg.fel.cvut.cz.wrappers.WGame;
import gg.fel.cvut.cz.wrappers.WPlayer;
import gg.fel.cvut.cz.wrappers.WRegion;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//TODO implement creator
public class UpdatableGame extends Game implements
    IUpdatableContainer<WGame, Game> {

  private final transient WGame wrapped;

  public UpdatableGame(BWReplayCounter bwCounter, WGame wrapped) {
    super(bwCounter);
    this.wrapped = wrapped;
  }

  @Override
  public WGame getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public void update(UpdateManager internalUpdaterFacade, int currentFrame) {
    try {
      lock.writeLock().lock();

      //updates
      if (players.propertyHasNotBeenAdded()) {
        players.addProperty(ImmutableSet.copyOf(wrapped.getScInstance().getPlayers().stream()
            .map(WPlayer::getOrCreateWrapper)
            .map(internalUpdaterFacade::getDataContainer)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet())), 0);
      }
      if (gameType.propertyHasNotBeenAdded()) {
        this.gameType
            .addProperty(GameTypeEnum.Unknown.getOurType(wrapped.getScInstance().getGameType()), 0);
      }
      frameCount.addProperty(wrapped.getScInstance().getFrameCount(), currentFrame);
      FPS.addProperty(wrapped.getScInstance().getFPS(), currentFrame);
      averageFPS.addProperty(wrapped.getScInstance().getAverageFPS(), currentFrame);
      elapsedTime.addProperty(wrapped.getScInstance().elapsedTime(), currentFrame);
      if (regions.propertyHasNotBeenAdded()) {
        regions.addProperty(ImmutableSet.copyOf(BWTA.getRegions().stream()
            .map(WRegion::getOrCreateWrapper)
            .map(internalUpdaterFacade::getDataContainer)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet())), 0);
      }
      if (chokePoints.propertyHasNotBeenAdded()) {
        chokePoints.addProperty(ImmutableSet.copyOf(BWTA.getChokepoints().stream()
            .map(WChokePoint::getOrCreateWrapper)
            .map(internalUpdaterFacade::getDataContainer)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet())), 0);
      }
      if (baseLocations.propertyHasNotBeenAdded()) {
        baseLocations.addProperty(ImmutableSet.copyOf(BWTA.getBaseLocations().stream()
            .map(WBaseLocation::getOrCreateWrapper)
            .map(internalUpdaterFacade::getDataContainer)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet())), 0);
      }
      if (startLocations.propertyHasNotBeenAdded()) {
        startLocations.addProperty(ImmutableSet.copyOf(BWTA.getStartLocations().stream()
            .map(WBaseLocation::getOrCreateWrapper)
            .map(internalUpdaterFacade::getDataContainer)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet())), 0);
      }
      if (mapWidth.propertyHasNotBeenAdded()) {
        mapWidth.addProperty(wrapped.getScInstance().mapWidth(), 0);
      }
      if (mapHeight.propertyHasNotBeenAdded()) {
        mapHeight.addProperty(wrapped.getScInstance().mapHeight(), 0);
      }
      if (mapName.propertyHasNotBeenAdded()) {
        mapName.addProperty(wrapped.getScInstance().mapName(), 0);
      }
      if (grid.propertyHasNotBeenAdded()) {
        grid.addProperty(ImmutableSet.copyOf(
            IntStream.range(0, wrapped.getScInstance().mapWidth()).boxed()
                .flatMap(i -> IntStream.range(0, wrapped.getScInstance().mapHeight())
                    .boxed().map(j -> new bwapi.TilePosition(i, j)))
                .filter(bwapi.TilePosition::isValid)
                .map(WTilePosition::getOrCreateWrapper)
                .map(internalUpdaterFacade::getDataContainer)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet())), 0);
      }
      //updated in frame
      updatedInFrame = currentFrame;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public UpdatableGame getContainer() {
    return this;
  }

  @Override
  public Stream<? extends AContainer> getReferencedContainers(int currentFrame) {
    return Stream.of(players.getValueInFrame(currentFrame),
        regions.getValueInFrame(currentFrame),
        chokePoints.getValueInFrame(currentFrame),
        baseLocations.getValueInFrame(currentFrame),
        startLocations.getValueInFrame(currentFrame),
        grid.getValueInFrame(currentFrame))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .flatMap(Collection::stream);
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
