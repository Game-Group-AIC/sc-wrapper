package gg.fel.cvut.cz.data.events.subscribers;

import gg.fel.cvut.cz.data.readonly.Player;

/**
 * Contract for on player left event notification subscriber
 */
public interface IPlayerLeftNotificationSubscriber {

  /**
   * To notify subscriber
   */
  void notifySubscriber(Player player);

}
