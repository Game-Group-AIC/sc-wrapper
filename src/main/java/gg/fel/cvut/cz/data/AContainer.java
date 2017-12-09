package gg.fel.cvut.cz.data;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Common template class for containers of in-game readonly
 */
//TODO from json
public abstract class AContainer implements InGameInterface, Serializable {

  protected BWReplayCounter bwCounter;
  protected transient final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

  //negative one indicates that data have not been updated yet
  protected int updatedInFrame = -1;

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
   * Returns refresh time of data contained in container
   */
  public int updatedInFrame() {
    try {
      lock.readLock().lock();
      return updatedInFrame;
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

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();
}
