package gg.fel.cvut.cz.facades.queue;

import java.util.Optional;
import lombok.Getter;

/**
 * Command contains code which should be executed in game. Receiver of result of execution may be
 * specified to get result of execution
 */
public class Command<V> {

  @Getter
  private final CommandType commandType;
  private final Optional<IResponseReceiver<V>> responseReceiver;
  private final ExecutableStrategy<V> executableStrategy;

  public Command(CommandType commandType, IResponseReceiver<V> responseReceiver,
      ExecutableStrategy<V> executableStrategy) {
    this.commandType = commandType;
    this.responseReceiver = Optional.ofNullable(responseReceiver);
    this.executableStrategy = executableStrategy;
  }

  public Command(CommandType commandType, ExecutableStrategy<V> executableStrategy) {
    this.commandType = commandType;
    this.responseReceiver = Optional.empty();
    this.executableStrategy = executableStrategy;
  }


  /**
   * Execute code and returns result of executed method
   */
  private V executeStrategy() {
    return executableStrategy.execute();
  }

  /**
   * Method to be called by queue manager. Default behaviour is to execute item and send result to
   * receiver
   */
  public void execute() {
    if (responseReceiver.isPresent()) {
      responseReceiver.get().receiveResponse(executeStrategy());
    } else {
      executeStrategy();
    }
  }

  /**
   * Represents contract for code to be executed
   */
  public interface ExecutableStrategy<V> {

    V execute();
  }

}
