package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import java.util.Objects;

public abstract class AContainerForPosition extends AContainer {

  private final int x, y;

  protected AContainerForPosition(BWReplayCounter bwCounter, int x, int y) {
    super(bwCounter);
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AContainerForPosition that = (AContainerForPosition) o;
    return x == that.x &&
        y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
