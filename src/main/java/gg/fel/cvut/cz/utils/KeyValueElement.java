package gg.fel.cvut.cz.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "key")
public class KeyValueElement<K extends Serializable, V extends Serializable> implements
    Serializable {

  @JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "@class")
  private final K key;

  @JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "@class")
  private final V value;

  @JsonCreator
  public KeyValueElement(@JsonProperty("key") K key, @JsonProperty("value") V value) {
    this.key = key;
    this.value = value;
  }

}
