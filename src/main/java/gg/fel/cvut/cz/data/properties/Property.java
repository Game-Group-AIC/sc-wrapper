package gg.fel.cvut.cz.data.properties;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gg.fel.cvut.cz.data.IProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Describes single property of SC instance
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class", defaultImpl = Property.class)
@EqualsAndHashCode(of = "value")
@AllArgsConstructor
public class Property<T extends Serializable> implements IProperty<T> {

  @Getter
  private final T value;
}
