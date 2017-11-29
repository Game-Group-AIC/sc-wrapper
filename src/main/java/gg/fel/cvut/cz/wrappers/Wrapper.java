package gg.fel.cvut.cz.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.Getter;

/**
 * Wrapper template for SC classes
 */
@JsonIgnoreType
public abstract class Wrapper<T> {

  private transient static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

  @Getter
  private final T scInstance;

  Wrapper(T scInstance) {
    this.scInstance = scInstance;
  }

  @Override
  public abstract int hashCode();

  @Override
  public abstract boolean equals(Object obj);

  static <T, L extends Wrapper<T>, K extends IKey> L getOrCreateWrapper(T toWrap, Map<K, L> register
      , IKeyBuilderStrategy<T, K> keyBuilderStrategy, IWrappingStrategy<T, L> wrappingStrategy) {
    K key;
    try {
      lock.writeLock().lock();
      key = keyBuilderStrategy.buildKey(toWrap);
    } finally {
      lock.writeLock().unlock();
    }
    L wrapper;
    try {
      lock.readLock().lock();
      wrapper = register.get(key);
      if (wrapper != null) {
        return wrapper;
      }
    } finally {
      lock.readLock().unlock();
    }
    try {
      lock.writeLock().lock();
      wrapper = wrappingStrategy.wrap(toWrap);
      register.put(key, wrapper);
      return wrapper;
    } finally {
      lock.writeLock().unlock();
    }
  }

  /**
   * Strategy to build key
   */
  interface IKeyBuilderStrategy<T, V extends IKey> {

    /**
     * Builds key
     */
    V buildKey(T scInstance);

  }

  /**
   * Strategy to build key
   */
  interface IWrappingStrategy<T, L extends Wrapper<T>> {

    /**
     * Wrap
     */
    L wrap(T scInstance);

  }

}
