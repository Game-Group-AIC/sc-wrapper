package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IChokePoint;
import gg.fel.cvut.cz.api.IGame;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.GameTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

//TODO implement
public class Game extends AContainer implements IGame, Serializable {

  protected final StaticPropertyRegister<ImmutableSet<Player>, Property<ImmutableSet<Player>>> players = new StaticPropertyRegister<ImmutableSet<Player>, Property<ImmutableSet<Player>>>(
      Property::new);
  protected final StaticPropertyRegister<GameTypeEnum, Property<GameTypeEnum>> gameType = new StaticPropertyRegister<GameTypeEnum, Property<GameTypeEnum>>(
      Property::new);
  protected final DynamicPropertyRegister<Integer, Property<Integer>> frameCount = new DynamicPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final DynamicPropertyRegister<Integer, Property<Integer>> FPS = new DynamicPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final DynamicPropertyRegister<Double, Property<Double>> averageFPS = new DynamicPropertyRegister<Double, Property<Double>>(
      Property::new);
  protected final DynamicPropertyRegister<Integer, Property<Integer>> elapsedTime = new DynamicPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<Region>, Property<ImmutableSet<Region>>> regions = new StaticPropertyRegister<ImmutableSet<Region>, Property<ImmutableSet<Region>>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<ChokePoint>, Property<ImmutableSet<ChokePoint>>> chokePoints = new StaticPropertyRegister<ImmutableSet<ChokePoint>, Property<ImmutableSet<ChokePoint>>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<BaseLocation>, Property<ImmutableSet<BaseLocation>>> baseLocations = new StaticPropertyRegister<ImmutableSet<BaseLocation>, Property<ImmutableSet<BaseLocation>>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<BaseLocation>, Property<ImmutableSet<BaseLocation>>> startLocations = new StaticPropertyRegister<ImmutableSet<BaseLocation>, Property<ImmutableSet<BaseLocation>>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> mapWidth = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> mapHeight = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<String, Property<String>> mapName = new StaticPropertyRegister<String, Property<String>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<TilePosition>, Property<ImmutableSet<TilePosition>>> grid = new StaticPropertyRegister<ImmutableSet<TilePosition>, Property<ImmutableSet<TilePosition>>>(
      Property::new);

  public Game(BWReplayCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public boolean equals(Object o) {
    return false;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public Optional<Stream<IPlayer>> getPlayers() {
    return getPropertyOnTimeLineStrategyOnSet(players)
        .map(set -> set.stream().map(o -> o));
  }

  @Override
  public Optional<GameTypeEnum> getGameType() {
    return getPropertyOnTimeLineStrategy(gameType);
  }

  @Override
  public Optional<Integer> getFrameCount() {
    return getPropertyOnTimeLineStrategy(frameCount);
  }

  @Override
  public Optional<Integer> getFPS() {
    return getPropertyOnTimeLineStrategy(FPS);
  }

  @Override
  public Optional<Double> getAverageFPS() {
    return getPropertyOnTimeLineStrategy(averageFPS);
  }

  @Override
  public Optional<Integer> elapsedTime() {
    return getPropertyOnTimeLineStrategy(elapsedTime);
  }

  @Override
  public Optional<Stream<IRegion>> getRegions() {
    return getPropertyOnTimeLineStrategyOnSet(regions)
        .map(set -> set.stream().map(o -> o));
  }

  @Override
  public Optional<Stream<IChokePoint>> getChokePoints() {
    return getPropertyOnTimeLineStrategyOnSet(chokePoints)
        .map(set -> set.stream().map(o -> o));
  }

  @Override
  public Optional<Stream<IBaseLocation>> getBaseLocations() {
    return getPropertyOnTimeLineStrategyOnSet(baseLocations)
        .map(set -> set.stream().map(o -> o));
  }

  @Override
  public Optional<Stream<IBaseLocation>> getStartLocations() {
    return getPropertyOnTimeLineStrategyOnSet(startLocations)
        .map(set -> set.stream().map(o -> o));
  }

  @Override
  public Optional<Integer> mapWidth() {
    return getPropertyOnTimeLineStrategy(mapWidth);
  }

  @Override
  public Optional<Integer> mapHeight() {
    return getPropertyOnTimeLineStrategy(mapHeight);
  }

  @Override
  public Optional<String> mapName() {
    return getPropertyOnTimeLineStrategy(mapName);
  }

  @Override
  public Optional<Stream<ITilePosition>> getGrid() {
    return getPropertyOnTimeLineStrategyOnSet(grid)
        .map(set -> set.stream().map(o -> o));
  }

}
