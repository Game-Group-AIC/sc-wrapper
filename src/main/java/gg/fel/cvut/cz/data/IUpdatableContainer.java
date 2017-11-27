package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.facades.UpdaterFacade;
import gg.fel.cvut.cz.wrappers.Wrapper;

import java.util.stream.Stream;

/**
 * Template for updatable container with reference on SC instance.
 *
 * @param <T>
 */
public interface IUpdatableContainer<T extends Wrapper<?>, L extends AContainer> extends IContainer {

    //Monitor to lock multiple access to BWAPI at the same time
    Object SC_ACCESS_MONITOR = new Object();

    /**
     * Get wrapped SC instance associated with this updatable object
     *
     * @return
     */
    T getWrappedSCInstance();

    /**
     * This method should call updateStrategy as it requires to be called with concrete implementation of this interface
     *
     * @param updaterFacade
     * @return
     */
    Stream<? extends AContainer> update(UpdaterFacade updaterFacade);

    /**
     * Get as data access container only for this updatable container
     *
     * @return
     */
    L getContainer();

}

