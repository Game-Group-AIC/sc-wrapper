package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.facades.queue.Command;

/**
 * Contract for command executor
 */
public interface ICommandExecutor {

  /**
   * Adds command to queue so it can be executed in the future
   */
  void addCommand(Command command);

  /**
   * Execute as many commands as possible in given time interval
   */
  void executedCommands(long timeResources);

}
