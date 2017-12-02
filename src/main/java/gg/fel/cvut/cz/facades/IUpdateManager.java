package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.data.readonly.Game;
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
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Contract of UpdateManager (updatable methods)
 */
public interface IUpdateManager extends IGameDataWrapper {

  /**
   * Method to be called preferably at the beginning of the game to init game instance
   */
  Optional<Game> wrapGame(bwapi.Game game);

  /**
   * Method to be called preferably at the beginning of the game to init all types
   */
  void initializeAllTypes(UpdateStrategy updateStrategy);

  /**
   * Update bullet
   */
  boolean update(Bullet bulletToUpdate, UpdateStrategy updateStrategy);

  /**
   * Update baseLocation
   */
  boolean update(BaseLocation baseLocation, UpdateStrategy updateStrategy);

  /**
   * Update chokePoint
   */
  boolean update(ChokePoint chokePoint, UpdateStrategy updateStrategy);

  /**
   * Update position
   */
  boolean update(Position position, UpdateStrategy updateStrategy);

  /**
   * Update region
   */
  boolean update(Region region, UpdateStrategy updateStrategy);

  /**
   * Update tilePosition
   */
  boolean update(TilePosition tilePosition, UpdateStrategy updateStrategy);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Bullet bullet);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(BaseLocation baseLocation);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(ChokePoint chokePoint);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Position position);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Region region);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(TilePosition tilePosition);

  /**
   * Update bullet
   */
  void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy, int depth, int currentFrame);

  /**
   * Update position
   */
  void update(Position position, UpdateStrategy updateStrategy, int depth, int currentFrame);

  /**
   * Update baseLocation
   */
  void update(TilePosition tilePosition, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Update baseLocation
   */
  void update(BaseLocation baseLocation, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Update baseLocation
   */
  void update(ChokePoint chokePoint, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Update game
   */
  void update(Game game, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Game game);

  /**
   * Update player
   */
  void update(Player player, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Player player);

  /**
   * Update race
   */
  void update(Race race, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Race race);

  /**
   * Update region
   */
  void update(Region region, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(TechType techType);

  /**
   * Update techType
   */
  void update(TechType techType, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(Unit unit);

  /**
   * Update unit
   */
  void update(Unit unit, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(UnitType unitType);

  /**
   * Update unitType
   */
  void update(UnitType unitType, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(WeaponType weaponType);

  /**
   * Update weaponType
   */
  void update(WeaponType weaponType, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns delta of current time and last updated. If object has not been updated yet max integer
   * is returned. For object updated after current frame, zero is returned.
   */
  int getDeltaUpdate(UpgradeType upgradeType);

  /**
   * Update upgradeType
   */
  void update(UpgradeType upgradeType, UpdateStrategy updateStrategy, int depth,
      int currentFrame);

  /**
   * Returns are containers associated with this updater
   */
  Stream<? extends AContainer> getAllContainers();

  /**
   * Update game
   */
  boolean update(Game game, UpdateStrategy updateStrategy);

  /**
   * Update player
   */
  boolean update(Player player, UpdateStrategy updateStrategy);

  /**
   * Update race
   */
  boolean update(Race race, UpdateStrategy updateStrategy);

  /**
   * Update techType
   */
  boolean update(TechType techType, UpdateStrategy updateStrategy);

  /**
   * Update unit
   */
  boolean update(Unit unit, UpdateStrategy updateStrategy);

  /**
   * Update unitType
   */
  boolean update(UnitType unitType, UpdateStrategy updateStrategy);

  /**
   * Update upgradeType
   */
  boolean update(UpgradeType upgradeType, UpdateStrategy updateStrategy);

  /**
   * Update weaponType
   */
  boolean update(WeaponType weaponType, UpdateStrategy updateStrategy);

}
