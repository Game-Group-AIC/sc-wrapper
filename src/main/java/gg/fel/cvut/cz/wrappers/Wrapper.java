package gg.fel.cvut.cz.wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Wrapper template for SC classes
 * @param <T>
 */
@AllArgsConstructor
abstract class Wrapper<T> {

    @Getter
    final T scInstance;

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}
