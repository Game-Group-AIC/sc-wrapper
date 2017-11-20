package gg.fel.cvut.cz.wrappers.register;


import gg.fel.cvut.cz.wrappers.Wrapper;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Template for wrapping register
 *
 * @param <T>
 * @param <V>
 * @param <K>
 */
@AllArgsConstructor
public abstract class AWrappingRegister<T, V extends Wrapper<T>, K> {
    private final ConvertInstanceToKeyStrategy<T, K> instanceToKeyConvertingStrategy;
    private final Map<K, T> register = new HashMap<>();

//    V getWrappedInstance(T instanceToWrap);

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
