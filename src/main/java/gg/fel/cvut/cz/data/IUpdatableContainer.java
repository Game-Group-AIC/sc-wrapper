package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.wrappers.Wrapper;
import gg.fel.cvut.cz.wrappers.factories.MainWrappingFactory;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

/**
 * Template for updatable container with reference on SC instance.
 *
 * @param <V>
 * @param <L>
 */
public interface IUpdatableContainer<V extends Wrapper<?>, L extends AContainer, K extends IUpdatableContainer<V, L, K>> extends InGameInterface, IContainer {

    /**
     * Method to obtain lock instance
     *
     * @return
     */
    ReentrantReadWriteLock getLock();

    /**
     * Get wrapped SC instance associated with this updatable object
     *
     * @return
     */
    V getWrappedSCInstance();

    /**
     * Get read-only container
     *
     * @return
     */
    L getDataAccessContainer();

    /**
     * Update container - thread safe wrapper
     *
     * @param mainWrappingFactory
     * @param frame
     * @param updateStrategy
     * @return
     */
    default Stream<AUpdateStrategy<?, ?, ?>> update(MainWrappingFactory mainWrappingFactory, int frame, IExecuteUpdateStrategy<V, L, K> updateStrategy) {
        try {
            getLock().writeLock().lock();
            return callUpdateStrategy(mainWrappingFactory, frame, updateStrategy);
        } finally {
            getLock().writeLock().unlock();
        }
    }

    /**
     * This method should call updateStrategy as it requires to be called with concrete implementation of this interface
     *
     * @param mainWrappingFactory
     * @param frame
     * @param updateStrategy
     * @return
     */
    Stream<AUpdateStrategy<?, ?, ?>> callUpdateStrategy(MainWrappingFactory mainWrappingFactory, int frame, IExecuteUpdateStrategy<V, L, K> updateStrategy);

}

