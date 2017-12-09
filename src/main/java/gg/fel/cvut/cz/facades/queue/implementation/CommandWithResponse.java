package gg.fel.cvut.cz.facades.queue.implementation;

import gg.fel.cvut.cz.facades.queue.Command;
import gg.fel.cvut.cz.facades.queue.IResponseReceiver;

/**
 * Command which sends response to reciever on its execution
 */
public class CommandWithResponse<V> extends Command {

  private final IResponseReceiver<V> responseReceiver;
  private final ExecutableStrategy<V> executableStrategy;

  public CommandWithResponse(CommandType commandType,
      IResponseReceiver<V> responseReceiver,
      ExecutableStrategy<V> executableStrategy) {
    super(commandType);
    this.responseReceiver = responseReceiver;
    this.executableStrategy = executableStrategy;
  }


  /**
   * Execute code and returns result of executed method
   */
  private V executeStrategy() {
    return executableStrategy.execute();
  }

  public void execute() {
    responseReceiver.receiveResponse(executeStrategy());
  }

  /**
   * Represents contract for code to be executed
   */
  public interface ExecutableStrategy<V> {

    V execute();
  }

}
