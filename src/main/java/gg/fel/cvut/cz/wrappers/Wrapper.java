package gg.fel.cvut.cz.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Wrapper template for SC classes
 *
 * @param <T>
 */
@JsonIgnoreType
@AllArgsConstructor
public abstract class Wrapper<T> {
    @Getter
    private final T scInstance;

    @Override
    public abstract int hashCode();

    @Override
    public abstract boolean equals(Object obj);
}
