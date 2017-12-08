package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class TilePosition extends AContainer implements ITilePosition, Serializable {

  protected final DynamicPropertyRegister<ImmutableSet<IUnit>, Property<ImmutableSet<IUnit>>> units = new DynamicPropertyRegister<ImmutableSet<IUnit>, Property<ImmutableSet<IUnit>>>(
      Property::new);
  protected final StaticPropertyRegister<IPosition, Property<IPosition>> position = new StaticPropertyRegister<IPosition, Property<IPosition>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> groundHeight = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<ITilePosition>, Property<ImmutableSet<ITilePosition>>> neighbours = new StaticPropertyRegister<ImmutableSet<ITilePosition>, Property<ImmutableSet<ITilePosition>>>(
      Property::new);
  private final Set<StaticPropertyRegister<?, ?>> toHash = ImmutableSet.of(position);

  public TilePosition(BWReplayCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<Integer> getGroundHeight() {
    return getPropertyOnTimeLineStrategy(groundHeight);
  }

  @Override
  public Optional<Stream<IUnit>> getUnitsOnTile() {
    return getPropertyOnTimeLineStrategyOnSet(units).map(Collection::stream);
  }

  @Override
  public Optional<IPosition> getPosition() {
    return getPropertyOnTimeLineStrategy(position);
  }

  @Override
  public boolean hasCreep() {
    // todo:
    return false;
  }

  @Override
  public Optional<Boolean> hasPower(IPlayer currentPlayer) {
    // todo:
    return null;
  }

  @Override
  public Optional<Boolean> canBuildHere(IUnitType type) {
    // todo:
    return null;
  }

  @Override
  public Optional<Boolean> canBuildHere(IPlayer player, IUnitType type) {
    // todo:
    return null;
  }

  @Override
  public Optional<Stream<ITilePosition>> getNeighbours() {
    return getPropertyOnTimeLineStrategyOnSet(neighbours).map(Collection::stream);
  }

  @Override
  protected Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode() {
    return toHash;
  }
}
