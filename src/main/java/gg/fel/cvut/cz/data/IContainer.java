package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.facades.IUpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import java.io.Serializable;

/**
 * Interface for data container
 */
public interface IContainer extends InGameInterface, Serializable {

  /**
   * Returns whether instance should be updated based on parameters
   */
  default boolean shouldBeUpdated(UpdateStrategy updateStrategy, IUpdateManager updaterFacade,
      int depth) {
    return false;
  }

  /**
   * Returns whether instance should be updated based on parameters
   */
  default void update(UpdateStrategy updateStrategy, IUpdateManager updaterFacade, int depth,
      int currentFrame) {
    //EMPTY
  }

  /**
   * Returns number of frame when container was last updated. If container was never updated, returns -1
   */
  int updatedInFrame();

}
