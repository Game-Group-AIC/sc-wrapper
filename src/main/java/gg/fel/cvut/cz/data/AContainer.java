package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.counters.BWCounter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Common template class for containers of in-game instances
 */
public abstract class AContainer implements InGameInterface, IContainer {
    private transient BWCounter bwCounter = null;

    public void setBwCounter(BWCounter bwCounter) {
        this.bwCounter = bwCounter;
    }

    /**
     * Strategy to select property on timeline given the counter
     *
     * @param register
     * @param <T>
     * @return
     */
    protected <T extends Serializable> Optional<T> getPropertyOnTimeLineStrategy(IPropertyRegister<T> register) {
        if (bwCounter == null) {
            return register.getLatestValue();
        }
        return register.getValueInFrame(bwCounter.getCurrentFrame());
    }

    /**
     * Strategy to select property on timeline given the counter
     *
     * @param register
     * @param <V>
     * @return
     */
    protected <V extends Serializable> Optional<Set<V>> getPropertyOnTimeLineStrategyOnSet(IPropertyRegister<HashSet<V>> register) {
        if (bwCounter == null) {
            return register.getLatestValue().map(vs -> vs);
        }
        return register.getValueInFrame(bwCounter.getCurrentFrame()).map(vs -> vs);
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
    protected <V extends Serializable, K extends Serializable, T extends HashMap<V, K> & Serializable> Optional<K> getPropertyOnTimeLineStrategy(IPropertyRegister<T> register, V key) {
        if (bwCounter == null) {
            return register.getLatestValue().map(t -> t.get(key));
        }
        return register.getValueInFrame(bwCounter.getCurrentFrame()).map(t -> t.get(key));
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
        return staticPropertiesForEqualsAndHashCode().equals(that.staticPropertiesForEqualsAndHashCode());
    }

    @Override
    public int hashCode() {
        return staticPropertiesForEqualsAndHashCode().hashCode();
    }
}
