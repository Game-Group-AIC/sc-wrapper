package gg.fel.cvut.cz.wrappers;

import gg.fel.cvut.cz.enums.IGameTypes;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;

/**
 * Common ancestor for all types
 */
public abstract class WrapperForType<T, V extends IGameTypes<T, V>> extends
    WrapperForClassWithID<T> {

  @Getter
  private final V type;

  protected transient static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

  WrapperForType(T scInstance, V type) {
    super(scInstance, type.ordinal());
    this.type = type;
  }

}
