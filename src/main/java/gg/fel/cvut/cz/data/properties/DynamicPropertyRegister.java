package gg.fel.cvut.cz.data.properties;

import com.google.common.collect.Iterables;
import gg.fel.cvut.cz.data.IPropertyRegister;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * Manages dynamic property by tracking its value trough the time
 */
@Slf4j
public class DynamicPropertyRegister<T extends Serializable> implements IPropertyRegister<T>,
    Serializable {

  //TODO replace by class with time. Make second transient - init it on access only
  private final List<Property<T>> propertyTimeline = new ArrayList<>();
  private final List<Integer> timelineWithReferenceToProperty = new ArrayList<>();

  public void addProperty(T propertyValue, int inFrame) {

    //altering timeline
    if (timelineWithReferenceToProperty.size() > inFrame) {
      log.error("The timeline can not be altered.");
    }
    //extending timeline
    else {

      //fill timeline with time steps and references
      for (int i = timelineWithReferenceToProperty.size(); i < inFrame; i++) {
        timelineWithReferenceToProperty.add(propertyTimeline.size() - 1);
      }

      //do we have new value?
      if (propertyTimeline.isEmpty() || !Iterables.getLast(propertyTimeline)
          .hasSameValue(propertyValue)) {
        propertyTimeline.add(new Property<>(propertyValue));
      }

      //add current
      timelineWithReferenceToProperty.add(propertyTimeline.size() - 1);
    }
  }

  //TODO collect values in interval

  public Optional<T> getLatestValue() {
    if (propertyTimeline.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(Iterables.getLast(propertyTimeline)).map(Property::getValue);
  }

  public Optional<T> getValueInFrame(int frame) {
    if (timelineWithReferenceToProperty.size() <= frame) {
      return getLatestValue();
    }
    int index = timelineWithReferenceToProperty.get(frame);
    if (index < 0) {
      return Optional.empty();
    }
    return Optional.of(propertyTimeline.get(index)).map(Property::getValue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DynamicPropertyRegister<?> that = (DynamicPropertyRegister<?>) o;
    return propertyTimeline.equals(that.propertyTimeline) && timelineWithReferenceToProperty
        .equals(that.timelineWithReferenceToProperty);
  }

  @Override
  public int hashCode() {
    int result = propertyTimeline.hashCode();
    result = 31 * result + timelineWithReferenceToProperty.hashCode();
    return result;
  }
}
