package gg.fel.cvut.cz.facades.managers;

import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.BulletType;
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
import gg.fel.cvut.cz.data.updatable.UpdatableBaseLocation;
import gg.fel.cvut.cz.data.updatable.UpdatableBullet;
import gg.fel.cvut.cz.data.updatable.UpdatableBulletType;
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
import gg.fel.cvut.cz.data.updatable.UpdatableWeaponType;
import gg.fel.cvut.cz.facades.IUpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.WBullet;
import gg.fel.cvut.cz.wrappers.WBulletType;
import gg.fel.cvut.cz.wrappers.WChokePoint;
import gg.fel.cvut.cz.wrappers.WGame;
import gg.fel.cvut.cz.wrappers.WPlayer;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.WRace;
import gg.fel.cvut.cz.wrappers.WRegion;
import gg.fel.cvut.cz.wrappers.WTechType;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import gg.fel.cvut.cz.wrappers.WUnit;
import gg.fel.cvut.cz.wrappers.WUnitType;
import gg.fel.cvut.cz.wrappers.WUpgradeType;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Manages updates
 */
//TODO implement methods
public class UpdateManager extends BWDataFacade<BWCounter> implements IUpdateManager {

  private final Updater<WBullet, Bullet, UpdatableBullet> bulletUpdater = new Updater<>(
      instance -> new UpdatableBullet(bwCounter, instance), this);
  private final Updater<WBaseLocation, BaseLocation, UpdatableBaseLocation> baseLocationUpdater = new Updater<>(
      instance -> new UpdatableBaseLocation(bwCounter, instance), this);
  private final Updater<WBulletType, BulletType, UpdatableBulletType> bulletTypeUpdater = new Updater<>(
      instance -> new UpdatableBulletType(bwCounter, instance), this);
  private final Updater<WChokePoint, ChokePoint, UpdatableChokePoint> chokePointUpdater = new Updater<>(
      instance -> new UpdatableChokePoint(bwCounter, instance), this);
  private final Updater<WGame, Game, UpdatableGame> gameUpdater = new Updater<>(
      instance -> new UpdatableGame(bwCounter, instance), this);
  private final Updater<WPlayer, Player, UpdatablePlayer> playerUpdater = new Updater<>(
      instance -> new UpdatablePlayer(bwCounter, instance), this);
  private final Updater<WPosition, Position, UpdatablePosition> positionUpdater = new Updater<>(
      instance -> new UpdatablePosition(bwCounter, instance), this);
  private final Updater<WRace, Race, UpdatableRace> raceUpdater = new Updater<>(
      instance -> new UpdatableRace(bwCounter, instance), this);
  private final Updater<WRegion, Region, UpdatableRegion> regionUpdater = new Updater<>(
      instance -> new UpdatableRegion(bwCounter, instance), this);
  private final Updater<WTechType, TechType, UpdatableTechType> techTypeUpdater = new Updater<>(
      instance -> new UpdatableTechType(bwCounter, instance), this);
  private final Updater<WTilePosition, TilePosition, UpdatableTilePosition> tilePositionUpdater = new Updater<>(
      instance -> new UpdatableTilePosition(bwCounter, instance), this);
  private final Updater<WUnit, Unit, UpdatableUnit> unitUpdater = new Updater<>(
      instance -> new UpdatableUnit(bwCounter, instance), this);
  private final Updater<WUnitType, UnitType, UpdatableUnitType> unitTypeUpdater = new Updater<>(
      instance -> new UpdatableUnitType(bwCounter, instance), this);
  private final Updater<WUpgradeType, UpgradeType, UpdatableUpgradeType> upgradeTypeUpdater = new Updater<>(
      instance -> new UpdatableUpgradeType(bwCounter, instance), this);
  private final Updater<WWeaponType, WeaponType, UpdatableWeaponType> weaponTypeUpdater = new Updater<>(
      instance -> new UpdatableWeaponType(bwCounter, instance), this);


  UpdateManager(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public boolean update(Bullet bulletToUpdate, UpdateStrategy updateStrategy) {
    if (bulletToUpdate.shouldBeUpdated(updateStrategy, this, 0)) {
      update(bulletToUpdate, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(BaseLocation baseLocation, UpdateStrategy updateStrategy) {
    return false;
  }

  @Override
  public boolean update(ChokePoint chokePoint, UpdateStrategy updateStrategy) {
    return false;
  }

  @Override
  public boolean update(Position position, UpdateStrategy updateStrategy) {
    return false;
  }

  @Override
  public boolean update(Region region, UpdateStrategy updateStrategy) {
    return false;
  }

  @Override
  public boolean update(TilePosition tilePosition, UpdateStrategy updateStrategy) {
    return false;
  }

  @Override
  public int getDeltaUpdate(Bullet bullet) {
    return bulletUpdater.getDeltaUpdate(bullet, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(BaseLocation baseLocation) {
    return 0;
  }

  @Override
  public int getDeltaUpdate(ChokePoint chokePoint) {
    return 0;
  }

  @Override
  public int getDeltaUpdate(Position position) {
    return 0;
  }

  @Override
  public int getDeltaUpdate(Region region) {
    return 0;
  }

  @Override
  public int getDeltaUpdate(TilePosition tilePosition) {
    return 0;
  }

  /**
   * Update bullet
   */
  @Override
  public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    bulletUpdater.update(bulletToUpdate, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Position position, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public void update(TilePosition tilePosition, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public void update(BaseLocation baseLocation, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public void update(ChokePoint chokePoint, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public void update(Game game, UpdateStrategy updateStrategy, int depth, int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(Game game) {
    return 0;
  }

  @Override
  public void update(Player player, UpdateStrategy updateStrategy, int depth, int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(Player player) {
    return 0;
  }

  @Override
  public void update(Race race, UpdateStrategy updateStrategy, int depth, int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(Race race) {
    return 0;
  }

  @Override
  public void update(Region region, UpdateStrategy updateStrategy, int depth, int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(TechType techType) {
    return 0;
  }

  @Override
  public void update(TechType techType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(Unit unit) {
    return 0;
  }

  @Override
  public void update(Unit unit, UpdateStrategy updateStrategy, int depth, int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(UnitType unitType) {
    return 0;
  }

  @Override
  public void update(UnitType unitType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(WeaponType weaponType) {
    return 0;
  }

  @Override
  public void update(WeaponType weaponType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(UpgradeType upgradeType) {
    return 0;
  }

  @Override
  public void update(UpgradeType upgradeType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public int getDeltaUpdate(BulletType bulletType) {
    return 0;
  }

  @Override
  public void update(BulletType bulletType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public Stream<? extends AContainer> getAllContainers() {
    //TODO
    return Stream.empty();
//        return Stream.concat(bulletUpdater.getAllContainers(), );
  }

  @Override
  public Optional<Bullet> getDataContainer(WBullet bullet) {
    return bulletUpdater.getWrappedInstance(bullet);
  }

  @Override
  public Optional<WBullet> getBWInstance(Bullet container) {
    return bulletUpdater.getBWInstance(container);
  }

  @Override
  public Optional<BaseLocation> getDataContainer(WBaseLocation baseLocation) {
    return null;
  }

  @Override
  public Optional<WBaseLocation> getBWInstance(BaseLocation container) {
    return null;
  }

  @Override
  public Optional<BulletType> getDataContainer(WBulletType bulletType) {
    return null;
  }

  @Override
  public Optional<WBulletType> getBWInstance(BulletType container) {
    return null;
  }

  @Override
  public Optional<ChokePoint> getDataContainer(WChokePoint chokePoint) {
    return null;
  }

  @Override
  public Optional<WChokePoint> getBWInstance(ChokePoint container) {
    return null;
  }

  @Override
  public Optional<Game> getDataContainer(WGame game) {
    return null;
  }

  @Override
  public Optional<WGame> getBWInstance(Game container) {
    return null;
  }

  @Override
  public Optional<Player> getDataContainer(WPlayer player) {
    return null;
  }

  @Override
  public Optional<WPlayer> getBWInstance(Player container) {
    return null;
  }

  @Override
  public Optional<Position> getDataContainer(WPosition position) {
    return null;
  }

  @Override
  public Optional<WPosition> getBWInstance(Position container) {
    return null;
  }

  @Override
  public Optional<Race> getDataContainer(WRace race) {
    return null;
  }

  @Override
  public Optional<WRace> getBWInstance(Race container) {
    return null;
  }

  @Override
  public Optional<Region> getDataContainer(WRegion region) {
    return null;
  }

  @Override
  public Optional<WRegion> getBWInstance(Region container) {
    return null;
  }

  @Override
  public Optional<TechType> getDataContainer(WTechType techType) {
    return null;
  }

  @Override
  public Optional<WTechType> getBWInstance(TechType container) {
    return null;
  }

  @Override
  public Optional<TilePosition> getDataContainer(WTilePosition tilePosition) {
    return null;
  }

  @Override
  public Optional<WTilePosition> getBWInstance(TilePosition container) {
    return null;
  }

  @Override
  public Optional<Unit> getDataContainer(WUnit unit) {
    return null;
  }

  @Override
  public Optional<WUnit> getBWInstance(Unit container) {
    return null;
  }

  @Override
  public Optional<UnitType> getDataContainer(WUnitType unitType) {
    return null;
  }

  @Override
  public Optional<WUnitType> getBWInstance(UnitType container) {
    return null;
  }

  @Override
  public Optional<UpgradeType> getDataContainer(WUpgradeType upgradeType) {
    return null;
  }

  @Override
  public Optional<WUpgradeType> getBWInstance(UpgradeType container) {
    return null;
  }

  @Override
  public Optional<WeaponType> getDataContainer(WWeaponType weaponType) {
    return null;
  }

  @Override
  public Optional<WWeaponType> getBWInstance(WeaponType container) {
    return null;
  }
}
