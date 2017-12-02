package gg.fel.cvut.cz.data.events;

import gg.fel.cvut.cz.data.events.subscribers.IGameHasEndedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.INukeDetectedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IPlayerLeftNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IReceiveTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.ISendTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IUnitNotificationSubscriber;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Unit;
import java.io.Serializable;
import lombok.AllArgsConstructor;

/**
 * Read-only register for events
 */
public class EventsRegister implements Serializable {

  StaticPropertyRegister<Boolean> onEnd = new StaticPropertyRegister<>();
  DynamicPropertyRegister<String> onSendText = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<ReceiveTextContainer> onReceiveText = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Player> onPlayerLeft = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Position> onNukeDetect = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitDiscover = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitEvade = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitShow = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitHide = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitCreate = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitDestroy = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitMorph = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitRenegade = new DynamicPropertyRegister<>();
  DynamicPropertyRegister<Unit> onUnitComplete = new DynamicPropertyRegister<>();

  public void onEnd(int currentFrame, IGameHasEndedNotificationSubscriber subscriber) {
    onEnd.getValueInFrame(currentFrame).ifPresent(subscriber::notifySubscriber);
  }

  public void onSendText(int currentFrame, ISendTextNotificationSubscriber subscriber) {
    onSendText.getValueInFrame(currentFrame).ifPresent(subscriber::notifySubscriber);
  }

  public void onReceiveText(int currentFrame, IReceiveTextNotificationSubscriber subscriber) {
    onReceiveText.getValueInFrame(currentFrame)
        .ifPresent(cont -> subscriber.notifySubscriber(cont.player, cont.text));
  }

  public void onPlayerLeft(int currentFrame, IPlayerLeftNotificationSubscriber subscriber) {
    onPlayerLeft.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onNukeDetect(int currentFrame, INukeDetectedNotificationSubscriber subscriber) {
    onNukeDetect.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitDiscover(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitDiscover.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitEvade(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitEvade.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitShow(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitShow.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitHide(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitHide.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitCreate(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitCreate.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitDestroy(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitDestroy.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitMorph(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitMorph.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitRenegade(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitRenegade.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  public void onUnitComplete(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitComplete.getValueInFrame(currentFrame)
        .ifPresent(subscriber::notifySubscriber);
  }

  @AllArgsConstructor
  static class ReceiveTextContainer implements Serializable {

    private final Player player;
    private final String text;
  }

}
