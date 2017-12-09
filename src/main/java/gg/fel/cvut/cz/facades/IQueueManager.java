package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.facades.queue.Command;

/**
 * Contract for command executor
 */
public interface IQueueManager {

  /**
   * Adds command to queue so it can be executed in the future
   */
  void executeCommands(long timeResources);

  /**
   * Execute as many commands as possible in given time interval
   */
  void addCommand(Command command);

}
