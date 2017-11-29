package gg.fel.cvut.cz.wrappers;

import bwta.BaseLocation;
import java.util.HashMap;
import java.util.Map;

public class WBaseLocation extends WrapperForPosition<BaseLocation> {

  private static final Map<Key, WBaseLocation> register = new HashMap<>();

  private WBaseLocation(BaseLocation scInstance) {
    super(scInstance, scInstance.getX(), scInstance.getY());
  }

  public static WBaseLocation getOrCreateWrapper(BaseLocation baseLocation) {
    return getOrCreateWrapper(baseLocation, register,
        scInstance -> new Key(scInstance.getX(), scInstance.getY()), WBaseLocation::new);
  }

}
