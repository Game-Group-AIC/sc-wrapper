package gg.fel.cvut.cz.wrappers;

import bwapi.Position;
import java.util.HashMap;
import java.util.Map;

public class WPosition extends WrapperForPosition<Position> {

  private static final Map<Key, WPosition> register = new HashMap<>();

  private WPosition(Position scInstance) {
    super(scInstance, scInstance.getX(), scInstance.getY());
  }

  public static WPosition getOrCreateWrapper(Position position) {
    return getOrCreateWrapper(position, register,
        scInstance -> new Key(scInstance.getX(), scInstance.getY()), WPosition::new);
  }

}
