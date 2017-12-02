package gg.fel.cvut.cz.wrappers;

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

  @EqualsAndHashCode(of = {"id"})
  @AllArgsConstructor
  static class Key implements IKey {

    private final int id;
  }

}
