package gg.fel.cvut.cz.data.properties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * Describes single property of SC instance
 *
 * @param <T>
 */
@EqualsAndHashCode(of = "value")
@AllArgsConstructor
class Property<T extends Serializable> implements Serializable {
    @Getter
    private final T value;

    boolean hasSameValue(T otherValue) {
        return value.equals(otherValue);
    }
}
