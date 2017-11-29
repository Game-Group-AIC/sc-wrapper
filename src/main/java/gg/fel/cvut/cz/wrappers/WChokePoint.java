package gg.fel.cvut.cz.wrappers;

import bwta.Chokepoint;
import java.util.HashMap;
import java.util.Map;

public class WChokePoint extends WrapperForPosition<Chokepoint> {

  private static final Map<Key, WChokePoint> register = new HashMap<>();

  private WChokePoint(Chokepoint scInstance) {
    super(scInstance, scInstance.getX(), scInstance.getY());
  }

  public static WChokePoint getOrCreateWrapper(Chokepoint chokepoint) {
    return getOrCreateWrapper(chokepoint, register,
        scInstance -> new Key(scInstance.getX(), scInstance.getY()), WChokePoint::new);
  }

}
