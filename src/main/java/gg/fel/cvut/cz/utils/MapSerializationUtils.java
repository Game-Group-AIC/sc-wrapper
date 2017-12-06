package gg.fel.cvut.cz.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Simple workaround for serialization of maps using jackson
 */
public class MapSerializationUtils {

  /**
   * Create list from map
   */
  public static <K extends Serializable, V extends Serializable> ImmutableList<KeyValueElement<K, V>> toList(
      Map<K, V> map) {
    if (map == null || map.isEmpty()) {
      return ImmutableList.of();
    }
    return ImmutableList.copyOf(map.entrySet().stream()
        .map(e -> new KeyValueElement<>(e.getKey(), e.getValue()))
        .collect(Collectors.toList()));
  }

  /**
   * Create map from list
   */
  public static <K extends Serializable, V extends Serializable> ImmutableMap<K, V> toMap(
      List<KeyValueElement<K, V>> list) {
    if (list == null || list.isEmpty()) {
      return ImmutableMap.of();
    }
    return ImmutableMap.copyOf(list.stream()
        .collect(Collectors.toMap(KeyValueElement::getKey, KeyValueElement::getValue)));
  }
}
