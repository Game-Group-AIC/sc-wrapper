package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.facades.AUpdaterFacade;

import java.util.stream.Stream;

/**
 * Template for updatable container with reference on SC instance.
 *
 * @param <T>
 */
public interface IUpdatableContainer<T> extends InGameInterface, IContainer {

    /**
     * Get wrapped SC instance associated with this updatable object
     *
     * @return
     */
    T getSCInstance();

    /**
     * This method should call updateStrategy as it requires to be called with concrete implementation of this interface
     *
     * @param updaterFacade
     * @return
     */
    Stream<? extends AContainer> update(AUpdaterFacade updaterFacade);

}

