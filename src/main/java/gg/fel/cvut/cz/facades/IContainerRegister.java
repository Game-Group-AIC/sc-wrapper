package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.wrappers.Wrapper;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Interface to be implemented by each wrapping factory
 */
public interface IContainerRegister<T extends Wrapper<?>, L extends AContainer, U extends IUpdatableContainer<T, L>> {

  /**
   * Returns container corresponding to this instance. If instance container is not present, new one
   * is created
   */
  Optional<U> getWrappedInstance(T wrappedInstance);

  /**
   * Returns are containers associated with this updater
   */
  Stream<L> getAllContainers();

}
