package gg.fel.cvut.cz.facades.queue;

import java.util.HashMap;
import java.util.Map;
import lombok.EqualsAndHashCode;

/**
 * Represents types of commands
 */
@EqualsAndHashCode(of = "id")
public class CommandType {

  private final String id;
  private static final Map<String, CommandType> commandTypesRegister = new HashMap<>();

  private CommandType(String id) {
    this.id = id;
  }

  public static synchronized CommandType getCommandType(String id) {
    CommandType commandType = commandTypesRegister.get(id);
    if (commandType != null) {
      return commandType;
    }
    commandType = new CommandType(id);
    commandTypesRegister.put(id, commandType);
    return commandType;
  }
}
