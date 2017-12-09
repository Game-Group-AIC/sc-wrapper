package gg.fel.cvut.cz.facades.managers;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.counters.IBWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
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
import gg.fel.cvut.cz.data.readonly.WalkPosition;
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
import gg.fel.cvut.cz.facades.IUpdateManager;
import gg.fel.cvut.cz.facades.data.UpdateTreeExecutor;
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
import gg.fel.cvut.cz.wrappers.WWalkPosition;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.Getter;

/**
 * Manages updates
 */
public class UpdateManager implements IUpdateManager, IBWCounter {

  @Getter
  private final BWReplayCounter bwCounter = new BWReplayCounter();
  @Getter
  private Optional<UpdatableGame> game = Optional.empty();
  @Getter
  private Optional<UpdatablePlayer> self = Optional.empty();

  private final ContainerRegister<WBullet, Bullet, UpdatableBullet> bulletUpdater = new ContainerRegister<WBullet, Bullet, UpdatableBullet>(
      instance -> new UpdatableBullet(bwCounter, instance));
  private final ContainerRegister<WBaseLocation, BaseLocation, UpdatableBaseLocation> baseLocationUpdater = new ContainerRegister<WBaseLocation, BaseLocation, UpdatableBaseLocation>(
      instance -> new UpdatableBaseLocation(bwCounter, instance));
  private final ContainerRegister<WChokePoint, ChokePoint, UpdatableChokePoint> chokePointUpdater = new ContainerRegister<WChokePoint, ChokePoint, UpdatableChokePoint>(
      instance -> new UpdatableChokePoint(bwCounter, instance));
  private final ContainerRegister<WGame, Game, UpdatableGame> gameUpdater = new ContainerRegister<WGame, Game, UpdatableGame>(
      instance -> new UpdatableGame(bwCounter, instance));
  private final ContainerRegister<WPlayer, Player, UpdatablePlayer> playerUpdater = new ContainerRegister<WPlayer, Player, UpdatablePlayer>(
      instance -> new UpdatablePlayer(bwCounter, instance));
  private final ContainerRegister<WPosition, Position, UpdatablePosition> positionUpdater = new ContainerRegister<WPosition, Position, UpdatablePosition>(
      instance -> new UpdatablePosition(bwCounter, instance));
  private final ContainerRegister<WRace, Race, UpdatableRace> raceUpdater = new ContainerRegister<WRace, Race, UpdatableRace>(
      instance -> new UpdatableRace(bwCounter, instance));
  private final ContainerRegister<WRegion, Region, UpdatableRegion> regionUpdater = new ContainerRegister<WRegion, Region, UpdatableRegion>(
      instance -> new UpdatableRegion(bwCounter, instance));
  private final ContainerRegister<WTechType, TechType, UpdatableTechType> techTypeUpdater = new ContainerRegister<WTechType, TechType, UpdatableTechType>(
      instance -> new UpdatableTechType(bwCounter, instance));
  private final ContainerRegister<WTilePosition, TilePosition, UpdatableTilePosition> tilePositionUpdater = new ContainerRegister<WTilePosition, TilePosition, UpdatableTilePosition>(
      instance -> new UpdatableTilePosition(bwCounter, instance));
  private final ContainerRegister<WUnit, Unit, UpdatableUnit> unitUpdater = new ContainerRegister<WUnit, Unit, UpdatableUnit>(
      instance -> new UpdatableUnit(bwCounter, instance));
  private final ContainerRegister<WUnitType, UnitType, UpdatableUnitType> unitTypeUpdater = new ContainerRegister<WUnitType, UnitType, UpdatableUnitType>(
      instance -> new UpdatableUnitType(bwCounter, instance));
  private final ContainerRegister<WUpgradeType, UpgradeType, UpdatableUpgradeType> upgradeTypeUpdater = new ContainerRegister<WUpgradeType, UpgradeType, UpdatableUpgradeType>(
      instance -> new UpdatableUpgradeType(bwCounter, instance));
  private final ContainerRegister<WWeaponType, WeaponType, UpdatableWeaponType> weaponTypeUpdater = new ContainerRegister<WWeaponType, WeaponType, UpdatableWeaponType>(
      instance -> new UpdatableWeaponType(bwCounter, instance));
  private final ContainerRegister<WWalkPosition, WalkPosition, UpdatableWalkPosition> walkPositionUpdater = new ContainerRegister<WWalkPosition, WalkPosition, UpdatableWalkPosition>(
      instance -> new UpdatableWalkPosition(bwCounter, instance));

  @Override
  public Stream<Unit> getUnits() {
    return unitUpdater.getAllContainers();
  }

  @Override
  public Stream<Bullet> getBullets() {
    return bulletUpdater.getAllContainers();
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
  public Optional<UpdatableGame> setGame(bwapi.Game game) {
    this.game = gameUpdater.getWrappedInstance(WGame.getOrCreateWrapper(game));
    this.self = playerUpdater.getWrappedInstance(WPlayer.getOrCreateWrapper(game.self()));
    return this.game;
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
        .filter(o -> o instanceof IUpdatableContainer<?, ?>)
        .forEach(o -> ((IUpdatableContainer<?, ?>) o).update(this, 0));
  }

  @Override
  public boolean update(UpdatableBullet bulletToUpdate, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor
        .executeUpdate(bulletToUpdate, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public Stream<? extends AContainer> getAllContainers() {
    return Stream.of(baseLocationUpdater.getAllContainers(), bulletUpdater.getAllContainers(),
        gameUpdater.getAllContainers(), playerUpdater.getAllContainers(),
        walkPositionUpdater.getAllContainers(), positionUpdater.getAllContainers(),
        raceUpdater.getAllContainers(), regionUpdater.getAllContainers(),
        techTypeUpdater.getAllContainers(), tilePositionUpdater.getAllContainers(),
        unitUpdater.getAllContainers(), unitTypeUpdater.getAllContainers(),
        upgradeTypeUpdater.getAllContainers(), weaponTypeUpdater.getAllContainers())
        .flatMap(stream -> stream);
  }

  @Override
  public boolean update(UpdatableBaseLocation baseLocation, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor
        .executeUpdate(baseLocation, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableChokePoint chokePoint, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(chokePoint, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableGame game, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(game, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatablePlayer player, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(player, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatablePosition position, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(position, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableRace race, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(race, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableRegion region, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(region, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableTechType techType, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(techType, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableTilePosition tilePosition, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor
        .executeUpdate(tilePosition, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableUnit unit, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(unit, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableUnitType unitType, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(unitType, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableUpgradeType upgradeType, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor
        .executeUpdate(upgradeType, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableWeaponType weaponType, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor.executeUpdate(weaponType, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public boolean update(UpdatableWalkPosition walkPosition, UpdateStrategy updateStrategy) {
    UpdateTreeExecutor
        .executeUpdate(walkPosition, updateStrategy, bwCounter.getCurrentFrame(), this);
    return true;
  }

  @Override
  public Optional<Bullet> getDataContainer(WBullet bullet) {
    return bulletUpdater.getWrappedInstance(bullet).map(o -> o);
  }

  @Override
  public Optional<BaseLocation> getDataContainer(WBaseLocation baseLocation) {
    return baseLocationUpdater.getWrappedInstance(baseLocation).map(o -> o);
  }

  @Override
  public Optional<ChokePoint> getDataContainer(WChokePoint chokePoint) {
    return chokePointUpdater.getWrappedInstance(chokePoint).map(o -> o);
  }

  @Override
  public Optional<Player> getDataContainer(WPlayer player) {
    return playerUpdater.getWrappedInstance(player).map(o -> o);
  }

  @Override
  public Optional<Position> getDataContainer(WPosition position) {
    return positionUpdater.getWrappedInstance(position).map(o -> o);
  }

  @Override
  public Optional<Race> getDataContainer(WRace race) {
    return raceUpdater.getWrappedInstance(race).map(o -> o);
  }

  @Override
  public Optional<Region> getDataContainer(WRegion region) {
    return regionUpdater.getWrappedInstance(region).map(o -> o);
  }

  @Override
  public Optional<TechType> getDataContainer(WTechType techType) {
    return techTypeUpdater.getWrappedInstance(techType).map(o -> o);
  }

  @Override
  public Optional<TilePosition> getDataContainer(WTilePosition tilePosition) {
    return tilePositionUpdater.getWrappedInstance(tilePosition).map(o -> o);
  }

  @Override
  public Optional<Unit> getDataContainer(WUnit unit) {
    return unitUpdater.getWrappedInstance(unit).map(o -> o);
  }

  @Override
  public Optional<UnitType> getDataContainer(WUnitType unitType) {
    return unitTypeUpdater.getWrappedInstance(unitType).map(o -> o);
  }

  @Override
  public Optional<UpgradeType> getDataContainer(WUpgradeType upgradeType) {
    return upgradeTypeUpdater.getWrappedInstance(upgradeType).map(o -> o);
  }

  @Override
  public Optional<WeaponType> getDataContainer(WWeaponType weaponType) {
    return weaponTypeUpdater.getWrappedInstance(weaponType).map(o -> o);
  }

  @Override
  public Optional<WalkPosition> getDataContainer(WWalkPosition walkPosition) {
    return walkPositionUpdater.getWrappedInstance(walkPosition).map(o -> o);
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
