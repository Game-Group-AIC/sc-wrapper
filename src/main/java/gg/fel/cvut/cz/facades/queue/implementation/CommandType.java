package gg.fel.cvut.cz.facades.queue.implementation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.EqualsAndHashCode;

/**
 * Represents types of commands
 */
@EqualsAndHashCode(of = "id")
public class CommandType {

  private final String id;
  private static final Map<String, CommandType> commandTypesRegister = new HashMap<>();
  private static final ReadWriteLock LOCK = new ReentrantReadWriteLock(true);

  private CommandType(String id) {
    this.id = id;
  }

  public static CommandType getCommandType(String id) {
    CommandType commandType;
    try {
      LOCK.readLock().lock();
      commandType = commandTypesRegister.get(id);
    } finally {
      LOCK.readLock().unlock();
    }
    if (commandType != null) {
      return commandType;
    }
    commandType = new CommandType(id);
    try {
      LOCK.writeLock().lock();
      commandTypesRegister.put(id, commandType);
    } finally {
      LOCK.writeLock().unlock();
    }
    return commandType;
  }
}
