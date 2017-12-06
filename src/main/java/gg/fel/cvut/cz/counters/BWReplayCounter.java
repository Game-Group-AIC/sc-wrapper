package gg.fel.cvut.cz.counters;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Tracks time in replays for SC:BW
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@NoArgsConstructor
public class BWReplayCounter implements IBWReplayCounter, Serializable {

  @JsonIgnore
  @Setter
  private transient Optional<Integer> length = Optional.empty();
  @Getter
  private final transient BWCounter bwCounter = new BWCounter();

  @JsonCreator
  public BWReplayCounter(@JsonProperty("length") int length) {
    this.length = Optional.of(length);
  }

  @JsonProperty("length")
  @Override
  public int lengthOfReplay() {
    return length.orElse(getCurrentFrame());
  }

  @Override
  public void decreaseClock() {
    bwCounter.decrease();
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
