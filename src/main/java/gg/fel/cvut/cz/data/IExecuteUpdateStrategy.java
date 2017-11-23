package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.wrappers.Wrapper;
import gg.fel.cvut.cz.wrappers.factories.MainWrappingFactory;

import java.util.stream.Stream;

/**
 * Strategy to declare execution method in order to updatable properties
 *
 * @param <V>
 */
public interface IExecuteUpdateStrategy<V extends Wrapper<?>, L extends AContainer, K extends IUpdatableContainer<V, L, K>> {

    /**
     * Execute updateable on updatable container and return stream of updates to execute next
     *
     * @param updateWhat
     * @param mainWrappingFactory
     * @param frame
     * @return
     */
    Stream<AUpdateStrategy<?, ?, ?>> executeUpdate(K updateWhat, MainWrappingFactory mainWrappingFactory, int frame);

}
