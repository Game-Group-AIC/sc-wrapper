package gg.fel.cvut.cz.data.events.subscribers;

import gg.fel.cvut.cz.data.readonly.Position;

/**
 * Contract for on nuke detection event notification subscriber
 */
public interface INukeDetectedNotificationSubscriber {

  /**
   * To notify subscriber
   */
  void notifySubscriber(Position position);

}
