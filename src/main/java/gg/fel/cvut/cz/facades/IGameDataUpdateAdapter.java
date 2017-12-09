package gg.fel.cvut.cz.facades;

import bwapi.BWEventListener;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.data.readonly.Region;
import gg.fel.cvut.cz.data.readonly.TechType;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.data.readonly.Unit;
import gg.fel.cvut.cz.data.readonly.UnitType;
import gg.fel.cvut.cz.data.readonly.UpgradeType;
import gg.fel.cvut.cz.data.readonly.WeaponType;
import gg.fel.cvut.cz.facades.managers.ReplayGameFacade;
import gg.fel.cvut.cz.facades.queue.IResponseReceiver;
import gg.fel.cvut.cz.facades.queue.implementation.CommandWithResponse;
import gg.fel.cvut.cz.facades.queue.implementation.CommandWithoutResponse;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import java.util.Optional;

//TODO specific interfaces to command units

/**
 * Contract for facade allowing user to access game instances, update them and to command game
 */
public interface IGameDataUpdateAdapter extends IGameDataAccessAdapter, BWEventListener,
    IGameDataWrapper {

  /**
   * Returns current game as replay so it can be serialized
   */
  Optional<ReplayGameFacade> getGameAsReplay();

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
  void updateAll(UpdateStrategy updateStrategy, IResponseReceiver<Boolean> responseReceiver);

  /**
   * Updates all instances
   */
  void updateAll(UpdateStrategy updateStrategy);

  void update(BaseLocation baseLocation, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(BaseLocation baseLocation, UpdateStrategy updateStrategy);

  void update(ChokePoint chokePoint, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(ChokePoint chokePoint, UpdateStrategy updateStrategy);

  void updateGame(UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void updateGame(UpdateStrategy updateStrategy);

  void update(Player player, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(Player player, UpdateStrategy updateStrategy);

  void update(Position position, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(Position position, UpdateStrategy updateStrategy);

  void update(Race race, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(Race race, UpdateStrategy updateStrategy);

  void update(Region region, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(Region region, UpdateStrategy updateStrategy);

  void update(TechType techType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(TechType techType, UpdateStrategy updateStrategy);

  void update(TilePosition tilePosition, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(TilePosition tilePosition, UpdateStrategy updateStrategy);

  void update(Unit unit, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(Unit unit, UpdateStrategy updateStrategy);

  void update(UnitType unitType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(UnitType unitType, UpdateStrategy updateStrategy);

  void update(UpgradeType upgradeType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(UpgradeType upgradeType, UpdateStrategy updateStrategy);

  void update(WeaponType weaponType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> updateNotificationReceiver);

  void update(WeaponType weaponType, UpdateStrategy updateStrategy);

}
