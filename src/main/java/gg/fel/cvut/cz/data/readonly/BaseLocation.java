package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForPosition;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public class BaseLocation extends AContainerForPosition implements IBaseLocation, Serializable {

  protected final DynamicPropertyRegister<Integer, Property<Integer>> minerals = new DynamicPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final DynamicPropertyRegister<Integer, Property<Integer>> gas = new DynamicPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> staticMineralsAsUnits = new StaticPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> geysers = new StaticPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  protected final StaticPropertyRegister<Boolean, Property<Boolean>> isIsland = new StaticPropertyRegister<Boolean, Property<Boolean>>(
      Property::new);
  protected final StaticPropertyRegister<Boolean, Property<Boolean>> isStartLocation = new StaticPropertyRegister<Boolean, Property<Boolean>>(
      Property::new);
  protected final StaticPropertyRegister<Position, Property<Position>> position = new StaticPropertyRegister<Position, Property<Position>>(
      Property::new);

  public BaseLocation(BWReplayCounter bwCounter, int x, int y) {
    super(bwCounter, x, y);
  }

  @Override
  public Optional<Integer> minerals() {
    return getPropertyOnTimeLineStrategy(minerals);
  }

  @Override
  public Optional<Integer> gas() {
    return getPropertyOnTimeLineStrategy(gas);
  }

  @Override
  public Optional<Stream<IUnit>> getStaticMinerals() {
    return getPropertyOnTimeLineStrategyOnSet(staticMineralsAsUnits)
        .map(units -> units.stream().map(unit -> unit));
  }

  @Override
  public Optional<Stream<IUnit>> getGeysers() {
    return getPropertyOnTimeLineStrategyOnSet(geysers)
        .map(units -> units.stream().map(unit -> unit));
  }

  @Override
  public Optional<Boolean> isIsland() {
    return getPropertyOnTimeLineStrategy(isIsland);
  }

  @Override
  public Optional<Boolean> isStartLocation() {
    return getPropertyOnTimeLineStrategy(isStartLocation);
  }

  @Override
  public Optional<IPosition> getPosition() {
    return getPropertyOnTimeLineStrategy(position).map(p -> p);
  }

  //TODO compute - move to implementation to cache values?
  public Optional<Double> getGroundDistance(IBaseLocation other) {
    return Optional.of(Double.MAX_VALUE);
  }
}
