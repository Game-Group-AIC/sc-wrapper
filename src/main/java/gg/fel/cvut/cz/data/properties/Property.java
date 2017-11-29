package gg.fel.cvut.cz.data.properties;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Describes single property of SC instance
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
