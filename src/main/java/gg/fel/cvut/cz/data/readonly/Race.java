package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForType;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.RaceTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//TODO implement
public class Race extends AContainerForType<bwapi.Race, RaceTypeEnum> implements IRace,
    Serializable {

  public Race(BWReplayCounter bwCounter, RaceTypeEnum raceType) {
    super(bwCounter, raceType);
  }


  @Override
  protected Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode() {
    return new HashSet<>();
  }

  @Override
  public RaceTypeEnum getRaceType() {
    return type;
  }
}
