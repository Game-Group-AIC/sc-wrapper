package gg.fel.cvut.cz.facades.queue.implementation;

import gg.fel.cvut.cz.facades.IQueueManager;
import gg.fel.cvut.cz.facades.queue.Command;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.EqualsAndHashCode;

/**
 * Manages queued items - to order them by type that each type has fair chance that its commands
 * will be executed
 */
public class QueueManager implements IQueueManager {

  private final Map<CommandType, MyQueue> queues = new HashMap<>();
  private final List<MyQueue> orderOfMyQueues = new ArrayList<>();
  private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
  private List<MyQueue> toExecute = new ArrayList<>();

  @Override
  public void executeCommands(long timeResources) {
    long start = System.currentTimeMillis();
    boolean executedAnyCommand, hasTimeRemaining = true;

    //try to execute previous requests
    List<MyQueue> toExecuteNext = new ArrayList<>();

    //execute at least one from previous request
    if (!toExecute.isEmpty()) {
      toExecute.remove(0).executedCommand();
    }

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
    Set<MyQueue> queuesToSkip = new HashSet<>(toExecuteNext);

    //execute new requests if there is still time
    if (hasTimeRemaining && timeResources - (System.currentTimeMillis() - start) > 0) {
      int queues;
      try {
        lock.readLock().lock();
        queues = orderOfMyQueues.size();
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
          MyQueue myQueue;
          try {
            lock.readLock().lock();
            myQueue = orderOfMyQueues.get(i);
          } finally {
            lock.readLock().unlock();
          }
          if (myQueue.canBeExpectedToBeExecuteInInterval(
              timeResources - (System.currentTimeMillis() - start))) {
            if (myQueue.executedCommand()) {
              executedAnyCommand = true;
            }
          } else {

            //higher priority is assigned
            if (!queuesToSkip.contains(myQueue)) {
              toExecute.add(myQueue);
              queuesToSkip.add(myQueue);
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
  public void addCommand(Command command) {
    MyQueue myQueue;

    //is myQueue to handle this type present
    try {
      lock.readLock().lock();
      myQueue = queues.get(command.getCommandType());
    } finally {
      lock.readLock().unlock();
    }

    //create new myQueue to handle new types of commands
    if (myQueue == null) {
      try {
        lock.writeLock().lock();
        myQueue = new MyQueue(command.getCommandType());
        queues.put(myQueue.commandType, myQueue);
        orderOfMyQueues.add(myQueue);
      } finally {
        lock.writeLock().unlock();
      }
    }
    myQueue.addCommand(command);
  }

  /**
   * Maintains queue for command type
   */
  @EqualsAndHashCode(of = "commandType")
  private static class MyQueue {

    private final CommandType commandType;
    private final Object queueMonitor = new Object();
    private final Queue<Command> commands = new LinkedList<>();
    private long averageExecution = 0;
    private int countOfExecutions = 0;

    MyQueue(CommandType commandType) {
      this.commandType = commandType;
    }

    void addCommand(Command command) {
      synchronized (queueMonitor) {
        commands.add(command);
      }
    }

    boolean canBeExpectedToBeExecuteInInterval(long interval) {
      return interval > averageExecution;
    }

    boolean executedCommand() {
      Optional<Command> command;
      synchronized (queueMonitor) {
        command = Optional.ofNullable(commands.poll());
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
      }
      return false;
    }

  }

}
