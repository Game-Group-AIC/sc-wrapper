package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IChokePoint;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.IUnit;
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

public class Region extends AContainer implements IRegion, Serializable {

  protected final DynamicPropertyRegister<ImmutableSet<IUnit>, Property<ImmutableSet<IUnit>>> units = new DynamicPropertyRegister<ImmutableSet<IUnit>, Property<ImmutableSet<IUnit>>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<IChokePoint>, Property<ImmutableSet<IChokePoint>>> chokePoints = new StaticPropertyRegister<ImmutableSet<IChokePoint>, Property<ImmutableSet<IChokePoint>>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<IBaseLocation>, Property<ImmutableSet<IBaseLocation>>> baseLocations = new StaticPropertyRegister<ImmutableSet<IBaseLocation>, Property<ImmutableSet<IBaseLocation>>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<IRegion>, Property<ImmutableSet<IRegion>>> reachableRegions = new StaticPropertyRegister<ImmutableSet<IRegion>, Property<ImmutableSet<IRegion>>>(
      Property::new);
  protected final StaticPropertyRegister<IPosition, Property<IPosition>> position = new StaticPropertyRegister<IPosition, Property<IPosition>>(
      Property::new);
  private final Set<StaticPropertyRegister<?, ?>> toHash = ImmutableSet.of(position);

  public Region(BWReplayCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<IPosition> getPosition() {
    return getPropertyOnTimeLineStrategy(position);
  }

  @Override
  public Optional<Stream<IUnit>> getUnits() {
    return getPropertyOnTimeLineStrategyOnSet(units).map(Collection::stream);
  }

  @Override
  public Optional<Stream<IChokePoint>> getChokePoints() {
    return getPropertyOnTimeLineStrategyOnSet(chokePoints).map(Collection::stream);
  }

  @Override
  public Optional<Stream<IBaseLocation>> getBaseLocations() {
    return getPropertyOnTimeLineStrategyOnSet(baseLocations).map(Collection::stream);
  }

  @Override
  public Optional<Stream<IRegion>> getReachableRegions() {
    return getPropertyOnTimeLineStrategyOnSet(reachableRegions).map(Collection::stream);
  }

  @Override
  protected Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode() {
    return toHash;
  }
}
