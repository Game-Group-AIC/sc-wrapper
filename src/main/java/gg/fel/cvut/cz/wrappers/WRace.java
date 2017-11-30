package gg.fel.cvut.cz.wrappers;

import static bwapi.Race.None;
import static bwapi.Race.Protoss;
import static bwapi.Race.Random;
import static bwapi.Race.Terran;
import static bwapi.Race.Unknown;
import static bwapi.Race.Zerg;

import bwapi.Race;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WRace extends WrapperForClassWithID<Race> {

  private final static List<Race> RACE_TYPES = Arrays
      .asList(Zerg,
          Terran,
          Protoss,
          Random,
          None,
          Unknown);
  private static final Map<Key, WRace> register = new HashMap<>();

  private WRace(Race scInstance) {
    super(scInstance, getIndexInList(RACE_TYPES, scInstance));
  }

  public static WRace getOrCreateWrapper(Race race) {
    return getOrCreateWrapper(race, register,
        scInstance -> new Key(getIndexInList(RACE_TYPES, scInstance)), WRace::new);
  }

}
