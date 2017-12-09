package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IWalkPosition;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForPosition;
import java.io.Serializable;
import java.util.Optional;

//TODO implement
public class WalkPosition extends AContainerForPosition implements IWalkPosition, Serializable {

  protected WalkPosition(BWReplayCounter bwCounter, int x, int y) {
    super(bwCounter, x, y);
  }

  @Override
  public Optional<Boolean> isWalkable() {
    return Optional.empty();
  }

  @Override
  public Optional<IPosition> getPosition() {
    return Optional.empty();
  }
}
