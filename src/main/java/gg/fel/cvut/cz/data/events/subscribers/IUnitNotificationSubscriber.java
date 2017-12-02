package gg.fel.cvut.cz.data.events.subscribers;

import gg.fel.cvut.cz.data.readonly.Unit;

/**
 * Contract for unit event notification subscriber
 */
public interface IUnitNotificationSubscriber {

  /**
   * To notifySubscriber subscriber by event related to unit
   */
  void notifySubscriber(Unit unit);

}
