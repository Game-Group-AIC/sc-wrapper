package gg.fel.cvut.cz.wrappers;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

class WrapperForClassWithID<T> extends Wrapper<T> {

  private final int id;

  WrapperForClassWithID(T scInstance, int id) {
    super(scInstance);
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }

    WrapperForClassWithID<?> that = (WrapperForClassWithID<?>) o;

    return id == that.id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  /**
   * Hack - for types there are static readonly - to use some meaningful ID for wrappers of those
   * readonly, their order in list is used
   */
  static <V> int getIndexInList(List<V> listWithInstances, V instance) {
    for (int i = 0; i < listWithInstances.size(); i++) {
      if (listWithInstances.get(i) == instance) {
        return i;
      }
    }
    return -1;
  }

  @EqualsAndHashCode(of = {"id"})
  @AllArgsConstructor
  static class Key implements IKey {

    private final int id;
  }

}
