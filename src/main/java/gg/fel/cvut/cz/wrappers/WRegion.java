package gg.fel.cvut.cz.wrappers;

import bwta.Region;
import java.util.HashMap;
import java.util.Map;

public class WRegion extends WrapperForPosition<Region> {

  private static final Map<Key, WRegion> register = new HashMap<>();

  private WRegion(Region scInstance) {
    super(scInstance, scInstance.getX(), scInstance.getY());
  }

  public static WRegion getOrCreateWrapper(Region region) {
    return getOrCreateWrapper(region, register,
        scInstance -> new Key(scInstance.getX(), scInstance.getY()), WRegion::new);
  }
}
