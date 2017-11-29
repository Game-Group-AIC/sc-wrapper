package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.facades.IUpdaterFacade;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import java.io.Serializable;

/**
 * Interface for data container
 */
public interface IContainer extends InGameInterface, Serializable {

  void setBwCounter(BWCounter bwCounter);

  /**
   * Returns whether instance should be updated based on parameters
   */
  default boolean shouldBeUpdated(UpdateStrategy updateStrategy, IUpdaterFacade updaterFacade,
      int depth) {
    return false;
  }

  /**
   * Returns whether instance should be updated based on parameters
   */
  default void update(UpdateStrategy updateStrategy, IUpdaterFacade updaterFacade, int depth,
      int currentFrame) {
    //EMPTY
  }

}
