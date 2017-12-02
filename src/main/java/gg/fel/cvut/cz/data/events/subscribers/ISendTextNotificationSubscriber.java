package gg.fel.cvut.cz.data.events.subscribers;

/**
 * Contract for on send text event notification subscriber
 */
public interface ISendTextNotificationSubscriber {

  /**
   * To notify subscriber
   */
  void notifySubscriber(String s);

}
