package gg.fel.cvut.cz.wrappers;

import bwapi.Bullet;
import java.util.HashMap;
import java.util.Map;

public class WBullet extends WrapperForClassWithID<Bullet> {

  private static final Map<Key, WBullet> register = new HashMap<>();

  private WBullet(Bullet scInstance) {
    super(scInstance, scInstance.getID());
  }

  public static WBullet getOrCreateWrapper(Bullet bullet) {
    return getOrCreateWrapper(bullet, register, scInstance -> new Key(scInstance.getID()),
        WBullet::new);
  }

}
