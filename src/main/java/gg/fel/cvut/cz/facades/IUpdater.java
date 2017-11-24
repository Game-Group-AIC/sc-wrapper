package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.AContainer;

import java.util.Optional;

/**
 * Interface to be implemented by each wrapping factory
 *
 * @param <L>
 * @param <T>
 */
public interface IUpdater<T, L extends AContainer> {

    /**
     * Returns container corresponding to this instance
     *
     * @param instanceToWrap
     * @return
     */
    Optional<L> getWrappedInstance(T instanceToWrap);

    /**
     * Return BW instance for given container
     *
     * @param container
     * @return
     */
    Optional<T> getBWInstance(L container);

    /**
     * Method to update given container
     *
     * @param containerToUpdate
     */
    void update(L containerToUpdate);

}
