package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IChokePoint;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.Tuple;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public class ChokePoint extends AContainer implements IChokePoint, Serializable {

  protected final StaticPropertyRegister<Tuple<IRegion, IRegion>, Property<Tuple<IRegion, IRegion>>> regions = new StaticPropertyRegister<Tuple<IRegion, IRegion>, Property<Tuple<IRegion, IRegion>>>(
      Property::new);
  protected final StaticPropertyRegister<Tuple<IPosition, IPosition>, Property<Tuple<IPosition, IPosition>>> sides = new StaticPropertyRegister<Tuple<IPosition, IPosition>, Property<Tuple<IPosition, IPosition>>>(
      Property::new);
  protected final StaticPropertyRegister<Double, Property<Double>> width = new StaticPropertyRegister<Double, Property<Double>>(
      Property::new);
  protected final StaticPropertyRegister<IPosition, Property<IPosition>> position = new StaticPropertyRegister<IPosition, Property<IPosition>>(
      Property::new);
  private final Set<StaticPropertyRegister<?, ?>> toHash = ImmutableSet.of(position);

  public ChokePoint(BWReplayCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<Tuple<IRegion, IRegion>> getRegions() {
    return getPropertyOnTimeLineStrategy(regions);
  }

  @Override
  public Optional<Tuple<IPosition, IPosition>> getSides() {
    return getPropertyOnTimeLineStrategy(sides);
  }

  @Override
  public Optional<Double> getWidth() {
    return getPropertyOnTimeLineStrategy(width);
  }

  @Override
  public Optional<IPosition> getPosition() {
    return getPropertyOnTimeLineStrategy(position);
  }


  @Override
  protected Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode() {
    return toHash;
  }
}
