package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.enums.IGameTypes;
import java.util.Objects;

public abstract class AContainerForType<T, V extends IGameTypes<T, V>> extends AContainer {

  protected final V type;

  protected AContainerForType(BWReplayCounter bwCounter, V type) {
    super(bwCounter);
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AContainerForType<?, ?> that = (AContainerForType<?, ?>) o;
    return Objects.equals(type, that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }
}
