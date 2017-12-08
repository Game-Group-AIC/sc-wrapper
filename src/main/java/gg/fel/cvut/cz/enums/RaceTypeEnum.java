package gg.fel.cvut.cz.enums;

import bwapi.Race;
import java.util.List;

public enum RaceTypeEnum implements IGameTypes<Race, RaceTypeEnum> {
  Zerg,
  Terran,
  Protoss,
  Random,
  None,
  Unknown;

  @Override
  public List<Race> getTypes() {
    return RACES;
  }

  @Override
  public RaceTypeEnum[] getValues() {
    return RaceTypeEnum.values();
  }

  // todo: static set of tech possibilities here from IRace
}
