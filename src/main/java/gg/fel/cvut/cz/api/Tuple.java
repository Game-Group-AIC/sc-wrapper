package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.io.Serializable;
import java.util.Optional;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"first", "second"})
public class Tuple<K extends Serializable, V extends Serializable> implements Serializable {

  @JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "@class")
  private final K first;
  @JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "@class")
  private final V second;

  @JsonCreator
  public Tuple(@JsonProperty("first") K first, @JsonProperty("second") V second) {
    this.first = first;
    this.second = second;
  }

  public Optional<K> getFirst() {
    return Optional.ofNullable(first);
  }

  public Optional<V> getSecond() {
    return Optional.ofNullable(second);
  }
}
