package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.enums.IGameTypes;

public abstract class AContainerForType<T, V extends IGameTypes<T, V>> extends AContainer {

  protected final V type;

  protected AContainerForType(BWReplayCounter bwCounter, V type) {
    super(bwCounter);
    this.type = type;
  }
}
