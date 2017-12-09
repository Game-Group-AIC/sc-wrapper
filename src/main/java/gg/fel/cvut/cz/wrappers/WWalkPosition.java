package gg.fel.cvut.cz.wrappers;

import bwapi.WalkPosition;
import java.util.HashMap;
import java.util.Map;

public class WWalkPosition extends WrapperForPosition<WalkPosition> {

  private static final Map<Key, WWalkPosition> register = new HashMap<>();

  private WWalkPosition(WalkPosition scInstance) {
    super(scInstance, scInstance.getX(), scInstance.getY());
  }

  public static WWalkPosition getOrCreateWrapper(WalkPosition walkPosition) {
    return getOrCreateWrapper(walkPosition, register,
        scInstance -> new Key(scInstance.getX(), scInstance.getY()), WWalkPosition::new);
  }

}
