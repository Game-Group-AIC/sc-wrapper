package gg.fel.cvut.cz.data.properties;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.google.common.collect.ImmutableMap;
import gg.fel.cvut.cz.data.IProperty;
import gg.fel.cvut.cz.utils.KeyValueElement;
import gg.fel.cvut.cz.utils.MapSerializationUtils;
import java.io.Serializable;
import java.util.List;
import lombok.EqualsAndHashCode;

@JsonTypeInfo(use = Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class", defaultImpl = PropertyMap.class)
@EqualsAndHashCode(of = "map")
public class PropertyMap<K extends Serializable, V extends Serializable> implements
    IProperty<ImmutableMap<K, V>> {

  @JsonIgnore
  private final transient ImmutableMap<K, V> map;

  @JsonCreator
  public PropertyMap(@JsonProperty("content") List<KeyValueElement<K, V>> content) {
    this.map = MapSerializationUtils.toMap(content);
  }

  public PropertyMap(ImmutableMap<K, V> map) {
    this.map = map;
  }

  @JsonProperty("content")
  private List<KeyValueElement<K, V>> getMapAsList() {
    return MapSerializationUtils.toList(map);
  }

  @JsonIgnore
  @Override
  public ImmutableMap<K, V> getValue() {
    return map;
  }

}
