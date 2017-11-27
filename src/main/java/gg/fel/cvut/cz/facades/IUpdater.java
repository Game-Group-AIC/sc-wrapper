package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.AContainer;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Interface to be implemented by each wrapping factory
 *
 * @param <L>
 * @param <T>
 */
public interface IUpdater<T, L extends AContainer> {

    /**
     * Returns container corresponding to this instance. If instance container is not present, new one is created
     *
     * @param wrappedInstance
     * @return
     */
    Optional<L> getWrappedInstance(T wrappedInstance);

    /**
     * Return BW instance for given container. If container is not present, empty optional is returned
     *
     * @param container
     * @return
     */
    Optional<T> getBWInstance(L container);

    /**
     * Method to update given container and to return dependent containers
     *
     * @param containerToUpdate
     */
    Stream<? extends AContainer> update(L containerToUpdate, int currentFrame);

    /**
     * Returns delta of current time and last updated. If object has not been updated yet max integer is returned.
     * For object updated after current frame, zero is returned.
     *
     * @param containerToUpdate
     * @param currentFrame
     * @return
     */
    int getDeltaUpdate(L containerToUpdate, int currentFrame);

}
