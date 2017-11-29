package gg.fel.cvut.cz.wrappers;

import static bwapi.TechType.Archon_Warp;
import static bwapi.TechType.Burrowing;
import static bwapi.TechType.Cloaking_Field;
import static bwapi.TechType.Consume;
import static bwapi.TechType.Dark_Archon_Meld;
import static bwapi.TechType.Dark_Swarm;
import static bwapi.TechType.Defensive_Matrix;
import static bwapi.TechType.Disruption_Web;
import static bwapi.TechType.EMP_Shockwave;
import static bwapi.TechType.Ensnare;
import static bwapi.TechType.Feedback;
import static bwapi.TechType.Hallucination;
import static bwapi.TechType.Healing;
import static bwapi.TechType.Infestation;
import static bwapi.TechType.Irradiate;
import static bwapi.TechType.Lockdown;
import static bwapi.TechType.Lurker_Aspect;
import static bwapi.TechType.Maelstrom;
import static bwapi.TechType.Mind_Control;
import static bwapi.TechType.None;
import static bwapi.TechType.Nuclear_Strike;
import static bwapi.TechType.Optical_Flare;
import static bwapi.TechType.Parasite;
import static bwapi.TechType.Personnel_Cloaking;
import static bwapi.TechType.Plague;
import static bwapi.TechType.Psionic_Storm;
import static bwapi.TechType.Recall;
import static bwapi.TechType.Restoration;
import static bwapi.TechType.Scanner_Sweep;
import static bwapi.TechType.Spawn_Broodlings;
import static bwapi.TechType.Spider_Mines;
import static bwapi.TechType.Stasis_Field;
import static bwapi.TechType.Stim_Packs;
import static bwapi.TechType.Tank_Siege_Mode;
import static bwapi.TechType.Unknown;
import static bwapi.TechType.Yamato_Gun;

import bwapi.TechType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WTechType extends WrapperForClassWithID<TechType> {

  private final static List<TechType> TECH_TYPES = Arrays
      .asList(Stim_Packs, Lockdown, EMP_Shockwave, Spider_Mines, Scanner_Sweep,
          Tank_Siege_Mode, Defensive_Matrix, Irradiate, Yamato_Gun, Cloaking_Field,
          Personnel_Cloaking, Restoration,
          Optical_Flare, Healing, Nuclear_Strike, Burrowing, Infestation, Spawn_Broodlings,
          Dark_Swarm, Plague,
          Consume, Ensnare, Parasite, Lurker_Aspect, Psionic_Storm, Hallucination, Recall,
          Stasis_Field, Archon_Warp,
          Disruption_Web, Mind_Control, Dark_Archon_Meld, Feedback, Maelstrom, None, Unknown);
  private static final Map<Key, WTechType> register = new HashMap<>();

  private WTechType(TechType scInstance) {
    super(scInstance, getIndexInList(TECH_TYPES, scInstance));
  }

  public static WTechType getOrCreateWrapper(TechType techType) {
    return getOrCreateWrapper(techType, register,
        scInstance -> new Key(getIndexInList(TECH_TYPES, scInstance)), WTechType::new);
  }
}
