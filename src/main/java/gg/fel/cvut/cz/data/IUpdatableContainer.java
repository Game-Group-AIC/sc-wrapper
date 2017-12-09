package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.Wrapper;
import java.io.Serializable;
import java.util.stream.Stream;

/**
 * Template for updatable container with reference on SC instance.
 */
public interface IUpdatableContainer<T extends Wrapper<?>, L extends AContainer> extends
    InGameInterface, Serializable {

  /**
   * Get wrapped SC instance associated with this updatable object
   */
  T getWrappedSCInstance();

  /**
   * Get as data access container
   */
  L getContainer();

  /**
   * Get time of last update
   */
  int updatedInFrame();

  /**
   * Returns delta of current time and last refresh. Returned number is positive or 0
   */
  default int deltaOfUpdate(int currentFrame) {
    if (updatedInFrame() == -1) {
      //never updated
      return Integer.MAX_VALUE;
    }
    return Math.max(0, currentFrame - updatedInFrame());
  }

  /**
   * Get all referenced containers by this container
   */
  Stream<? extends AContainer> getReferencedContainers(int currentFrame);

  /**
   * Returns whether instance should be updated based on parameters. This is overridden by updatable objects
   */
  boolean shouldBeUpdated(UpdateStrategy updateStrategy, int depth, int currentFrame);

  /**
   * Refreshes container with new data. This is overridden by updatable objects
   */
  void update(UpdateManager updateManager, int currentFrame);

  /**
   * Method calls update on instance trough updateManager
   */
  void update(UpdateManager updateManager, UpdateStrategy updateStrategy);


}

