package gg.fel.cvut.cz.facades.managers;

import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.counters.IBWCounter;
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
import gg.fel.cvut.cz.data.updatable.UpdatableWeaponType;
import gg.fel.cvut.cz.facades.IUpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.WBullet;
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
import lombok.Getter;

/**
 * Manages updates
 */
public class UpdateManager implements IUpdateManager, IBWCounter {

  @Getter
  private final BWCounter bwCounter = new BWCounter();

  private final Updater<WBullet, Bullet, UpdatableBullet> bulletUpdater = new Updater<WBullet, Bullet, UpdatableBullet>(
      instance -> new UpdatableBullet(bwCounter, instance), this);
  private final Updater<WBaseLocation, BaseLocation, UpdatableBaseLocation> baseLocationUpdater = new Updater<WBaseLocation, BaseLocation, UpdatableBaseLocation>(
      instance -> new UpdatableBaseLocation(bwCounter, instance), this);
  private final Updater<WChokePoint, ChokePoint, UpdatableChokePoint> chokePointUpdater = new Updater<WChokePoint, ChokePoint, UpdatableChokePoint>(
      instance -> new UpdatableChokePoint(bwCounter, instance), this);
  private final Updater<WGame, Game, UpdatableGame> gameUpdater = new Updater<WGame, Game, UpdatableGame>(
      instance -> new UpdatableGame(bwCounter, instance), this);
  private final Updater<WPlayer, Player, UpdatablePlayer> playerUpdater = new Updater<WPlayer, Player, UpdatablePlayer>(
      instance -> new UpdatablePlayer(bwCounter, instance), this);
  private final Updater<WPosition, Position, UpdatablePosition> positionUpdater = new Updater<WPosition, Position, UpdatablePosition>(
      instance -> new UpdatablePosition(bwCounter, instance), this);
  private final Updater<WRace, Race, UpdatableRace> raceUpdater = new Updater<WRace, Race, UpdatableRace>(
      instance -> new UpdatableRace(bwCounter, instance), this);
  private final Updater<WRegion, Region, UpdatableRegion> regionUpdater = new Updater<WRegion, Region, UpdatableRegion>(
      instance -> new UpdatableRegion(bwCounter, instance), this);
  private final Updater<WTechType, TechType, UpdatableTechType> techTypeUpdater = new Updater<WTechType, TechType, UpdatableTechType>(
      instance -> new UpdatableTechType(bwCounter, instance), this);
  private final Updater<WTilePosition, TilePosition, UpdatableTilePosition> tilePositionUpdater = new Updater<WTilePosition, TilePosition, UpdatableTilePosition>(
      instance -> new UpdatableTilePosition(bwCounter, instance), this);
  private final Updater<WUnit, Unit, UpdatableUnit> unitUpdater = new Updater<WUnit, Unit, UpdatableUnit>(
      instance -> new UpdatableUnit(bwCounter, instance), this);
  private final Updater<WUnitType, UnitType, UpdatableUnitType> unitTypeUpdater = new Updater<WUnitType, UnitType, UpdatableUnitType>(
      instance -> new UpdatableUnitType(bwCounter, instance), this);
  private final Updater<WUpgradeType, UpgradeType, UpdatableUpgradeType> upgradeTypeUpdater = new Updater<WUpgradeType, UpgradeType, UpdatableUpgradeType>(
      instance -> new UpdatableUpgradeType(bwCounter, instance), this);
  private final Updater<WWeaponType, WeaponType, UpdatableWeaponType> weaponTypeUpdater = new Updater<WWeaponType, WeaponType, UpdatableWeaponType>(
      instance -> new UpdatableWeaponType(bwCounter, instance), this);

  @Override
  public Stream<Unit> getUnits() {
    return unitUpdater.getAllContainers().filter(unit -> unit.exists().orElse(false));
  }

  @Override
  public Stream<Bullet> getBullets() {
    return bulletUpdater.getAllContainers().filter(unit -> unit.exists().orElse(false));
  }

  @Override
  public Stream<Race> getRaces() {
    return raceUpdater.getAllContainers();
  }

  @Override
  public Stream<TechType> getTechTypes() {
    return techTypeUpdater.getAllContainers();
  }

  @Override
  public Stream<UpgradeType> getUpgradeTypes() {
    return upgradeTypeUpdater.getAllContainers();
  }

  @Override
  public Stream<UnitType> getUnitTypes() {
    return unitTypeUpdater.getAllContainers();
  }

  @Override
  public Stream<WeaponType> getWeaponTypes() {
    return weaponTypeUpdater.getAllContainers();
  }

  @Override
  public Optional<Game> wrapGame(bwapi.Game game) {
    return gameUpdater.getWrappedInstance(WGame.getOrCreateWrapper(game));
  }

  @Override
  public void initializeAllTypes(UpdateStrategy updateStrategy) {
    Stream.of(WRace.getAllWrappedTypes().map(this::getDataContainer),
        WTechType.getAllWrappedTypes().map(this::getDataContainer),
        WUnitType.getAllWrappedTypes().map(this::getDataContainer),
        WUpgradeType.getAllWrappedTypes().map(this::getDataContainer),
        WWeaponType.getAllWrappedTypes().map(this::getDataContainer))
        .flatMap(stream -> stream)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, 0))
        .forEach(o -> o.update(updateStrategy, this, 0, bwCounter.getCurrentFrame()));
  }

  @Override
  public int getDeltaUpdate(Bullet bullet) {
    return bulletUpdater.getDeltaUpdate(bullet, bwCounter.getCurrentFrame());
  }

  @Override
  public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    bulletUpdater.update(bulletToUpdate, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public Stream<? extends AContainer> getAllContainers() {
    return Stream.of(baseLocationUpdater.getAllContainers(), bulletUpdater.getAllContainers(),
        gameUpdater.getAllContainers(), playerUpdater.getAllContainers(),
        positionUpdater.getAllContainers(), raceUpdater.getAllContainers(),
        regionUpdater.getAllContainers(), techTypeUpdater.getAllContainers(),
        tilePositionUpdater.getAllContainers(), unitUpdater.getAllContainers(),
        unitTypeUpdater.getAllContainers(), upgradeTypeUpdater.getAllContainers(),
        weaponTypeUpdater.getAllContainers()).flatMap(stream -> stream);
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
  public boolean update(BaseLocation baseLocation, UpdateStrategy updateStrategy) {
    if (baseLocation.shouldBeUpdated(updateStrategy, this, 0)) {
      update(baseLocation, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(Bullet bullet, UpdateStrategy updateStrategy) {
    if (bullet.shouldBeUpdated(updateStrategy, this, 0)) {
      update(bullet, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(ChokePoint chokePoint, UpdateStrategy updateStrategy) {
    if (chokePoint.shouldBeUpdated(updateStrategy, this, 0)) {
      update(chokePoint, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(Game game, UpdateStrategy updateStrategy) {
    if (game.shouldBeUpdated(updateStrategy, this, 0)) {
      update(game, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(Player player, UpdateStrategy updateStrategy) {
    if (player.shouldBeUpdated(updateStrategy, this, 0)) {
      update(player, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(Position position, UpdateStrategy updateStrategy) {
    if (position.shouldBeUpdated(updateStrategy, this, 0)) {
      update(position, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(Race race, UpdateStrategy updateStrategy) {
    if (race.shouldBeUpdated(updateStrategy, this, 0)) {
      update(race, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(Region region, UpdateStrategy updateStrategy) {
    if (region.shouldBeUpdated(updateStrategy, this, 0)) {
      update(region, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(TechType techType, UpdateStrategy updateStrategy) {
    if (techType.shouldBeUpdated(updateStrategy, this, 0)) {
      update(techType, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(TilePosition tilePosition, UpdateStrategy updateStrategy) {
    if (tilePosition.shouldBeUpdated(updateStrategy, this, 0)) {
      update(tilePosition, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(Unit unit, UpdateStrategy updateStrategy) {
    if (unit.shouldBeUpdated(updateStrategy, this, 0)) {
      update(unit, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(UnitType unitType, UpdateStrategy updateStrategy) {
    if (unitType.shouldBeUpdated(updateStrategy, this, 0)) {
      update(unitType, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(UpgradeType upgradeType, UpdateStrategy updateStrategy) {
    if (upgradeType.shouldBeUpdated(updateStrategy, this, 0)) {
      update(upgradeType, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public boolean update(WeaponType weaponType, UpdateStrategy updateStrategy) {
    if (weaponType.shouldBeUpdated(updateStrategy, this, 0)) {
      update(weaponType, updateStrategy, 0, bwCounter.getCurrentFrame());
    }
    return true;
  }

  @Override
  public void update(BaseLocation baseLocation, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    baseLocationUpdater.update(baseLocation, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(ChokePoint chokePoint, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    chokePointUpdater.update(chokePoint, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Game game, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    gameUpdater.update(game, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Player player, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    playerUpdater.update(player, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Position position, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    positionUpdater.update(position, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Race race, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    raceUpdater.update(race, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Region region, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    regionUpdater.update(region, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(TechType techType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    techTypeUpdater.update(techType, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(TilePosition tilePosition, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    tilePositionUpdater.update(tilePosition, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Unit unit, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    unitUpdater.update(unit, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(UnitType unitType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    unitTypeUpdater.update(unitType, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(UpgradeType upgradeType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    upgradeTypeUpdater.update(upgradeType, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(WeaponType weaponType, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    weaponTypeUpdater.update(weaponType, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public int getDeltaUpdate(BaseLocation baseLocation) {
    return baseLocationUpdater.getDeltaUpdate(baseLocation, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(ChokePoint chokePoint) {
    return chokePointUpdater.getDeltaUpdate(chokePoint, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(Game game) {
    return gameUpdater.getDeltaUpdate(game, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(Player player) {
    return playerUpdater.getDeltaUpdate(player, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(Position position) {
    return positionUpdater.getDeltaUpdate(position, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(Race race) {
    return raceUpdater.getDeltaUpdate(race, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(Region region) {
    return regionUpdater.getDeltaUpdate(region, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(TechType techType) {
    return techTypeUpdater.getDeltaUpdate(techType, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(TilePosition tilePosition) {
    return tilePositionUpdater.getDeltaUpdate(tilePosition, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(Unit unit) {
    return unitUpdater.getDeltaUpdate(unit, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(UnitType unitType) {
    return unitTypeUpdater.getDeltaUpdate(unitType, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(UpgradeType upgradeType) {
    return upgradeTypeUpdater.getDeltaUpdate(upgradeType, bwCounter.getCurrentFrame());
  }

  @Override
  public int getDeltaUpdate(WeaponType weaponType) {
    return weaponTypeUpdater.getDeltaUpdate(weaponType, bwCounter.getCurrentFrame());
  }

  @Override
  public Optional<BaseLocation> getDataContainer(WBaseLocation baseLocation) {
    return baseLocationUpdater.getWrappedInstance(baseLocation);
  }

  @Override
  public Optional<WBaseLocation> getBWInstance(BaseLocation container) {
    return baseLocationUpdater.getBWInstance(container);
  }

  @Override
  public Optional<ChokePoint> getDataContainer(WChokePoint chokePoint) {
    return chokePointUpdater.getWrappedInstance(chokePoint);
  }

  @Override
  public Optional<WChokePoint> getBWInstance(ChokePoint container) {
    return chokePointUpdater.getBWInstance(container);
  }

  @Override
  public Optional<Player> getDataContainer(WPlayer player) {
    return playerUpdater.getWrappedInstance(player);
  }

  @Override
  public Optional<WPlayer> getBWInstance(Player container) {
    return playerUpdater.getBWInstance(container);
  }

  @Override
  public Optional<Position> getDataContainer(WPosition position) {
    return positionUpdater.getWrappedInstance(position);
  }

  @Override
  public Optional<WPosition> getBWInstance(Position container) {
    return positionUpdater.getBWInstance(container);
  }

  @Override
  public Optional<Race> getDataContainer(WRace race) {
    return raceUpdater.getWrappedInstance(race);
  }

  @Override
  public Optional<WRace> getBWInstance(Race container) {
    return raceUpdater.getBWInstance(container);
  }

  @Override
  public Optional<Region> getDataContainer(WRegion region) {
    return regionUpdater.getWrappedInstance(region);
  }

  @Override
  public Optional<WRegion> getBWInstance(Region container) {
    return regionUpdater.getBWInstance(container);
  }

  @Override
  public Optional<TechType> getDataContainer(WTechType techType) {
    return techTypeUpdater.getWrappedInstance(techType);
  }

  @Override
  public Optional<WTechType> getBWInstance(TechType container) {
    return techTypeUpdater.getBWInstance(container);
  }

  @Override
  public Optional<TilePosition> getDataContainer(WTilePosition tilePosition) {
    return tilePositionUpdater.getWrappedInstance(tilePosition);
  }

  @Override
  public Optional<WTilePosition> getBWInstance(TilePosition container) {
    return tilePositionUpdater.getBWInstance(container);
  }

  @Override
  public Optional<Unit> getDataContainer(WUnit unit) {
    return unitUpdater.getWrappedInstance(unit);
  }

  @Override
  public Optional<WUnit> getBWInstance(Unit container) {
    return unitUpdater.getBWInstance(container);
  }

  @Override
  public Optional<UnitType> getDataContainer(WUnitType unitType) {
    return unitTypeUpdater.getWrappedInstance(unitType);
  }

  @Override
  public Optional<WUnitType> getBWInstance(UnitType container) {
    return unitTypeUpdater.getBWInstance(container);
  }

  @Override
  public Optional<UpgradeType> getDataContainer(WUpgradeType upgradeType) {
    return upgradeTypeUpdater.getWrappedInstance(upgradeType);
  }

  @Override
  public Optional<WUpgradeType> getBWInstance(UpgradeType container) {
    return upgradeTypeUpdater.getBWInstance(container);
  }

  @Override
  public Optional<WeaponType> getDataContainer(WWeaponType weaponType) {
    return weaponTypeUpdater.getWrappedInstance(weaponType);
  }

  @Override
  public Optional<WWeaponType> getBWInstance(WeaponType container) {
    return weaponTypeUpdater.getBWInstance(container);
  }

  @Override
  public void increaseClocks() {
    bwCounter.increaseClocks();
  }

  @Override
  public int getCurrentFrame() {
    return bwCounter.getCurrentFrame();
  }
}
