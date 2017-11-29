package gg.fel.cvut.cz.facades.strategies;

import gg.fel.cvut.cz.data.AContainer;

/**
 * Interface for strategy to decide if instance should be updated
 */
public interface IUpdateContainerStrategy<L extends AContainer> {

  /**
   * Returns whether container should be updated based on difference from last update and depth of
   * update tree
   */
  boolean shouldBeUpdated(L container, int deltaUpdate, int depth);

}
