package gg.fel.cvut.cz.facades.queue.implementation;

import gg.fel.cvut.cz.facades.queue.Command;

/**
 * Represents command which will be just executed.
 */
public class CommandWithoutResponse extends Command {

  private final ExecutableStrategy executableStrategy;

  public CommandWithoutResponse(CommandType commandType,
      ExecutableStrategy executableStrategy) {
    super(commandType);
    this.executableStrategy = executableStrategy;
  }

  @Override
  public void execute() {
    executableStrategy.execute();
  }

  /**
   * Represents contract for code to be executed
   */
  public interface ExecutableStrategy {

    void execute();
  }

}
