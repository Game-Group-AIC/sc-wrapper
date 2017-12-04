package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.facades.queue.CommandType;
import gg.fel.cvut.cz.facades.queue.CommandWithResponse;
import gg.fel.cvut.cz.facades.queue.IResponseReceiver;
import gg.fel.cvut.cz.facades.queue.QueueManager;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestQueueManager {

  private static QueueManager queueManager = new QueueManager();
  private static final long duration = 30;
  private static final Receiver receiver = new Receiver();

  public static void main(String[] args) {
    List<Sender> senders = IntStream.range(0, 3).boxed()
        .map(value -> new Sender(CommandType.getCommandType(String.valueOf(value))))
        .collect(Collectors.toList());
    senders.forEach(Sender::start);
    receiver.start();
    while (true) {
      executeTurn();
    }
  }

  private static void executeTurn() {
    long start = System.currentTimeMillis();
    queueManager.executedCommands(duration);
    if (System.currentTimeMillis() - start > duration) {
      log.info("Duration was exceeded.");
    }
  }

  @AllArgsConstructor
  private static class Sender extends Thread {

    private final CommandType commandType;
    private final Random random = new Random();

    @Override
    public void run() {
      while (true) {
        queueManager.addCommand(new CommandWithResponse<>(commandType, receiver,
            () -> random.nextInt() + random.nextInt() >= random.nextInt() + random.nextInt()));
        try {
          Thread.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
          break;
        }
      }
    }
  }

  private static class Receiver extends Thread implements IResponseReceiver<Boolean> {

    private int count = 0;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    @Override
    public void receiveResponse(Boolean response) {
      try {
        lock.writeLock().lock();
        count++;
      } finally {
        lock.writeLock().unlock();
      }
    }

    @Override
    public void run() {
      int countedLast = 0;
      while (true) {
        int counted;
        try {
          lock.readLock().lock();
          counted = count;
        } finally {
          lock.readLock().unlock();
        }
        if (counted % 100 == 0 && countedLast != counted) {
          log.info("Next 100...");
          try {
            Thread.sleep(30);
          } catch (InterruptedException e) {
            e.printStackTrace();
            break;
          }
        }
        countedLast = counted;
      }
    }
  }


}
