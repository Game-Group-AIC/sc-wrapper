package gg.fel.cvut.cz.data.events;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.data.events.subscribers.IGameHasEndedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.INukeDetectedNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IPlayerLeftNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IReceiveTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.ISendTextNotificationSubscriber;
import gg.fel.cvut.cz.data.events.subscribers.IUnitNotificationSubscriber;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Unit;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Read-only register for events
 */
public class EventsRegister implements Serializable {

  final StaticPropertyRegister<Boolean, Property<Boolean>> onEnd = new StaticPropertyRegister<Boolean, Property<Boolean>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<String>, Property<ImmutableSet<String>>> onSendText = new DynamicPropertyRegister<ImmutableSet<String>, Property<ImmutableSet<String>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<ReceiveTextContainer>, Property<ImmutableSet<ReceiveTextContainer>>> onReceiveText = new DynamicPropertyRegister<ImmutableSet<ReceiveTextContainer>, Property<ImmutableSet<ReceiveTextContainer>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Player>, Property<ImmutableSet<Player>>> onPlayerLeft = new DynamicPropertyRegister<ImmutableSet<Player>, Property<ImmutableSet<Player>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Position>, Property<ImmutableSet<Position>>> onNukeDetect = new DynamicPropertyRegister<ImmutableSet<Position>, Property<ImmutableSet<Position>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitDiscover = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitEvade = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitShow = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitHide = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitCreate = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitDestroy = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitMorph = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitRenegade = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);
  final DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>> onUnitComplete = new DynamicPropertyRegister<ImmutableSet<Unit>, Property<ImmutableSet<Unit>>>(
      Property::new);

  public void onEnd(int currentFrame, IGameHasEndedNotificationSubscriber subscriber) {
    onEnd.getValueInFrame(currentFrame).ifPresent(subscriber::notifySubscriber);
  }

  public void onSendText(int currentFrame, ISendTextNotificationSubscriber subscriber) {
    onSendText.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onReceiveText(int currentFrame, IReceiveTextNotificationSubscriber subscriber) {
    onReceiveText.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(cont -> subscriber.notifySubscriber(cont.player, cont.text)));
  }

  public void onPlayerLeft(int currentFrame, IPlayerLeftNotificationSubscriber subscriber) {
    onPlayerLeft.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onNukeDetect(int currentFrame, INukeDetectedNotificationSubscriber subscriber) {
    onNukeDetect.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitDiscover(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitDiscover.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitEvade(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitEvade.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitShow(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitShow.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitHide(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitHide.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitCreate(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitCreate.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitDestroy(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitDestroy.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitMorph(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitMorph.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitRenegade(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitRenegade.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  public void onUnitComplete(int currentFrame, IUnitNotificationSubscriber subscriber) {
    onUnitComplete.getValueInFrame(currentFrame)
        .ifPresent(set -> set.forEach(subscriber::notifySubscriber));
  }

  @EqualsAndHashCode(of = {"player", "text"})
  @AllArgsConstructor
  static class ReceiveTextContainer implements Serializable {

    private final Player player;
    private final String text;
  }

}
