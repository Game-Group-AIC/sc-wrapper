package gg.fel.cvut.cz.data;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.facades.UpdateStrategy;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Common template class for containers of in-game readonly
 */
public abstract class AContainer implements InGameInterface, IContainer, Serializable {
    private transient BWCounter bwCounter = null;
    protected transient final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public void setBwCounter(BWCounter bwCounter) {
        this.bwCounter = bwCounter;
    }

    /**
     * Returns whether instance should be updated based on parameters
     *
     * @param updateStrategy
     * @param deltaUpdate
     * @param depth
     * @return
     */
    public abstract boolean shouldBeUpdated(UpdateStrategy updateStrategy, int deltaUpdate, int depth);

    /**
     * Strategy to select property on timeline given the counter
     *
     * @param register
     * @param <T>
     * @return
     */
    protected <T extends Serializable> Optional<T> getPropertyOnTimeLineStrategy(IPropertyRegister<T> register) {
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
     *
     * @param register
     * @param <V>
     * @return
     */
    protected <V extends Serializable> Optional<Set<V>> getPropertyOnTimeLineStrategyOnSet(IPropertyRegister<ImmutableSet<V>> register) {
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
     *
     * @param register
     * @param key
     * @param <V>
     * @param <K>
     * @param <T>
     * @return
     */
    protected <V extends Serializable, K extends Serializable, T extends ImmutableMap<V, K> & Serializable> Optional<K> getPropertyOnTimeLineStrategy(IPropertyRegister<T> register, V key) {
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

    private Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCodeWithLock() {
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
    protected abstract Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AContainer that = (AContainer) o;
        return staticPropertiesForEqualsAndHashCodeWithLock().equals(that.staticPropertiesForEqualsAndHashCodeWithLock());
    }

    @Override
    public int hashCode() {
        return staticPropertiesForEqualsAndHashCodeWithLock().hashCode();
    }
}
