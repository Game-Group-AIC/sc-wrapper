package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.wrappers.Wrapper;
import gg.fel.cvut.cz.wrappers.factories.MainWrappingFactory;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

/**
 * Template for strategy describing how container should be updated
 */
@AllArgsConstructor
public class AUpdateStrategy<V extends Wrapper<?>, L extends AContainer, K extends IUpdatableContainer<V, L, K>> {
    private final K executeOn;
    private final IExecuteUpdateStrategy<V, L, K> execution;

    /**
     * @param mainWrappingFactory
     * @return
     */
    public Stream<AUpdateStrategy<?, ?, ?>> executeUpdate(MainWrappingFactory mainWrappingFactory, int frame) {
        return executeOn.update(mainWrappingFactory, frame, execution);
    }

}
