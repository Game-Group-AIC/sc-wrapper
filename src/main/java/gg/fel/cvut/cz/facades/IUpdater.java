package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.AContainer;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Interface to be implemented by each wrapping factory
 */
public interface IUpdater<T, L extends AContainer> {

  /**
   * Returns container corresponding to this instance. If instance container is not present, new one
   * is created
   */
  Optional<L> getWrappedInstance(T wrappedInstance);

  /**
   * Return BW instance for given container. If container is not present, empty optional is
   * returned
   */
  Optional<T> getBWInstance(L container);

  /**
   * Method to update given container and to return dependent containers
   */
  Stream<? extends AContainer> update(L containerToUpdate, int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(L containerToUpdate, int currentFrame);

  /**
   * Returns are containers associated with this updater
   */
  Stream<L> getAllContainers();

}
