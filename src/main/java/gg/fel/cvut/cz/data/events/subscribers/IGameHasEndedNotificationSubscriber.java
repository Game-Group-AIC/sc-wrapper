package gg.fel.cvut.cz.data.events.subscribers;

/**
 * Contract for on game end event notification subscriber
 */
public interface IGameHasEndedNotificationSubscriber {

  /**
   * To notify subscriber
   */
  void notifySubscriber(boolean result);

}
