package gg.fel.cvut.cz.data.events;

import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Unit;

/**
 * Allows to update register with new events
 */
public class UpdatableEventsRegister extends EventsRegister {

  public void onEnd(int currentFrame, boolean value) {
    onEnd.addProperty(value, currentFrame);
  }

  public void onSendText(int currentFrame, String text) {
    onSendText.addProperty(text, currentFrame);
  }

  public void onReceiveText(int currentFrame, Player player, String text) {
    onReceiveText.addProperty(new ReceiveTextContainer(player, text), currentFrame);
  }

  public void onPlayerLeft(int currentFrame, Player player) {
    onPlayerLeft.addProperty(player, currentFrame);
  }

  public void onNukeDetect(int currentFrame, Position position) {
    onNukeDetect.addProperty(position, currentFrame);
  }

  public void onUnitDiscover(int currentFrame, Unit unit) {
    onUnitDiscover.addProperty(unit, currentFrame);
  }

  public void onUnitEvade(int currentFrame, Unit unit) {
    onUnitEvade.addProperty(unit, currentFrame);
  }

  public void onUnitShow(int currentFrame, Unit unit) {
    onUnitShow.addProperty(unit, currentFrame);
  }

  public void onUnitHide(int currentFrame, Unit unit) {
    onUnitHide.addProperty(unit, currentFrame);
  }

  public void onUnitCreate(int currentFrame, Unit unit) {
    onUnitCreate.addProperty(unit, currentFrame);
  }

  public void onUnitDestroy(int currentFrame, Unit unit) {
    onUnitDestroy.addProperty(unit, currentFrame);
  }

  public void onUnitMorph(int currentFrame, Unit unit) {
    onUnitMorph.addProperty(unit, currentFrame);
  }

  public void onUnitRenegade(int currentFrame, Unit unit) {
    onUnitRenegade.addProperty(unit, currentFrame);
  }

  public void onUnitComplete(int currentFrame, Unit unit) {
    onUnitComplete.addProperty(unit, currentFrame);
  }

}
