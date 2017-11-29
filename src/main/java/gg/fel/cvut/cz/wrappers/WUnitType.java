package gg.fel.cvut.cz.wrappers;


import static bwapi.UnitType.AllUnits;
import static bwapi.UnitType.Buildings;
import static bwapi.UnitType.Critter_Bengalaas;
import static bwapi.UnitType.Critter_Kakaru;
import static bwapi.UnitType.Critter_Ragnasaur;
import static bwapi.UnitType.Critter_Rhynadon;
import static bwapi.UnitType.Critter_Scantid;
import static bwapi.UnitType.Critter_Ursadon;
import static bwapi.UnitType.Factories;
import static bwapi.UnitType.Hero_Alan_Schezar;
import static bwapi.UnitType.Hero_Aldaris;
import static bwapi.UnitType.Hero_Alexei_Stukov;
import static bwapi.UnitType.Hero_Arcturus_Mengsk;
import static bwapi.UnitType.Hero_Artanis;
import static bwapi.UnitType.Hero_Danimoth;
import static bwapi.UnitType.Hero_Dark_Templar;
import static bwapi.UnitType.Hero_Devouring_One;
import static bwapi.UnitType.Hero_Edmund_Duke_Siege_Mode;
import static bwapi.UnitType.Hero_Edmund_Duke_Tank_Mode;
import static bwapi.UnitType.Hero_Fenix_Dragoon;
import static bwapi.UnitType.Hero_Fenix_Zealot;
import static bwapi.UnitType.Hero_Gantrithor;
import static bwapi.UnitType.Hero_Gerard_DuGalle;
import static bwapi.UnitType.Hero_Gui_Montag;
import static bwapi.UnitType.Hero_Hunter_Killer;
import static bwapi.UnitType.Hero_Hyperion;
import static bwapi.UnitType.Hero_Infested_Duran;
import static bwapi.UnitType.Hero_Infested_Kerrigan;
import static bwapi.UnitType.Hero_Jim_Raynor_Marine;
import static bwapi.UnitType.Hero_Jim_Raynor_Vulture;
import static bwapi.UnitType.Hero_Kukulza_Guardian;
import static bwapi.UnitType.Hero_Kukulza_Mutalisk;
import static bwapi.UnitType.Hero_Magellan;
import static bwapi.UnitType.Hero_Matriarch;
import static bwapi.UnitType.Hero_Mojo;
import static bwapi.UnitType.Hero_Norad_II;
import static bwapi.UnitType.Hero_Raszagal;
import static bwapi.UnitType.Hero_Samir_Duran;
import static bwapi.UnitType.Hero_Sarah_Kerrigan;
import static bwapi.UnitType.Hero_Tassadar;
import static bwapi.UnitType.Hero_Tassadar_Zeratul_Archon;
import static bwapi.UnitType.Hero_Tom_Kazansky;
import static bwapi.UnitType.Hero_Torrasque;
import static bwapi.UnitType.Hero_Unclean_One;
import static bwapi.UnitType.Hero_Warbringer;
import static bwapi.UnitType.Hero_Yggdrasill;
import static bwapi.UnitType.Hero_Zeratul;
import static bwapi.UnitType.Men;
import static bwapi.UnitType.None;
import static bwapi.UnitType.Powerup_Data_Disk;
import static bwapi.UnitType.Powerup_Flag;
import static bwapi.UnitType.Powerup_Khalis_Crystal;
import static bwapi.UnitType.Powerup_Khaydarin_Crystal;
import static bwapi.UnitType.Powerup_Mineral_Cluster_Type_1;
import static bwapi.UnitType.Powerup_Mineral_Cluster_Type_2;
import static bwapi.UnitType.Powerup_Protoss_Gas_Orb_Type_1;
import static bwapi.UnitType.Powerup_Protoss_Gas_Orb_Type_2;
import static bwapi.UnitType.Powerup_Psi_Emitter;
import static bwapi.UnitType.Powerup_Terran_Gas_Tank_Type_1;
import static bwapi.UnitType.Powerup_Terran_Gas_Tank_Type_2;
import static bwapi.UnitType.Powerup_Uraj_Crystal;
import static bwapi.UnitType.Powerup_Young_Chrysalis;
import static bwapi.UnitType.Powerup_Zerg_Gas_Sac_Type_1;
import static bwapi.UnitType.Powerup_Zerg_Gas_Sac_Type_2;
import static bwapi.UnitType.Protoss_Arbiter;
import static bwapi.UnitType.Protoss_Arbiter_Tribunal;
import static bwapi.UnitType.Protoss_Archon;
import static bwapi.UnitType.Protoss_Assimilator;
import static bwapi.UnitType.Protoss_Carrier;
import static bwapi.UnitType.Protoss_Citadel_of_Adun;
import static bwapi.UnitType.Protoss_Corsair;
import static bwapi.UnitType.Protoss_Cybernetics_Core;
import static bwapi.UnitType.Protoss_Dark_Archon;
import static bwapi.UnitType.Protoss_Dark_Templar;
import static bwapi.UnitType.Protoss_Dragoon;
import static bwapi.UnitType.Protoss_Fleet_Beacon;
import static bwapi.UnitType.Protoss_Forge;
import static bwapi.UnitType.Protoss_Gateway;
import static bwapi.UnitType.Protoss_High_Templar;
import static bwapi.UnitType.Protoss_Interceptor;
import static bwapi.UnitType.Protoss_Nexus;
import static bwapi.UnitType.Protoss_Observatory;
import static bwapi.UnitType.Protoss_Observer;
import static bwapi.UnitType.Protoss_Photon_Cannon;
import static bwapi.UnitType.Protoss_Probe;
import static bwapi.UnitType.Protoss_Pylon;
import static bwapi.UnitType.Protoss_Reaver;
import static bwapi.UnitType.Protoss_Robotics_Facility;
import static bwapi.UnitType.Protoss_Robotics_Support_Bay;
import static bwapi.UnitType.Protoss_Scarab;
import static bwapi.UnitType.Protoss_Scout;
import static bwapi.UnitType.Protoss_Shield_Battery;
import static bwapi.UnitType.Protoss_Shuttle;
import static bwapi.UnitType.Protoss_Stargate;
import static bwapi.UnitType.Protoss_Templar_Archives;
import static bwapi.UnitType.Protoss_Zealot;
import static bwapi.UnitType.Resource_Mineral_Field;
import static bwapi.UnitType.Resource_Mineral_Field_Type_2;
import static bwapi.UnitType.Resource_Mineral_Field_Type_3;
import static bwapi.UnitType.Resource_Vespene_Geyser;
import static bwapi.UnitType.Special_Cargo_Ship;
import static bwapi.UnitType.Special_Cerebrate;
import static bwapi.UnitType.Special_Cerebrate_Daggoth;
import static bwapi.UnitType.Special_Crashed_Norad_II;
import static bwapi.UnitType.Special_Floor_Gun_Trap;
import static bwapi.UnitType.Special_Floor_Hatch;
import static bwapi.UnitType.Special_Floor_Missile_Trap;
import static bwapi.UnitType.Special_Independant_Starport;
import static bwapi.UnitType.Special_Ion_Cannon;
import static bwapi.UnitType.Special_Khaydarin_Crystal_Form;
import static bwapi.UnitType.Special_Map_Revealer;
import static bwapi.UnitType.Special_Mature_Chrysalis;
import static bwapi.UnitType.Special_Mercenary_Gunship;
import static bwapi.UnitType.Special_Overmind;
import static bwapi.UnitType.Special_Overmind_Cocoon;
import static bwapi.UnitType.Special_Overmind_With_Shell;
import static bwapi.UnitType.Special_Pit_Door;
import static bwapi.UnitType.Special_Power_Generator;
import static bwapi.UnitType.Special_Protoss_Beacon;
import static bwapi.UnitType.Special_Protoss_Flag_Beacon;
import static bwapi.UnitType.Special_Protoss_Temple;
import static bwapi.UnitType.Special_Psi_Disrupter;
import static bwapi.UnitType.Special_Right_Pit_Door;
import static bwapi.UnitType.Special_Right_Upper_Level_Door;
import static bwapi.UnitType.Special_Right_Wall_Flame_Trap;
import static bwapi.UnitType.Special_Right_Wall_Missile_Trap;
import static bwapi.UnitType.Special_Start_Location;
import static bwapi.UnitType.Special_Stasis_Cell_Prison;
import static bwapi.UnitType.Special_Terran_Beacon;
import static bwapi.UnitType.Special_Terran_Flag_Beacon;
import static bwapi.UnitType.Special_Upper_Level_Door;
import static bwapi.UnitType.Special_Wall_Flame_Trap;
import static bwapi.UnitType.Special_Wall_Missile_Trap;
import static bwapi.UnitType.Special_Warp_Gate;
import static bwapi.UnitType.Special_XelNaga_Temple;
import static bwapi.UnitType.Special_Zerg_Beacon;
import static bwapi.UnitType.Special_Zerg_Flag_Beacon;
import static bwapi.UnitType.Spell_Dark_Swarm;
import static bwapi.UnitType.Spell_Disruption_Web;
import static bwapi.UnitType.Spell_Scanner_Sweep;
import static bwapi.UnitType.Terran_Academy;
import static bwapi.UnitType.Terran_Armory;
import static bwapi.UnitType.Terran_Barracks;
import static bwapi.UnitType.Terran_Battlecruiser;
import static bwapi.UnitType.Terran_Bunker;
import static bwapi.UnitType.Terran_Civilian;
import static bwapi.UnitType.Terran_Command_Center;
import static bwapi.UnitType.Terran_Comsat_Station;
import static bwapi.UnitType.Terran_Control_Tower;
import static bwapi.UnitType.Terran_Covert_Ops;
import static bwapi.UnitType.Terran_Dropship;
import static bwapi.UnitType.Terran_Engineering_Bay;
import static bwapi.UnitType.Terran_Factory;
import static bwapi.UnitType.Terran_Firebat;
import static bwapi.UnitType.Terran_Ghost;
import static bwapi.UnitType.Terran_Goliath;
import static bwapi.UnitType.Terran_Machine_Shop;
import static bwapi.UnitType.Terran_Marine;
import static bwapi.UnitType.Terran_Medic;
import static bwapi.UnitType.Terran_Missile_Turret;
import static bwapi.UnitType.Terran_Nuclear_Missile;
import static bwapi.UnitType.Terran_Nuclear_Silo;
import static bwapi.UnitType.Terran_Physics_Lab;
import static bwapi.UnitType.Terran_Refinery;
import static bwapi.UnitType.Terran_SCV;
import static bwapi.UnitType.Terran_Science_Facility;
import static bwapi.UnitType.Terran_Science_Vessel;
import static bwapi.UnitType.Terran_Siege_Tank_Siege_Mode;
import static bwapi.UnitType.Terran_Siege_Tank_Tank_Mode;
import static bwapi.UnitType.Terran_Starport;
import static bwapi.UnitType.Terran_Supply_Depot;
import static bwapi.UnitType.Terran_Valkyrie;
import static bwapi.UnitType.Terran_Vulture;
import static bwapi.UnitType.Terran_Vulture_Spider_Mine;
import static bwapi.UnitType.Terran_Wraith;
import static bwapi.UnitType.Unknown;
import static bwapi.UnitType.Zerg_Broodling;
import static bwapi.UnitType.Zerg_Cocoon;
import static bwapi.UnitType.Zerg_Creep_Colony;
import static bwapi.UnitType.Zerg_Defiler;
import static bwapi.UnitType.Zerg_Defiler_Mound;
import static bwapi.UnitType.Zerg_Devourer;
import static bwapi.UnitType.Zerg_Drone;
import static bwapi.UnitType.Zerg_Egg;
import static bwapi.UnitType.Zerg_Evolution_Chamber;
import static bwapi.UnitType.Zerg_Extractor;
import static bwapi.UnitType.Zerg_Greater_Spire;
import static bwapi.UnitType.Zerg_Guardian;
import static bwapi.UnitType.Zerg_Hatchery;
import static bwapi.UnitType.Zerg_Hive;
import static bwapi.UnitType.Zerg_Hydralisk;
import static bwapi.UnitType.Zerg_Hydralisk_Den;
import static bwapi.UnitType.Zerg_Infested_Command_Center;
import static bwapi.UnitType.Zerg_Infested_Terran;
import static bwapi.UnitType.Zerg_Lair;
import static bwapi.UnitType.Zerg_Larva;
import static bwapi.UnitType.Zerg_Lurker;
import static bwapi.UnitType.Zerg_Lurker_Egg;
import static bwapi.UnitType.Zerg_Mutalisk;
import static bwapi.UnitType.Zerg_Nydus_Canal;
import static bwapi.UnitType.Zerg_Overlord;
import static bwapi.UnitType.Zerg_Queen;
import static bwapi.UnitType.Zerg_Queens_Nest;
import static bwapi.UnitType.Zerg_Scourge;
import static bwapi.UnitType.Zerg_Spawning_Pool;
import static bwapi.UnitType.Zerg_Spire;
import static bwapi.UnitType.Zerg_Spore_Colony;
import static bwapi.UnitType.Zerg_Sunken_Colony;
import static bwapi.UnitType.Zerg_Ultralisk;
import static bwapi.UnitType.Zerg_Ultralisk_Cavern;
import static bwapi.UnitType.Zerg_Zergling;

import bwapi.UnitType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WUnitType extends WrapperForClassWithID<UnitType> {

  private final static List<UnitType> UNIT_TYPES = Arrays.asList(Terran_Firebat, Terran_Ghost,
      Terran_Goliath, Terran_Marine, Terran_Medic, Terran_SCV, Terran_Siege_Tank_Siege_Mode,
      Terran_Siege_Tank_Tank_Mode, Terran_Vulture, Terran_Vulture_Spider_Mine, Terran_Battlecruiser,
      Terran_Dropship, Terran_Nuclear_Missile, Terran_Science_Vessel, Terran_Valkyrie,
      Terran_Wraith,
      Hero_Alan_Schezar, Hero_Alexei_Stukov, Hero_Arcturus_Mengsk, Hero_Edmund_Duke_Tank_Mode,
      Hero_Edmund_Duke_Siege_Mode, Hero_Gerard_DuGalle, Hero_Gui_Montag, Hero_Hyperion,
      Hero_Jim_Raynor_Marine, Hero_Jim_Raynor_Vulture, Hero_Magellan, Hero_Norad_II,
      Hero_Samir_Duran,
      Hero_Sarah_Kerrigan, Hero_Tom_Kazansky, Terran_Civilian, Terran_Academy, Terran_Armory,
      Terran_Barracks, Terran_Bunker, Terran_Command_Center, Terran_Engineering_Bay, Terran_Factory,
      Terran_Missile_Turret, Terran_Refinery, Terran_Science_Facility, Terran_Starport,
      Terran_Supply_Depot,
      Terran_Comsat_Station, Terran_Control_Tower, Terran_Covert_Ops, Terran_Machine_Shop,
      Terran_Nuclear_Silo, Terran_Physics_Lab, Special_Crashed_Norad_II, Special_Ion_Cannon,
      Special_Power_Generator,
      Special_Psi_Disrupter, Protoss_Archon, Protoss_Dark_Archon, Protoss_Dark_Templar,
      Protoss_Dragoon,
      Protoss_High_Templar, Protoss_Probe, Protoss_Reaver, Protoss_Scarab, Protoss_Zealot,
      Protoss_Arbiter,
      Protoss_Carrier, Protoss_Corsair, Protoss_Interceptor, Protoss_Observer, Protoss_Scout,
      Protoss_Shuttle,
      Hero_Aldaris, Hero_Artanis, Hero_Danimoth, Hero_Dark_Templar, Hero_Fenix_Dragoon,
      Hero_Fenix_Zealot,
      Hero_Gantrithor, Hero_Mojo, Hero_Raszagal, Hero_Tassadar, Hero_Tassadar_Zeratul_Archon,
      Hero_Warbringer,
      Hero_Zeratul, Protoss_Arbiter_Tribunal, Protoss_Assimilator, Protoss_Citadel_of_Adun,
      Protoss_Cybernetics_Core,
      Protoss_Fleet_Beacon, Protoss_Forge, Protoss_Gateway, Protoss_Nexus, Protoss_Observatory,
      Protoss_Photon_Cannon,
      Protoss_Pylon, Protoss_Robotics_Facility, Protoss_Robotics_Support_Bay,
      Protoss_Shield_Battery,
      Protoss_Stargate, Protoss_Templar_Archives, Special_Khaydarin_Crystal_Form,
      Special_Protoss_Temple,
      Special_Stasis_Cell_Prison, Special_Warp_Gate, Special_XelNaga_Temple, Zerg_Broodling,
      Zerg_Defiler,
      Zerg_Drone, Zerg_Egg, Zerg_Hydralisk, Zerg_Infested_Terran, Zerg_Larva, Zerg_Lurker,
      Zerg_Lurker_Egg,
      Zerg_Ultralisk, Zerg_Zergling, Zerg_Cocoon, Zerg_Devourer, Zerg_Guardian, Zerg_Mutalisk,
      Zerg_Overlord,
      Zerg_Queen, Zerg_Scourge, Hero_Devouring_One, Hero_Hunter_Killer, Hero_Infested_Duran,
      Hero_Infested_Kerrigan,
      Hero_Kukulza_Guardian, Hero_Kukulza_Mutalisk, Hero_Matriarch, Hero_Torrasque,
      Hero_Unclean_One,
      Hero_Yggdrasill, Zerg_Creep_Colony, Zerg_Defiler_Mound, Zerg_Evolution_Chamber,
      Zerg_Extractor,
      Zerg_Greater_Spire, Zerg_Hatchery, Zerg_Hive, Zerg_Hydralisk_Den,
      Zerg_Infested_Command_Center, Zerg_Lair,
      Zerg_Nydus_Canal, Zerg_Queens_Nest, Zerg_Spawning_Pool, Zerg_Spire, Zerg_Spore_Colony,
      Zerg_Sunken_Colony,
      Zerg_Ultralisk_Cavern, Special_Cerebrate, Special_Cerebrate_Daggoth, Special_Mature_Chrysalis,
      Special_Overmind, Special_Overmind_Cocoon, Special_Overmind_With_Shell, Critter_Bengalaas,
      Critter_Kakaru,
      Critter_Ragnasaur, Critter_Rhynadon, Critter_Scantid, Critter_Ursadon, Resource_Mineral_Field,
      Resource_Mineral_Field_Type_2,
      Resource_Mineral_Field_Type_3, Resource_Vespene_Geyser, Spell_Dark_Swarm,
      Spell_Disruption_Web,
      Spell_Scanner_Sweep, Special_Protoss_Beacon, Special_Protoss_Flag_Beacon,
      Special_Terran_Beacon,
      Special_Terran_Flag_Beacon, Special_Zerg_Beacon, Special_Zerg_Flag_Beacon, Powerup_Data_Disk,
      Powerup_Flag, Powerup_Khalis_Crystal, Powerup_Khaydarin_Crystal,
      Powerup_Mineral_Cluster_Type_1,
      Powerup_Mineral_Cluster_Type_2, Powerup_Protoss_Gas_Orb_Type_1,
      Powerup_Protoss_Gas_Orb_Type_2, Powerup_Psi_Emitter,
      Powerup_Terran_Gas_Tank_Type_1, Powerup_Terran_Gas_Tank_Type_2, Powerup_Uraj_Crystal,
      Powerup_Young_Chrysalis,
      Powerup_Zerg_Gas_Sac_Type_1, Powerup_Zerg_Gas_Sac_Type_2, Special_Floor_Gun_Trap,
      Special_Floor_Missile_Trap,
      Special_Right_Wall_Flame_Trap, Special_Right_Wall_Missile_Trap, Special_Wall_Flame_Trap,
      Special_Wall_Missile_Trap, Special_Pit_Door, Special_Right_Pit_Door,
      Special_Right_Upper_Level_Door,
      Special_Upper_Level_Door, Special_Cargo_Ship, Special_Floor_Hatch,
      Special_Independant_Starport,
      Special_Map_Revealer, Special_Mercenary_Gunship, Special_Start_Location, None, AllUnits,
      Men, Buildings, Factories, Unknown);
  private static final Map<Key, WUnitType> register = new HashMap<>();

  private WUnitType(UnitType scInstance) {
    super(scInstance, getIndexInList(UNIT_TYPES, scInstance));
  }

  public static WUnitType getOrCreateWrapper(UnitType unitType) {
    return getOrCreateWrapper(unitType, register,
        scInstance -> new Key(getIndexInList(UNIT_TYPES, scInstance)), WUnitType::new);
  }

}
