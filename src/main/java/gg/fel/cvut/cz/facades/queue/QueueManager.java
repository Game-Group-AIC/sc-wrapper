package gg.fel.cvut.cz.facades.queue;

import gg.fel.cvut.cz.facades.ICommandExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.EqualsAndHashCode;

/**
 * Manages queued items - to order them by type that each type has fair chance that its commands
 * will be executed
 */
public class QueueManager implements ICommandExecutor {

  private final Map<CommandType, Queue> queues = new HashMap<>();
  private final List<Queue> orderOfQueues = new ArrayList<>();
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
  private List<Queue> toExecute = new ArrayList<>();

  @Override
  public void executedCommands(long timeResources) {
    long start = System.currentTimeMillis();
    boolean executedAnyCommand, hasTimeRemaining = true;

    //try to execute previous requests
    List<Queue> toExecuteNext = new ArrayList<>();
    for (int i = 0; i < toExecute.size(); i++) {
      if (toExecute.get(i).canBeExpectedToBeExecuteInInterval(
          timeResources - (System.currentTimeMillis() - start))) {
        toExecute.get(i).executedCommand();
      } else {

        //higher priority is assigned
        toExecuteNext.add(toExecute.get(i));
      }
      if (timeResources - (System.currentTimeMillis() - start) <= 0) {

        //reschedule
        if (i + 1 < toExecute.size()) {
          toExecuteNext.addAll(toExecute.subList(i + 1, toExecute.size()));
        }

        //exit execution and resume next time where it ended
        hasTimeRemaining = false;
        break;
      }
    }

    toExecute = toExecuteNext;
    Set<Queue> queuesForExecution = new HashSet<>(toExecuteNext);

    //execute new requests if there is still time
    if (hasTimeRemaining && timeResources - (System.currentTimeMillis() - start) > 0) {
      int queues;
      try {
        lock.readLock().lock();
        queues = orderOfQueues.size();
      } finally {
        lock.readLock().unlock();
      }

      //execute new request while there is a time
      while (true) {
        executedAnyCommand = false;
        for (int i = 0; i < queues; i++) {
          if (timeResources - (System.currentTimeMillis() - start) <= 0) {

            //exit execution and resume next time where it ended
            hasTimeRemaining = false;
            break;
          }
          Queue queue;
          try {
            lock.readLock().lock();
            queue = orderOfQueues.get(i);
          } finally {
            lock.readLock().unlock();
          }
          if (queue.canBeExpectedToBeExecuteInInterval(
              timeResources - (System.currentTimeMillis() - start))) {
            if (queue.executedCommand()) {
              executedAnyCommand = true;
            }
          } else {

            //higher priority is assigned
            if (!queuesForExecution.contains(queue)) {
              toExecute.add(queue);
              queuesForExecution.add(queue);
            }
          }
        }
        if (!executedAnyCommand || !hasTimeRemaining) {
          break;
        }
      }
    }
  }

  @Override
  public void addCommand(Command<?> command) {
    Queue queue;

    //is queue to handle this type present
    try {
      lock.readLock().lock();
      queue = queues.get(command.getCommandType());
    } finally {
      lock.readLock().unlock();
    }

    //create new queue to handle new types of commands
    if (queue == null) {
      try {
        lock.writeLock().lock();
        queue = new Queue(command.getCommandType());
        queues.put(queue.commandType, queue);
        orderOfQueues.add(queue);
      } finally {
        lock.writeLock().unlock();
      }
    }
    queue.addCommand(command);
  }

  /**
   * Maintains queue for command type
   */
  @EqualsAndHashCode(of = "commandType")
  private static class Queue {

    private final CommandType commandType;
    private final Object queueMonitor = new Object();
    private final LinkedList<Command<?>> commands = new LinkedList<>();
    private long averageExecution = 0;
    private int countOfExecutions = 0;

    Queue(CommandType commandType) {
      this.commandType = commandType;
    }

    void addCommand(Command<?> command) {
      synchronized (queueMonitor) {
        commands.add(command);
      }
    }

    boolean canBeExpectedToBeExecuteInInterval(long interval) {
      return interval > averageExecution;
    }

    boolean executedCommand() {
      Optional<Command<?>> command;
      synchronized (queueMonitor) {
        command = Optional.ofNullable(commands.pollFirst());
      }
      if (command.isPresent()) {

        //execute command and update average execution time
        long start = System.currentTimeMillis(), duration;
        command.get().execute();
        duration = System.currentTimeMillis() - start;
        averageExecution =
            ((averageExecution * countOfExecutions) + duration) / (countOfExecutions + 1);
        countOfExecutions++;
        return true;
      } else {
        return false;
      }
    }

  }

}
