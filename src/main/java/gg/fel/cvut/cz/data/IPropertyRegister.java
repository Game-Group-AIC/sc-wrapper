package gg.fel.cvut.cz.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.io.Serializable;
import java.util.Optional;

/**
 * Common interface for property managers - with constant and dynamic property
 */
@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public interface IPropertyRegister<T extends Serializable> extends Serializable {

  void addProperty(T propertyValue, int inFrame) throws IllegalAccessException;

  Optional<T> getLatestValue();

  Optional<T> getValueInFrame(int frame);

  /**
   * Contract how to create new property
   */
  interface NewPropertyCreationStrategy<T extends Serializable, V extends IProperty<T>> {

    V createNewProperty(T value);
  }

}
