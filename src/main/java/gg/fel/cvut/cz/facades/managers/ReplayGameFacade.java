package gg.fel.cvut.cz.facades.managers;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IChokePoint;
import gg.fel.cvut.cz.api.IGameFacade;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.counters.IBWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.events.EventsRegister;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasEndedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.INukeDetectedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IOnFrameNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IPlayerLeftNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IReceiveTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.ISendTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IUnitNotificationSubscriber;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.Game;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.data.readonly.TechType;
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
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Facade to access data from parsed replay
 */
@Slf4j
@AllArgsConstructor
@Builder
public class ReplayGameFacade implements
    IGameDataAccessAdapter, Serializable, IBWReplayCounter, IGameFacade {

  private final BWReplayCounter bwCounter;

  //parsed data
  private final EventsRegister eventsRegister;
  private final Game game;
  private final ImmutableSet<Bullet> bullets;
  private final ImmutableSet<Unit> units;
  private final ImmutableMap<RaceTypeEnum, Race> races;
  private final ImmutableMap<TechTypeEnum, TechType> techTypes;
  private final ImmutableMap<UnitTypeEnum, UnitType> unitTypes;
  private final ImmutableMap<UpgradeTypeEnum, UpgradeType> upgradeTypes;
  private final ImmutableMap<WeaponTypeEnum, WeaponType> weaponTypes;

  //event notification receivers
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitDiscover = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitEvade = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitShow = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitHide = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitCreate = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitDestroy = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitMorph = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitRenegade = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IUnitNotificationSubscriber> onUnitComplete = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IGameHasEndedNotificationSubscriber> onEnd = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<INukeDetectedNotificationSubscriber> onNukeDetect = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IPlayerLeftNotificationSubscriber> onPlayerLeft = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IReceiveTextNotificationSubscriber> onReceiveText = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<ISendTextNotificationSubscriber> onSendText = Optional.empty();
  @Setter
  @Builder.Default
  private transient Optional<IOnFrameNotificationSubscriber> onFrame = Optional.empty();

  public Stream<Unit> getUnits() {
    return units.stream();
  }

  public Stream<Bullet> getBullets() {
    return bullets.stream();
  }

  @Override
  public Optional<Stream<IPlayer>> getPlayers() {
    return game.getPlayers();
  }

  @Override
  public Optional<GameTypeEnum> getGameType() {
    return game.getGameType();
  }

  @Override
  public Optional<Integer> getFrameCount() {
    return game.getFrameCount();
  }

  @Override
  public Optional<Integer> getFPS() {
    return game.getFPS();
  }

  @Override
  public Optional<Double> getAverageFPS() {
    return game.getAverageFPS();
  }

  @Override
  public Optional<Integer> elapsedTime() {
    return game.elapsedTime();
  }

  @Override
  public Optional<Stream<IRegion>> getRegions() {
    return game.getRegions();
  }

  @Override
  public Optional<Stream<IChokePoint>> getChokePoints() {
    return game.getChokePoints();
  }

  @Override
  public Optional<Stream<IBaseLocation>> getBaseLocations() {
    return game.getBaseLocations();
  }

  @Override
  public Optional<Stream<IBaseLocation>> getStartLocations() {
    return game.getStartLocations();
  }

  @Override
  public Optional<Integer> mapWidth() {
    return game.mapWidth();
  }

  @Override
  public Optional<Integer> mapHeight() {
    return game.mapHeight();
  }

  @Override
  public Optional<String> mapName() {
    return game.mapName();
  }

  @Override
  public Optional<Stream<ITilePosition>> getGrid() {
    return game.getGrid();
  }

  @Override
  public int lengthOfReplay() {
    return bwCounter.lengthOfReplay();
  }

  @Override
  public synchronized void decreaseClock() {
    bwCounter.decreaseClock();
    onFrame.ifPresent(ns -> ns.notifySubscriber(bwCounter.getCurrentFrame()));
    notifySubscribersOnEvent();
  }

  @Override
  public synchronized void increaseClocks() {
    bwCounter.increaseClocks();
    onFrame.ifPresent(ns -> ns.notifySubscriber(bwCounter.getCurrentFrame()));
    notifySubscribersOnEvent();
  }

  @Override
  public int getCurrentFrame() {
    return bwCounter.getCurrentFrame();
  }

  @Override
  public Stream<? extends AContainer> getAllGameInstances() {
    return Stream.concat(getBullets(), getUnits());
  }

  @Override
  public Optional<Race> getType(RaceTypeEnum race) {
    return Optional.empty();
  }

  @Override
  public Optional<TechType> getType(TechTypeEnum techType) {
    return Optional.empty();
  }

  @Override
  public Optional<UnitType> getType(UnitTypeEnum unitType) {
    return Optional.empty();
  }

  @Override
  public Optional<UpgradeType> getType(UpgradeTypeEnum techType) {
    return Optional.empty();
  }

  @Override
  public Optional<WeaponType> getType(WeaponTypeEnum unitType) {
    return Optional.empty();
  }

  private void notifySubscribersOnEvent() {
    onUnitDiscover.ifPresent(ns -> eventsRegister.onUnitDiscover(getCurrentFrame(), ns));
    onUnitEvade.ifPresent(ns -> eventsRegister.onUnitEvade(getCurrentFrame(), ns));
    onUnitShow.ifPresent(ns -> eventsRegister.onUnitShow(getCurrentFrame(), ns));
    onUnitHide.ifPresent(ns -> eventsRegister.onUnitHide(getCurrentFrame(), ns));
    onUnitCreate.ifPresent(ns -> eventsRegister.onUnitCreate(getCurrentFrame(), ns));
    onUnitDestroy.ifPresent(ns -> eventsRegister.onUnitDestroy(getCurrentFrame(), ns));
    onUnitMorph.ifPresent(ns -> eventsRegister.onUnitMorph(getCurrentFrame(), ns));
    onUnitRenegade.ifPresent(ns -> eventsRegister.onUnitRenegade(getCurrentFrame(), ns));
    onUnitComplete.ifPresent(ns -> eventsRegister.onUnitComplete(getCurrentFrame(), ns));
    onEnd.ifPresent(ns -> eventsRegister.onEnd(getCurrentFrame(), ns));
    onNukeDetect.ifPresent(ns -> eventsRegister.onNukeDetect(getCurrentFrame(), ns));
    onPlayerLeft.ifPresent(ns -> eventsRegister.onPlayerLeft(getCurrentFrame(), ns));
    onReceiveText.ifPresent(ns -> eventsRegister.onReceiveText(getCurrentFrame(), ns));
    onSendText.ifPresent(ns -> eventsRegister.onSendText(getCurrentFrame(), ns));
  }

}
