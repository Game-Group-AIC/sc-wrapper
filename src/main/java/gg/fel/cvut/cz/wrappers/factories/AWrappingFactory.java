package gg.fel.cvut.cz.wrappers.factories;


import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.wrappers.Wrapper;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Template for wrapping factories
 *
 * @param <T>
 * @param <V>
 * @param <K>
 */
@AllArgsConstructor
public abstract class AWrappingFactory<T, K, V extends Wrapper<T>, L extends AContainer, U extends IUpdatableContainer<V, L, U>> implements IWrappingFactory<T, L> {
    private final ConvertInstanceToKeyStrategy<T, K> instanceToKeyConvertingStrategy;
    private final Map<K, U> register = new HashMap<>();

    /**
     * Template for strategy which takes SC instance and returns its key - to be able to use it in dictionary
     *
     * @param <T>
     * @param <K>
     */
    public interface ConvertInstanceToKeyStrategy<T, K> {
        K convertInstanceToKey(T instance);
    }
}
