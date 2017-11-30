package gg.fel.cvut.cz.facades.queue;

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
  void execute() {
    executableStrategy.execute();
  }

  /**
   * Represents contract for code to be executed
   */
  public interface ExecutableStrategy {

    void execute();
  }

}
