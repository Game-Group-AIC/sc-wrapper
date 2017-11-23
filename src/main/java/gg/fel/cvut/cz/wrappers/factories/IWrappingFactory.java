package gg.fel.cvut.cz.wrappers.factories;

import gg.fel.cvut.cz.data.AContainer;

/**
 * Interface to be implemented by each wrapping factory
 *
 * @param <L>
 * @param <T>
 */
public interface IWrappingFactory<T, L extends AContainer> {

    /**
     * Returns container corresponding to this instance
     *
     * @param instanceToWrap
     * @return
     */
    L getWrappedInstance(T instanceToWrap);

}
