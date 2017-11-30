package gg.fel.cvut.cz.facades;

import bwapi.BWEventListener;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.facades.managers.ReplayGameFacade;
import gg.fel.cvut.cz.facades.queue.CommandWithResponse;
import gg.fel.cvut.cz.facades.queue.CommandWithoutResponse;
import gg.fel.cvut.cz.facades.queue.IResponseReceiver;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;

//TODO specific interfaces to command units

/**
 * Contract for facade allowing user to access game instances, update them and to command game
 */
public interface IGameDataUpdateAdapter extends IGameDataAccessAdapter, BWEventListener,
    IGameDataWrapper {

  /**
   * Returns current game as replay so it can be serialized
   */
  ReplayGameFacade getGameAsReplay();

  void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy);

  /**
   * Sends command to game. When command is executed response is returned in asynchronous fashion
   */
  void sendCommandToGame(CommandWithResponse commandWithResponse);

  /**
   * Sends command to game. No response is returned
   */
  void sendCommandToGame(CommandWithoutResponse commandWithoutResponse);

  /**
   * Updates all instances
   */
  void updateAll();

  //TODO rest of the methods

}
