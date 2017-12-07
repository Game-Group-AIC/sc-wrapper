package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class TilePosition extends AContainer implements ITilePosition, Serializable {

  protected final StaticPropertyRegister<Integer, Property<Integer>> groundHeight = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> unitsOnTile = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  protected final StaticPropertyRegister<Position, Property<Position>> position = new StaticPropertyRegister<Position, Property<Position>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<TilePosition>, Property<ImmutableSet<TilePosition>>> neighbours = new StaticPropertyRegister<ImmutableSet<TilePosition>, Property<ImmutableSet<TilePosition>>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> x = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> y = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);

  //TODO from json + creator
  protected Set<StaticPropertyRegister<?, ?>> toHash = new HashSet<>();

  public TilePosition(BWReplayCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  protected Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode() {
    return toHash;
  }

  @Override
  public Optional<Integer> getGroundHeight() {
    return Optional.empty();
  }

  @Override
  public Optional<Stream<IUnit>> getUnitsOnTile() {
    return getPropertyOnTimeLineStrategyOnSet(unitsOnTile)
        .map(set -> set.stream().map(o -> o));
  }

  @Override
  public Optional<IPosition> getPosition() {
    return Optional.empty();
  }

  @Override
  public Optional<Stream<ITilePosition>> getNeighbours() {
    return getPropertyOnTimeLineStrategyOnSet(neighbours)
        .map(set -> set.stream().map(o -> o));
  }
}
