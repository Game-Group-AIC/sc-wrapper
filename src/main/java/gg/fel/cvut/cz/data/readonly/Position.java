package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForPosition;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.Optional;

public class Position extends AContainerForPosition implements IPosition, Serializable {

  protected final StaticPropertyRegister<ITilePosition, Property<ITilePosition>> tilePosition = new StaticPropertyRegister<ITilePosition, Property<ITilePosition>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> x = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> y = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<IRegion, Property<IRegion>> region = new StaticPropertyRegister<IRegion, Property<IRegion>>(
      Property::new);

  public Position(BWReplayCounter bwCounter, int x, int y) {
    super(bwCounter, x, y);
  }

  @Override
  public Optional<Integer> getX() {
    return getPropertyOnTimeLineStrategy(x);
  }

  @Override
  public Optional<Integer> getY() {
    return getPropertyOnTimeLineStrategy(y);
  }

  @Override
  public Optional<ITilePosition> getTilePosition() {
    return getPropertyOnTimeLineStrategy(tilePosition);
  }

  @Override
  public Optional<IRegion> getRegion() {
    return getPropertyOnTimeLineStrategy(region);
  }
}
