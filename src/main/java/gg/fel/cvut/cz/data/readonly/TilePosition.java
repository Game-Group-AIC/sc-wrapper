package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForPosition;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public class TilePosition extends AContainerForPosition implements ITilePosition, Serializable {

  protected final StaticPropertyRegister<Integer, Property<Integer>> groundHeight = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final StaticPropertyRegister<Position, Property<Position>> position = new StaticPropertyRegister<Position, Property<Position>>(
      Property::new);
  protected final StaticPropertyRegister<ImmutableSet<TilePosition>, Property<ImmutableSet<TilePosition>>> neighbours = new StaticPropertyRegister<ImmutableSet<TilePosition>, Property<ImmutableSet<TilePosition>>>(
      Property::new);

  public TilePosition(BWReplayCounter bwCounter, int x, int y) {
    super(bwCounter, x, y);
  }

  @Override
  public Optional<Integer> getGroundHeight() {
    return Optional.empty();
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
