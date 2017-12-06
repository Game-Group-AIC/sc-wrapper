package gg.fel.cvut.cz.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.io.Serializable;

/**
 * Contract for property
 */
@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface IProperty<T> extends Serializable {

  T getValue();

  default boolean hasSameValue(T otherValue) {
    return getValue().equals(otherValue);
  }

}
