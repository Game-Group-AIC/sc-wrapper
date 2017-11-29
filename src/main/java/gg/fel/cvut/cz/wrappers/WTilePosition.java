package gg.fel.cvut.cz.wrappers;

import bwapi.TilePosition;
import java.util.HashMap;
import java.util.Map;

public class WTilePosition extends WrapperForPosition<TilePosition> {

  private static final Map<Key, WTilePosition> register = new HashMap<>();

  private WTilePosition(TilePosition scInstance) {
    super(scInstance, scInstance.getX(), scInstance.getY());
  }

  public static WTilePosition getOrCreateWrapper(TilePosition tilePosition) {
    return getOrCreateWrapper(tilePosition, register,
        scInstance -> new Key(scInstance.getX(), scInstance.getY()), WTilePosition::new);
  }

}
