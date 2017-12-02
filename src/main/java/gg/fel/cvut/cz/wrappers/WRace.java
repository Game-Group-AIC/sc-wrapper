package gg.fel.cvut.cz.wrappers;

import bwapi.Race;
import gg.fel.cvut.cz.enums.RaceTypeEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WRace extends WrapperForType<Race, RaceTypeEnum> {

  private static final Map<RaceTypeEnum, WRace> register = new HashMap<>();

  private WRace(RaceTypeEnum raceType) {
    super(raceType.getBWType(), raceType);
  }

  public static WRace getOrCreateWrapper(RaceTypeEnum raceType) {
    return getOrCreateWrapper(raceType, register, WRace::new, lock);
  }

  public static Stream<WRace> getAllWrappedTypes() {
    return Arrays.stream(RaceTypeEnum.values()).map(WRace::getOrCreateWrapper);
  }
}
