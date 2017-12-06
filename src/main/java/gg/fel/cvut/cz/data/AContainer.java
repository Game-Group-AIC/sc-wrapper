package gg.fel.cvut.cz.data;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Common template class for containers of in-game readonly
 */
//TODO use concrete implementations in data containers
public abstract class AContainer implements IContainer {

  protected BWReplayCounter bwCounter;
  protected transient final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

  protected AContainer(BWReplayCounter bwCounter) {
    this.bwCounter = bwCounter;
  }

  /**
   * Strategy to select property on timeline given the counter
   */
  protected <T extends Serializable> Optional<T> getPropertyOnTimeLineStrategy(
      IPropertyRegister<T> register) {
    try {
      lock.readLock().lock();
      if (bwCounter == null) {
        return register.getLatestValue();
      }
      return register.getValueInFrame(bwCounter.getCurrentFrame());
    } finally {
      lock.readLock().unlock();
    }
  }

  /**
   * Strategy to select property on timeline given the counter
   */
  protected <V extends Serializable> Optional<Set<V>> getPropertyOnTimeLineStrategyOnSet(
      IPropertyRegister<ImmutableSet<V>> register) {
    try {
      lock.readLock().lock();
      if (bwCounter == null) {
        return register.getLatestValue().map(vs -> vs);
      }
      return register.getValueInFrame(bwCounter.getCurrentFrame()).map(vs -> vs);
    } finally {
      lock.readLock().unlock();
    }
  }

  /**
   * Strategy to select property on timeline given the counter
   */
  protected <V extends Serializable, K extends Serializable, T extends Map<? extends V, K> & Serializable> Optional<K> getPropertyOnTimeLineStrategy(
      IPropertyRegister<T> register, V key) {
    try {
      lock.readLock().lock();
      if (bwCounter == null) {
        return register.getLatestValue().map(t -> t.get(key));
      }
      return register.getValueInFrame(bwCounter.getCurrentFrame()).map(t -> t.get(key));
    } finally {
      lock.readLock().unlock();
    }
  }

  private Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCodeWithLock() {
    try {
      lock.readLock().lock();
      return staticPropertiesForEqualsAndHashCode();
    } finally {
      lock.readLock().unlock();
    }
  }

  /**
   * Properties used for equals and hash code
   */
  protected abstract Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AContainer that = (AContainer) o;
    return staticPropertiesForEqualsAndHashCodeWithLock()
        .equals(that.staticPropertiesForEqualsAndHashCodeWithLock());
  }

  @Override
  public int hashCode() {
    return staticPropertiesForEqualsAndHashCodeWithLock().hashCode();
  }
}
