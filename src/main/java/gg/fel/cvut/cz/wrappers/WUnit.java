package gg.fel.cvut.cz.wrappers;

import bwapi.Unit;
import java.util.HashMap;
import java.util.Map;

public class WUnit extends WrapperForClassWithID<Unit> {

  private static final Map<Key, WUnit> register = new HashMap<>();

  private WUnit(Unit scInstance) {
    super(scInstance, scInstance.getID());
  }

  public static WUnit getOrCreateWrapper(Unit unit) {
    return getOrCreateWrapper(unit, register, scInstance -> new Key(scInstance.getID()),
        WUnit::new);
  }

}
