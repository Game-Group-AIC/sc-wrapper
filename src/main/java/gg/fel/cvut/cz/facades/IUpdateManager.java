package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.data.readonly.TechType;
import gg.fel.cvut.cz.data.readonly.Unit;
import gg.fel.cvut.cz.data.readonly.UnitType;
import gg.fel.cvut.cz.data.readonly.UpgradeType;
import gg.fel.cvut.cz.data.readonly.WeaponType;
import gg.fel.cvut.cz.data.updatable.UpdatableBaseLocation;
import gg.fel.cvut.cz.data.updatable.UpdatableBullet;
import gg.fel.cvut.cz.data.updatable.UpdatableChokePoint;
import gg.fel.cvut.cz.data.updatable.UpdatableGame;
import gg.fel.cvut.cz.data.updatable.UpdatablePlayer;
import gg.fel.cvut.cz.data.updatable.UpdatablePosition;
import gg.fel.cvut.cz.data.updatable.UpdatableRace;
import gg.fel.cvut.cz.data.updatable.UpdatableRegion;
import gg.fel.cvut.cz.data.updatable.UpdatableTechType;
import gg.fel.cvut.cz.data.updatable.UpdatableTilePosition;
import gg.fel.cvut.cz.data.updatable.UpdatableUnit;
import gg.fel.cvut.cz.data.updatable.UpdatableUnitType;
import gg.fel.cvut.cz.data.updatable.UpdatableUpgradeType;
import gg.fel.cvut.cz.data.updatable.UpdatableWalkPosition;
import gg.fel.cvut.cz.data.updatable.UpdatableWeaponType;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Contract of UpdateManager (updatable methods)
 */
public interface IUpdateManager extends IGameDataWrapper {

  Optional<UpdatableGame> getGame();

  Stream<Unit> getUnits();

  Stream<Bullet> getBullets();

  Stream<Race> getRaces();

  Stream<TechType> getTechTypes();

  Stream<UpgradeType> getUpgradeTypes();

  Stream<UnitType> getUnitTypes();

  Stream<WeaponType> getWeaponTypes();

  /**
   * Method to be called preferably at the beginning of the game to init game instance
   */
  Optional<UpdatableGame> setGame(bwapi.Game game);

  /**
   * Method to be called preferably at the beginning of the game to init all types
   */
  void initializeAllTypes(UpdateStrategy updateStrategy);

  /**
   * Update bullet
   */
  boolean update(UpdatableBullet bulletToUpdate, UpdateStrategy updateStrategy);

  /**
   * Update baseLocation
   */
  boolean update(UpdatableBaseLocation baseLocation, UpdateStrategy updateStrategy);

  /**
   * Update chokePoint
   */
  boolean update(UpdatableChokePoint chokePoint, UpdateStrategy updateStrategy);

  /**
   * Update position
   */
  boolean update(UpdatablePosition position, UpdateStrategy updateStrategy);

  /**
   * Update region
   */
  boolean update(UpdatableRegion region, UpdateStrategy updateStrategy);

  /**
   * Update tilePosition
   */
  boolean update(UpdatableTilePosition tilePosition, UpdateStrategy updateStrategy);

  /**
   * Returns are containers associated with this updater
   */
  Stream<? extends AContainer> getAllContainers();

  /**
   * Update game
   */
  boolean update(UpdatableGame game, UpdateStrategy updateStrategy);

  /**
   * Update player
   */
  boolean update(UpdatablePlayer player, UpdateStrategy updateStrategy);

  /**
   * Update race
   */
  boolean update(UpdatableRace race, UpdateStrategy updateStrategy);

  /**
   * Update techType
   */
  boolean update(UpdatableTechType techType, UpdateStrategy updateStrategy);

  /**
   * Update unit
   */
  boolean update(UpdatableUnit unit, UpdateStrategy updateStrategy);

  /**
   * Update unitType
   */
  boolean update(UpdatableUnitType unitType, UpdateStrategy updateStrategy);

  /**
   * Update upgradeType
   */
  boolean update(UpdatableUpgradeType upgradeType, UpdateStrategy updateStrategy);

  /**
   * Update weaponType
   */
  boolean update(UpdatableWeaponType weaponType, UpdateStrategy updateStrategy);

  /**
   * Update walkPosition
   */
  boolean update(UpdatableWalkPosition walkPosition, UpdateStrategy updateStrategy);

}
