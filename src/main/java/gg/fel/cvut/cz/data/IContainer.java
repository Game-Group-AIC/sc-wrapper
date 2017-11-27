package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.facades.IInternalUpdaterFacade;
import gg.fel.cvut.cz.facades.UpdateStrategy;

import java.io.Serializable;

/**
 * Interface for data container
 */
public interface IContainer extends InGameInterface, Serializable {

    void setBwCounter(BWCounter bwCounter);

    /**
     * Returns whether instance should be updated based on parameters
     *
     * @param updateStrategy
     * @param internalUpdaterFacade
     * @param depth
     * @return
     */
    default boolean shouldBeUpdated(UpdateStrategy updateStrategy, IInternalUpdaterFacade internalUpdaterFacade, int depth) {
        return false;
    }

    /**
     * Returns whether instance should be updated based on parameters
     *
     * @param updateStrategy
     * @param internalUpdaterFacade
     * @param depth
     * @param currentFrame
     * @return
     */
    default void update(UpdateStrategy updateStrategy, IInternalUpdaterFacade internalUpdaterFacade, int depth, int currentFrame) {
        //EMPTY
    }

}
