package gg.fel.cvut.cz.data.events;

import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Unit;

/**
 * Allows to update register with new events
 */
public class UpdatableEventsRegister extends EventsRegister {

  private transient final EventTypeRegister<String> onSendTextRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<ReceiveTextContainer> onReceiveTextRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Player> onPlayerLeftRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Position> onNukeDetectRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitDiscoverRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitEvadeRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitShowRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitHideRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitCreateRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitDestroyRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitMorphRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitRenegadeRegister = new EventTypeRegister<>();
  private transient final EventTypeRegister<Unit> onUnitCompleteRegister = new EventTypeRegister<>();


  public void saveEvents(int currentFrame) {
    onSendTextRegister.batchEventsInFrameToRegister(currentFrame, onSendText);
    onReceiveTextRegister.batchEventsInFrameToRegister(currentFrame, onReceiveText);
    onPlayerLeftRegister.batchEventsInFrameToRegister(currentFrame, onPlayerLeft);
    onNukeDetectRegister.batchEventsInFrameToRegister(currentFrame, onNukeDetect);
    onUnitDiscoverRegister.batchEventsInFrameToRegister(currentFrame, onUnitDiscover);
    onUnitEvadeRegister.batchEventsInFrameToRegister(currentFrame, onUnitEvade);
    onUnitShowRegister.batchEventsInFrameToRegister(currentFrame, onUnitShow);
    onUnitHideRegister.batchEventsInFrameToRegister(currentFrame, onUnitHide);
    onUnitCreateRegister.batchEventsInFrameToRegister(currentFrame, onUnitCreate);
    onUnitDestroyRegister.batchEventsInFrameToRegister(currentFrame, onUnitDestroy);
    onUnitMorphRegister.batchEventsInFrameToRegister(currentFrame, onUnitMorph);
    onUnitRenegadeRegister.batchEventsInFrameToRegister(currentFrame, onUnitRenegade);
    onUnitCompleteRegister.batchEventsInFrameToRegister(currentFrame, onUnitComplete);

  }

  public void onUnitDiscover(Unit unit) {
    onUnitDiscoverRegister.add(unit);
  }

  public void onUnitEvade(Unit unit) {
    onUnitEvadeRegister.add(unit);
  }

  public void onUnitShow(Unit unit) {
    onUnitShowRegister.add(unit);
  }

  public void onUnitHide(Unit unit) {
    onUnitHideRegister.add(unit);
  }

  public void onUnitCreate(Unit unit) {
    onUnitCreateRegister.add(unit);
  }

  public void onUnitDestroy(Unit unit) {
    onUnitDestroyRegister.add(unit);
  }

  public void onUnitMorph(Unit unit) {
    onUnitMorphRegister.add(unit);
  }

  public void onUnitRenegade(Unit unit) {
    onUnitRenegadeRegister.add(unit);
  }

  public void onUnitComplete(Unit unit) {
    onUnitCompleteRegister.add(unit);
  }

  public void onEnd(int currentFrame, boolean value) {
    onEnd.addProperty(value, currentFrame);
  }

  public void onSendText(String text) {
    onSendTextRegister.add(text);
  }

  public void onReceiveText(Player player, String text) {
    onReceiveTextRegister.add(new ReceiveTextContainer(player, text));
  }

  public void onPlayerLeft(Player player) {
    onPlayerLeftRegister.add(player);
  }

  public void onNukeDetect(Position position) {
    onNukeDetectRegister.add(position);
  }

}
