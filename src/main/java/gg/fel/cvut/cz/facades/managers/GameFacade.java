package gg.fel.cvut.cz.facades.managers;

import bwapi.DefaultBWListener;
import bwapi.Mirror;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.counters.IBWClock;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.facades.IGameDataUpdateAdapter;
import gg.fel.cvut.cz.facades.queue.CommandType;
import gg.fel.cvut.cz.facades.queue.CommandWithResponse;
import gg.fel.cvut.cz.facades.queue.CommandWithoutResponse;
import gg.fel.cvut.cz.facades.queue.IResponseReceiver;
import gg.fel.cvut.cz.facades.queue.QueueManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBullet;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Allows user to access game instances, update them and to command game
 */
//TODO builder with subscribers and writers on bw events returning wrapped objects
@Slf4j
public class GameFacade extends DefaultBWListener implements IGameDataUpdateAdapter,
    IBWClock, Runnable {

  private final BWCounter bwCounter = new BWCounter();
  private final UpdateManager updaterFacade = new UpdateManager(bwCounter);
  private final QueueManager queueManager = new QueueManager();

  //command types
  private static final CommandType BULLET_UPDATE = CommandType.getCommandType("BULLET_UPDATE");

  @Getter
  @Setter
  private long frameExecutionTime;

  //game related fields
  private Mirror mirror = new Mirror();

  public GameFacade(long frameExecutionTime) {
    this.frameExecutionTime = frameExecutionTime;
  }

  //TODO on start - init game, map,...

  @Override
  public void run() {
    try {
      mirror.getModule().setEventListener(this);
      mirror.startGame();
    } catch (Exception e) {
      //Catch any exception that occur not to "kill" the bot with one trivial error
      log.error(e.getMessage());
//      e.printStackTrace();
    }
  }

  @Override
  public void onFrame() {
    try {
      queueManager.executedCommands(frameExecutionTime);
    } catch (Exception e) {
      //Catch any exception that occur not to "kill" the bot with one trivial error
      log.error(e.getMessage());
//      e.printStackTrace();
    }
    bwCounter.increaseClocks();
  }

  @Override
  public Optional<Bullet> getDataContainer(WBullet bullet) {
    return updaterFacade.getDataContainer(bullet);
  }

  @Override
  public Optional<WBullet> getBWInstance(Bullet container) {
    return updaterFacade.getBWInstance(container);
  }

  @Override
  public void sendCommandToGame(CommandWithResponse commandWithResponse) {
    queueManager.addCommand(commandWithResponse);
  }

  @Override
  public void sendCommandToGame(CommandWithoutResponse commandWithoutResponse) {
    queueManager.addCommand(commandWithoutResponse);
  }

  @Override
  public int getCurrentFrame() {
    return bwCounter.getCurrentFrame();
  }

  @Override
  public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(BULLET_UPDATE,
        () -> updaterFacade.update(bulletToUpdate, updateStrategy)));
  }

  @Override
  public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(BULLET_UPDATE, responseReceiver,
        () -> updaterFacade.update(bulletToUpdate, updateStrategy)));
  }

  @Override
  public Stream<? extends AContainer> getAllGameInstances() {
    return updaterFacade.getAllContainers();
  }
}
