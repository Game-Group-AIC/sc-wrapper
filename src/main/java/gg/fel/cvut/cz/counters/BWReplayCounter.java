package gg.fel.cvut.cz.counters;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;

/**
 * Tracks time in replays for SC:BW
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
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
