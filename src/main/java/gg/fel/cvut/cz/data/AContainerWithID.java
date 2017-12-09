package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import java.util.Objects;

public abstract class AContainerWithID extends AContainer {

  private final int id;

  protected AContainerWithID(BWReplayCounter bwCounter, int id) {
    super(bwCounter);
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AContainerWithID that = (AContainerWithID) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
