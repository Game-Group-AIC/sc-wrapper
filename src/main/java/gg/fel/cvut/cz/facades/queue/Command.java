package gg.fel.cvut.cz.facades.queue;

import lombok.Getter;

/**
 * Command contains code which should be executed in game
 */
public abstract class Command {

  @Getter
  private final CommandType commandType;

  Command(CommandType commandType) {
    this.commandType = commandType;
  }

  /**
   * Method to be called by queue manager to execute command
   */
  abstract void execute();

}
