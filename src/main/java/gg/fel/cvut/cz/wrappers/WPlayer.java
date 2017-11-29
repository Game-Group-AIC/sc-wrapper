package gg.fel.cvut.cz.wrappers;

import bwapi.Player;
import java.util.HashMap;
import java.util.Map;

public class WPlayer extends WrapperForClassWithID<Player> {

  private static final Map<Key, WPlayer> register = new HashMap<>();

  private WPlayer(Player scInstance) {
    super(scInstance, scInstance.getID());
  }

  public static WPlayer getOrCreateWrapper(Player player) {
    return getOrCreateWrapper(player, register, scInstance -> new Key(scInstance.getID()),
        WPlayer::new);
  }
}
