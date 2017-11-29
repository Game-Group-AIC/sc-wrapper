package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.facades.managers.UpdaterFacade;
import gg.fel.cvut.cz.wrappers.Wrapper;
import java.util.stream.Stream;

/**
 * Template for updatable container with reference on SC instance.
 */
public interface IUpdatableContainer<T extends Wrapper<?>, L extends AContainer> extends
    IContainer {

  //Monitor to lock multiple access to BWAPI at the same time
  Object SC_ACCESS_MONITOR = new Object();

  /**
   * Get wrapped SC instance associated with this updatable object
   */
  T getWrappedSCInstance();

  /**
   * This method should call updateStrategy as it requires to be called with concrete managers
   * of this interface
   */
  Stream<? extends AContainer> update(UpdaterFacade internalUpdaterFacade);

  /**
   * Get as data access container only for this updatable container
   */
  L getContainer();

}

