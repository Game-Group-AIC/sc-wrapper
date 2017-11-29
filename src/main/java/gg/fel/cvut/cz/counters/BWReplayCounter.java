package gg.fel.cvut.cz.counters;

import java.io.Serializable;

/**
 * Tracks time in replays for SC:BW
 */
public class BWReplayCounter extends BWCounter implements IBWReplayCounter, Serializable {

  private final int gameLength;

  public BWReplayCounter(int gameLength) {
    this.gameLength = gameLength;
  }

  @Override
  public int lengthOfReplay() {
    return gameLength;
  }

  @Override
  public void decreaseClock() {
    decrease();
  }

}
