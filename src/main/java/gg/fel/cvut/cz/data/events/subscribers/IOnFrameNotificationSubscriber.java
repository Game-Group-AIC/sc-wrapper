package gg.fel.cvut.cz.data.events.subscribers;

/**
 * Contract for on game end event notification subscriber
 */
public interface IOnFrameNotificationSubscriber {

  /**
   * To notify subscriber
   */
  void notifySubscriber(int currentFrame);

}
