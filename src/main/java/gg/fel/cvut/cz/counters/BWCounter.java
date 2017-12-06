package gg.fel.cvut.cz.counters;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.NoArgsConstructor;

/**
 * Tracks time in SC:BW
 */
@NoArgsConstructor
public class BWCounter implements IBWCounter, Serializable {

  private transient int currentFrame = 0;
  private transient final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

  public void increaseClocks() {
    try {
      lock.writeLock().lock();
      currentFrame++;
    } finally {
      lock.writeLock().unlock();
    }
  }

  public int getCurrentFrame() {
    try {
      lock.readLock().lock();
      return currentFrame;
    } finally {
      lock.readLock().unlock();
    }
  }

  void decrease() {
    try {
      lock.writeLock().lock();
      currentFrame = Math.max(0, currentFrame - 1);
    } finally {
      lock.writeLock().unlock();
    }
  }
}
