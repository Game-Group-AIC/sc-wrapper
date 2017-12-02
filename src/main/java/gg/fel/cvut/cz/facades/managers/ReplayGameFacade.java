package gg.fel.cvut.cz.facades.managers;

import gg.fel.cvut.cz.api.IGame;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.counters.IBWReplayCounter;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasEndedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasStartedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.INukeDetectedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IPlayerLeftNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IReceiveTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.ISendTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IUnitNotificationSubscriber;
import gg.fel.cvut.cz.facades.IGameDataAccessAdapter;
import java.io.Serializable;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Builder
public class ReplayGameFacade extends BWDataFacade<BWReplayCounter> implements
    IGameDataAccessAdapter, Serializable, IBWReplayCounter, IGame {

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


}
