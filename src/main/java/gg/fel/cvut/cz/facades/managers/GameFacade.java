package gg.fel.cvut.cz.facades.managers;

import bwapi.DefaultBWListener;
import bwapi.Mirror;
import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IChokePoint;
import gg.fel.cvut.cz.api.IGame;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.counters.IBWClock;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.events.UpdatableEventsRegister;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasEndedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasStartedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.INukeDetectedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IPlayerLeftNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IReceiveTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.ISendTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IUnitNotificationSubscriber;
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
import gg.fel.cvut.cz.enums.GameTypeEnum;
import gg.fel.cvut.cz.enums.RaceTypeEnum;
import gg.fel.cvut.cz.enums.TechTypeEnum;
import gg.fel.cvut.cz.enums.UnitTypeEnum;
import gg.fel.cvut.cz.enums.UpgradeTypeEnum;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import gg.fel.cvut.cz.facades.IGameDataAccessAdapter;
import gg.fel.cvut.cz.facades.IGameDataUpdateAdapter;
import gg.fel.cvut.cz.facades.queue.CommandType;
import gg.fel.cvut.cz.facades.queue.CommandWithResponse;
import gg.fel.cvut.cz.facades.queue.CommandWithoutResponse;
import gg.fel.cvut.cz.facades.queue.IResponseReceiver;
import gg.fel.cvut.cz.facades.queue.QueueManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.WBullet;
import gg.fel.cvut.cz.wrappers.WChokePoint;
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
import java.util.Set;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Allows user to access game instances, update them and to command game
 */
@Slf4j
@AllArgsConstructor
@Builder
public class GameFacade extends DefaultBWListener implements IGameDataUpdateAdapter,
    IBWClock, Runnable, IGame, IGameDataAccessAdapter {

  private final BWCounter bwCounter = new BWCounter();
  private final UpdateManager updateManager = new UpdateManager(bwCounter);
  private final QueueManager queueManager = new QueueManager();
  private final UpdatableEventsRegister eventsRegister = new UpdatableEventsRegister();
  private Optional<Game> game = Optional.empty();

  //event notification receivers
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitDiscover = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitEvade = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitShow = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitHide = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitCreate = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitDestroy = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitMorph = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitRenegade = Optional.empty();
  @Builder.Default
  private final Optional<IUnitNotificationSubscriber> onUnitComplete = Optional.empty();
  @Builder.Default
  private final Optional<IGameHasEndedNotificationSubscriber> onEnd = Optional.empty();
  @Builder.Default
  private final Optional<IGameHasStartedNotificationSubscriber> onStart = Optional.empty();
  @Builder.Default
  private final Optional<INukeDetectedNotificationSubscriber> onNukeDetect = Optional.empty();
  @Builder.Default
  private final Optional<IPlayerLeftNotificationSubscriber> onPlayerLeft = Optional.empty();
  @Builder.Default
  private final Optional<IReceiveTextNotificationSubscriber> onReceiveText = Optional.empty();
  @Builder.Default
  private final Optional<ISendTextNotificationSubscriber> onSendText = Optional.empty();

  //command types
  private static final CommandType BASE_LOCATION_UPDATE = CommandType
      .getCommandType("BASE_LOCATION_UPDATE");
  private static final CommandType BULLET_UPDATE = CommandType.getCommandType("BULLET_UPDATE");
  private static final CommandType BULLET_TYPE_UPDATE = CommandType
      .getCommandType("BULLET_TYPE_UPDATE");
  private static final CommandType CHOKE_POINT_UPDATE = CommandType
      .getCommandType("CHOKE_POINT_UPDATE");
  private static final CommandType GAME_UPDATE = CommandType.getCommandType("GAME_UPDATE");
  private static final CommandType PLAYER_UPDATE = CommandType.getCommandType("PLAYER_UPDATE");
  private static final CommandType POSITION_UPDATE = CommandType.getCommandType("POSITION_UPDATE");
  private static final CommandType RACE_UPDATE = CommandType.getCommandType("RACE_UPDATE");
  private static final CommandType REGION_UPDATE = CommandType.getCommandType("REGION_UPDATE");
  private static final CommandType TECH_TYPE_UPDATE = CommandType
      .getCommandType("TECH_TYPE_UPDATE");
  private static final CommandType TILE_POSITION_UPDATE = CommandType
      .getCommandType("TILE_POSITION_UPDATE");
  private static final CommandType UNIT_UPDATE = CommandType.getCommandType("UNIT_UPDATE");
  private static final CommandType UNIT_TYPE_UPDATE = CommandType
      .getCommandType("UNIT_TYPE_UPDATE");
  private static final CommandType UPGRADE_TYPE_UPDATE = CommandType
      .getCommandType("UPGRADE_TYPE_UPDATE");
  private static final CommandType WEAPON_TYPE_UPDATE = CommandType
      .getCommandType("WEAPON_TYPE_UPDATE");
  private static final CommandType UPDATE_ALL = CommandType
      .getCommandType("UPDATE_ALL");

  @Getter
  @Setter
  private long frameExecutionTime;

  //game related fields
  private Mirror mirror = new Mirror();

  public GameFacade(long frameExecutionTime) {
    this.frameExecutionTime = frameExecutionTime;
  }

  @Override
  public ReplayGameFacade getGameAsReplay() {
    //TODO
    return null;
  }

  @Override
  public void onStart() {
    Optional<Game> game = updateManager.wrapGame(mirror.getGame());
    if (game.isPresent()) {
      UpdateStrategy updateAllStrategy = new UpdateStrategy();
      updateManager.update(game.get(), updateAllStrategy);
      updateManager.initializeAllTypes(updateAllStrategy);
      onStart.ifPresent(IGameHasStartedNotificationSubscriber::notifySubscriber);
      log.info("Game has started.");
    } else {
      log.error("Failed to start the game.");
    }
  }

  @Override
  public void onEnd(boolean b) {
    onEnd.ifPresent(subscriber -> {
      subscriber.notifySubscriber(b);
      eventsRegister.onEnd(getCurrentFrame() + 1, b);
    });
  }

  @Override
  public void onSendText(String s) {
    onSendText.ifPresent(subscriber -> {
      subscriber.notifySubscriber(s);
      eventsRegister.onSendText(getCurrentFrame() + 1, s);
    });
  }

  @Override
  public void onReceiveText(bwapi.Player player, String s) {
    Optional<Player> p = updateManager.getDataContainer(WPlayer.getOrCreateWrapper(player));
    if (p.isPresent() && onReceiveText.isPresent()) {
      onReceiveText.get().notifySubscriber(p.get(), s);
      eventsRegister.onReceiveText(getCurrentFrame() + 1, p.get(), s);
    }
  }

  @Override
  public void onPlayerLeft(bwapi.Player player) {
    Optional<Player> p = updateManager.getDataContainer(WPlayer.getOrCreateWrapper(player));
    if (p.isPresent() && onPlayerLeft.isPresent()) {
      onPlayerLeft.get().notifySubscriber(p.get());
      eventsRegister.onPlayerLeft(getCurrentFrame() + 1, p.get());
    }
  }

  @Override
  public void onNukeDetect(bwapi.Position position) {
    Optional<Position> p = updateManager.getDataContainer(WPosition.getOrCreateWrapper(position));
    if (p.isPresent() && onNukeDetect.isPresent()) {
      onNukeDetect.get().notifySubscriber(p.get());
      eventsRegister.onNukeDetect(getCurrentFrame() + 1, p.get());
    }
  }

  @Override
  public void onUnitDiscover(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitDiscover.isPresent()) {
      onUnitDiscover.get().notifySubscriber(u.get());
      eventsRegister.onUnitDiscover(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitEvade(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitEvade.isPresent()) {
      onUnitEvade.get().notifySubscriber(u.get());
      eventsRegister.onUnitEvade(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitShow(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitShow.isPresent()) {
      onUnitShow.get().notifySubscriber(u.get());
      eventsRegister.onUnitShow(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitHide(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitHide.isPresent()) {
      onUnitHide.get().notifySubscriber(u.get());
      eventsRegister.onUnitHide(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitCreate(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitCreate.isPresent()) {
      onUnitCreate.get().notifySubscriber(u.get());
      eventsRegister.onUnitCreate(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitDestroy(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitDestroy.isPresent()) {
      onUnitDestroy.get().notifySubscriber(u.get());
      eventsRegister.onUnitDestroy(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitMorph(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitMorph.isPresent()) {
      onUnitMorph.get().notifySubscriber(u.get());
      eventsRegister.onUnitMorph(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitRenegade(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitRenegade.isPresent()) {
      onUnitRenegade.get().notifySubscriber(u.get());
      eventsRegister.onUnitRenegade(getCurrentFrame() + 1, u.get());
    }
  }

  @Override
  public void onUnitComplete(bwapi.Unit unit) {
    Optional<Unit> u = updateManager.getDataContainer(WUnit.getOrCreateWrapper(unit));
    if (u.isPresent() && onUnitComplete.isPresent()) {
      onUnitComplete.get().notifySubscriber(u.get());
      eventsRegister.onUnitComplete(getCurrentFrame() + 1, u.get());
    }
  }

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
  public void sendCommandToGame(CommandWithResponse commandWithResponse) {
    queueManager.addCommand(commandWithResponse);
  }

  @Override
  public void sendCommandToGame(CommandWithoutResponse commandWithoutResponse) {
    queueManager.addCommand(commandWithoutResponse);
  }

  @Override
  public void updateAll(UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(UPDATE_ALL, responseReceiver,
        () -> {
          getAllGameInstances()
              .forEach(o -> o.update(updateStrategy, updateManager, 0, getCurrentFrame()));
          return true;
        }));
  }

  @Override
  public void updateAll(UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(UPDATE_ALL,
        () -> getAllGameInstances()
            .forEach(o -> o.update(updateStrategy, updateManager, 0, getCurrentFrame()))));
  }

  @Override
  public int getCurrentFrame() {
    return bwCounter.getCurrentFrame();
  }

  @Override
  public void update(BaseLocation baseLocation, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(BASE_LOCATION_UPDATE,
        () -> updateManager.update(baseLocation, updateStrategy)));
  }

  @Override
  public void update(BaseLocation baseLocation, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(BASE_LOCATION_UPDATE, responseReceiver,
        () -> updateManager.update(baseLocation, updateStrategy)));
  }

  @Override
  public void update(Bullet bullet, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(BULLET_UPDATE,
        () -> updateManager.update(bullet, updateStrategy)));
  }

  @Override
  public void update(Bullet bullet, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(BULLET_UPDATE, responseReceiver,
        () -> updateManager.update(bullet, updateStrategy)));
  }

  @Override
  public void update(ChokePoint chokePoint, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(CHOKE_POINT_UPDATE,
        () -> updateManager.update(chokePoint, updateStrategy)));
  }

  @Override
  public void update(ChokePoint chokePoint, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(CHOKE_POINT_UPDATE, responseReceiver,
        () -> updateManager.update(chokePoint, updateStrategy)));
  }

  @Override
  public void updateGame(UpdateStrategy updateStrategy) {
    game.ifPresent(game -> queueManager.addCommand(new CommandWithoutResponse(GAME_UPDATE,
        () -> updateManager.update(game, updateStrategy))));
  }

  @Override
  public void updateGame(UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    if (game.isPresent()) {
      queueManager.addCommand(new CommandWithResponse<>(GAME_UPDATE, responseReceiver,
          () -> updateManager.update(game.get(), updateStrategy)));
    } else {
      responseReceiver.receiveResponse(false);
    }
  }

  @Override
  public void update(Player player, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(PLAYER_UPDATE,
        () -> updateManager.update(player, updateStrategy)));
  }

  @Override
  public void update(Player player, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(PLAYER_UPDATE, responseReceiver,
        () -> updateManager.update(player, updateStrategy)));
  }

  @Override
  public void update(Position position, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(POSITION_UPDATE,
        () -> updateManager.update(position, updateStrategy)));
  }

  @Override
  public void update(Position position, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(POSITION_UPDATE, responseReceiver,
        () -> updateManager.update(position, updateStrategy)));
  }

  @Override
  public void update(Race race, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(RACE_UPDATE,
        () -> updateManager.update(race, updateStrategy)));
  }

  @Override
  public void update(Race race, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(RACE_UPDATE, responseReceiver,
        () -> updateManager.update(race, updateStrategy)));
  }

  @Override
  public void update(Region region, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(REGION_UPDATE,
        () -> updateManager.update(region, updateStrategy)));
  }

  @Override
  public void update(Region region, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(REGION_UPDATE, responseReceiver,
        () -> updateManager.update(region, updateStrategy)));
  }

  @Override
  public void update(TechType techType, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(TECH_TYPE_UPDATE,
        () -> updateManager.update(techType, updateStrategy)));
  }

  @Override
  public void update(TechType techType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(TECH_TYPE_UPDATE, responseReceiver,
        () -> updateManager.update(techType, updateStrategy)));
  }

  @Override
  public void update(TilePosition tilePosition, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(TILE_POSITION_UPDATE,
        () -> updateManager.update(tilePosition, updateStrategy)));
  }

  @Override
  public void update(TilePosition tilePosition, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(TILE_POSITION_UPDATE, responseReceiver,
        () -> updateManager.update(tilePosition, updateStrategy)));
  }

  @Override
  public void update(Unit unit, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(UNIT_UPDATE,
        () -> updateManager.update(unit, updateStrategy)));
  }

  @Override
  public void update(Unit unit, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(UNIT_UPDATE, responseReceiver,
        () -> updateManager.update(unit, updateStrategy)));
  }

  @Override
  public void update(UnitType unitType, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(UNIT_TYPE_UPDATE,
        () -> updateManager.update(unitType, updateStrategy)));
  }

  @Override
  public void update(UnitType unitType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(UNIT_TYPE_UPDATE, responseReceiver,
        () -> updateManager.update(unitType, updateStrategy)));
  }

  @Override
  public void update(UpgradeType upgradeType, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(UPGRADE_TYPE_UPDATE,
        () -> updateManager.update(upgradeType, updateStrategy)));
  }

  @Override
  public void update(UpgradeType upgradeType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(UPGRADE_TYPE_UPDATE, responseReceiver,
        () -> updateManager.update(upgradeType, updateStrategy)));
  }

  @Override
  public void update(WeaponType weaponType, UpdateStrategy updateStrategy) {
    queueManager.addCommand(new CommandWithoutResponse(WEAPON_TYPE_UPDATE,
        () -> updateManager.update(weaponType, updateStrategy)));
  }

  @Override
  public void update(WeaponType weaponType, UpdateStrategy updateStrategy,
      IResponseReceiver<Boolean> responseReceiver) {
    queueManager.addCommand(new CommandWithResponse<>(WEAPON_TYPE_UPDATE, responseReceiver,
        () -> updateManager.update(weaponType, updateStrategy)));
  }

  @Override
  public Stream<? extends AContainer> getAllGameInstances() {
    return updateManager.getAllContainers();
  }

  @Override
  public Optional<Race> getType(RaceTypeEnum race) {
    return updateManager.getDataContainer(WRace.getOrCreateWrapper(race));
  }

  @Override
  public Optional<TechType> getType(TechTypeEnum techType) {
    return updateManager.getDataContainer(WTechType.getOrCreateWrapper(techType));
  }

  @Override
  public Optional<UnitType> getType(UnitTypeEnum unitType) {
    return updateManager.getDataContainer(WUnitType.getOrCreateWrapper(unitType));
  }

  @Override
  public Optional<UpgradeType> getType(UpgradeTypeEnum techType) {
    return updateManager.getDataContainer(WUpgradeType.getOrCreateWrapper(techType));
  }

  @Override
  public Optional<WeaponType> getType(WeaponTypeEnum unitType) {
    return updateManager.getDataContainer(WWeaponType.getOrCreateWrapper(unitType));
  }

  @Override
  public Optional<BaseLocation> getDataContainer(WBaseLocation baseLocation) {
    return updateManager.getDataContainer(baseLocation);
  }

  @Override
  public Optional<WBaseLocation> getBWInstance(BaseLocation container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<Bullet> getDataContainer(WBullet bullet) {
    return updateManager.getDataContainer(bullet);
  }

  @Override
  public Optional<WBullet> getBWInstance(Bullet container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<ChokePoint> getDataContainer(WChokePoint chokePoint) {
    return updateManager.getDataContainer(chokePoint);
  }

  @Override
  public Optional<WChokePoint> getBWInstance(ChokePoint container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<Player> getDataContainer(WPlayer player) {
    return updateManager.getDataContainer(player);
  }

  @Override
  public Optional<WPlayer> getBWInstance(Player container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<Position> getDataContainer(WPosition position) {
    return updateManager.getDataContainer(position);
  }

  @Override
  public Optional<WPosition> getBWInstance(Position container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<Race> getDataContainer(WRace race) {
    return updateManager.getDataContainer(race);
  }

  @Override
  public Optional<WRace> getBWInstance(Race container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<Region> getDataContainer(WRegion region) {
    return updateManager.getDataContainer(region);
  }

  @Override
  public Optional<WRegion> getBWInstance(Region container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<TechType> getDataContainer(WTechType techType) {
    return updateManager.getDataContainer(techType);
  }

  @Override
  public Optional<WTechType> getBWInstance(TechType container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<TilePosition> getDataContainer(WTilePosition tilePosition) {
    return updateManager.getDataContainer(tilePosition);
  }

  @Override
  public Optional<WTilePosition> getBWInstance(TilePosition container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<Unit> getDataContainer(WUnit unit) {
    return updateManager.getDataContainer(unit);
  }

  @Override
  public Optional<WUnit> getBWInstance(Unit container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<UnitType> getDataContainer(WUnitType unitType) {
    return updateManager.getDataContainer(unitType);
  }

  @Override
  public Optional<WUnitType> getBWInstance(UnitType container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<UpgradeType> getDataContainer(WUpgradeType upgradeType) {
    return updateManager.getDataContainer(upgradeType);
  }

  @Override
  public Optional<WUpgradeType> getBWInstance(UpgradeType container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<WeaponType> getDataContainer(WWeaponType weaponType) {
    return updateManager.getDataContainer(weaponType);
  }

  @Override
  public Optional<WWeaponType> getBWInstance(WeaponType container) {
    return updateManager.getBWInstance(container);
  }

  @Override
  public Optional<IPlayer> getSelf() {
    return game.flatMap(Game::getSelf);
  }

  @Override
  public Optional<Set<IPlayer>> getPlayers() {
    return game.flatMap(Game::getPlayers);
  }

  @Override
  public Optional<GameTypeEnum> getGameType() {
    return game.flatMap(Game::getGameType);
  }

  @Override
  public Optional<Integer> getFrameCount() {
    return game.flatMap(Game::getFrameCount);
  }

  @Override
  public Optional<Integer> getFPS() {
    return game.flatMap(Game::getFPS);
  }

  @Override
  public Optional<Double> getAverageFPS() {
    return game.flatMap(Game::getAverageFPS);
  }

  @Override
  public Optional<Integer> elapsedTime() {
    return game.flatMap(Game::elapsedTime);
  }

  @Override
  public Optional<Set<IRegion>> getRegions() {
    return game.flatMap(Game::getRegions);
  }

  @Override
  public Optional<Set<IChokePoint>> getChokePoints() {
    return game.flatMap(Game::getChokePoints);
  }

  @Override
  public Optional<Set<IBaseLocation>> getBaseLocations() {
    return game.flatMap(Game::getBaseLocations);
  }

  @Override
  public Optional<Set<IBaseLocation>> getStartLocations() {
    return game.flatMap(Game::getStartLocations);
  }

  @Override
  public Optional<Integer> mapWidth() {
    return game.flatMap(Game::mapWidth);
  }

  @Override
  public Optional<Integer> mapHeight() {
    return game.flatMap(Game::mapHeight);
  }

  @Override
  public Optional<String> mapName() {
    return game.flatMap(Game::mapName);
  }
}
