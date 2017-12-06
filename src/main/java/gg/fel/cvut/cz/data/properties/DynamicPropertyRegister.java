package gg.fel.cvut.cz.data.properties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.google.common.collect.Iterables;
import gg.fel.cvut.cz.api.Tuple;
import gg.fel.cvut.cz.data.IProperty;
import gg.fel.cvut.cz.data.IPropertyRegister;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

/**
 * Manages dynamic property by tracking its value trough the time
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class", defaultImpl = DynamicPropertyRegister.class)
@Slf4j
public class DynamicPropertyRegister<T extends Serializable, V extends IProperty<T>> implements
    IPropertyRegister<T>,
    Serializable {

  private final List<V> propertyTimeline = new ArrayList<>();

  @JsonIgnore
  private transient final List<Integer> timelineWithReferenceToProperty = new ArrayList<>();

  private final transient NewPropertyCreationStrategy<T, V> newPropertyCreationStrategy;

  public DynamicPropertyRegister(
      NewPropertyCreationStrategy<T, V> newPropertyCreationStrategy) {
    this.newPropertyCreationStrategy = newPropertyCreationStrategy;
  }

  @JsonCreator
  public DynamicPropertyRegister(@JsonProperty("propertyTimeline") List<V> propertyTimeline,
      @JsonProperty("deltaDifferences") List<Tuple<Integer, Integer>> deltaDifferences) {
    this.newPropertyCreationStrategy = null;
    this.propertyTimeline.addAll(propertyTimeline);
    this.timelineWithReferenceToProperty
        .addAll(makeTimeLineWithReferenceToProperty(deltaDifferences));
  }

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
      if ((propertyTimeline.isEmpty() || !Iterables.getLast(propertyTimeline)
          .hasSameValue(propertyValue)) && newPropertyCreationStrategy != null) {
        propertyTimeline.add(newPropertyCreationStrategy.createNewProperty(propertyValue));
      }

      //add current
      timelineWithReferenceToProperty.add(propertyTimeline.size() - 1);
    }
  }

  public Optional<T> getLatestValue() {
    if (propertyTimeline.isEmpty()) {
      return Optional.empty();
    }
    return Optional.ofNullable(Iterables.getLast(propertyTimeline)).map(IProperty::getValue);
  }

  public Optional<T> getValueInFrame(int frame) {
    if (timelineWithReferenceToProperty.size() <= frame) {
      return getLatestValue();
    }
    int index = timelineWithReferenceToProperty.get(frame);
    if (index < 0) {
      return Optional.empty();
    }
    return Optional.of(propertyTimeline.get(index)).map(IProperty::getValue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DynamicPropertyRegister<?, ?> that = (DynamicPropertyRegister<?, ?>) o;
    return propertyTimeline.equals(that.propertyTimeline) && timelineWithReferenceToProperty
        .equals(that.timelineWithReferenceToProperty);
  }

  @Override
  public int hashCode() {
    int result = propertyTimeline.hashCode();
    result = 31 * result + timelineWithReferenceToProperty.hashCode();
    return result;
  }

  @JsonProperty("deltaDifferences")
  private List<Tuple<Integer, Integer>> getDeltaDifferences() {
    List<Tuple<Integer, Integer>> differences = new ArrayList<>();
    int lastPointer = -1, currentPointer;
    for (int i = 0; i < timelineWithReferenceToProperty.size(); i++) {
      currentPointer = timelineWithReferenceToProperty.get(i);
      if (currentPointer != lastPointer) {
        differences.add(new Tuple<>(i, currentPointer));
        lastPointer = currentPointer;
      }
    }
    return differences;
  }

  private List<Integer> makeTimeLineWithReferenceToProperty(
      List<Tuple<Integer, Integer>> deltaDifferences) {
    List<Integer> timelineWithReferenceToProperty = new ArrayList<>();
    int lastChange = 0, lastReference = -1;

    //in zero time set to null pointer
    timelineWithReferenceToProperty.add(lastReference);

    for (int i = 0; i < deltaDifferences.size(); i++) {
      Tuple<Integer, Integer> change = deltaDifferences.get(i);
      for (int j = lastChange; j < change.getFirst().get() - 1; j++) {
        timelineWithReferenceToProperty.add(lastReference);
      }
      lastChange = change.getFirst().get();
      lastReference = change.getSecond().get();
      timelineWithReferenceToProperty.add(lastReference);
    }
    return timelineWithReferenceToProperty;
  }

}
