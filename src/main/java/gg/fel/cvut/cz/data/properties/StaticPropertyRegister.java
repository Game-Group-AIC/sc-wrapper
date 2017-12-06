package gg.fel.cvut.cz.data.properties;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import gg.fel.cvut.cz.data.IProperty;
import gg.fel.cvut.cz.data.IPropertyRegister;
import java.io.Serializable;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Manages dynamic property by tracking its value trough the time
 */
@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class", defaultImpl = StaticPropertyRegister.class)
@Slf4j
@AllArgsConstructor
public class StaticPropertyRegister<T extends Serializable, V extends IProperty<T>> implements
    IPropertyRegister<T>,
    Serializable {

  private V property = null;
  private Integer timeOfCreation = null;

  private transient final NewPropertyCreationStrategy<T, V> newPropertyCreationStrategy;

  public StaticPropertyRegister(
      NewPropertyCreationStrategy<T, V> newPropertyCreationStrategy) {
    this.newPropertyCreationStrategy = newPropertyCreationStrategy;
  }

  public boolean propertyHasNotBeenAdded() {
    return property == null;
  }

  public void addProperty(T propertyValue, int inFrame) {

    //changing property
    if (property != null) {
      log.error("Changing property which is suppose to be constant.");
    } else {
      if (newPropertyCreationStrategy != null) {
        this.timeOfCreation = inFrame;
        this.property = newPropertyCreationStrategy.createNewProperty(propertyValue);
      }
    }
  }

  public Optional<T> getLatestValue() {
    return Optional.ofNullable(property).map(IProperty::getValue);
  }

  public Optional<T> getValueInFrame(int frame) {
    if (timeOfCreation == null || frame < timeOfCreation) {
      return Optional.empty();
    }
    return getLatestValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    StaticPropertyRegister<?, ?> that = (StaticPropertyRegister<?, ?>) o;

    return (property != null ? property.equals(that.property) : that.property == null)
        && (timeOfCreation != null ? timeOfCreation.equals(that.timeOfCreation)
        : that.timeOfCreation == null);
  }

  @Override
  public int hashCode() {
    int result = property != null ? property.hashCode() : 0;
    result = 31 * result + (timeOfCreation != null ? timeOfCreation.hashCode() : 0);
    return result;
  }
}
