package gg.fel.cvut.cz.data.events.subscribers;

import gg.fel.cvut.cz.data.readonly.Player;

/**
 * Contract for on receive text event notification subscriber
 */
public interface IReceiveTextNotificationSubscriber {

  /**
   * To notify subscriber
   */
  void notifySubscriber(Player player, String s);

}
