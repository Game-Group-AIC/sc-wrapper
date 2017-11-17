package gg.fel.cvut.cz.wrappers;


import bwapi.UnitType;

import java.util.Arrays;
import java.util.List;

import static bwapi.UnitType.*;

public class WUnitType extends WrapperForClassWithID<UnitType> {
    private final static List<UnitType> UNIT_TYPES = Arrays.asList(Terran_Firebat, Terran_Ghost,
            Terran_Goliath, Terran_Marine, Terran_Medic, Terran_SCV, Terran_Siege_Tank_Siege_Mode,
            Terran_Siege_Tank_Tank_Mode, Terran_Vulture, Terran_Vulture_Spider_Mine, Terran_Battlecruiser,
            Terran_Dropship, Terran_Nuclear_Missile, Terran_Science_Vessel, Terran_Valkyrie, Terran_Wraith,
            Hero_Alan_Schezar, Hero_Alexei_Stukov, Hero_Arcturus_Mengsk, Hero_Edmund_Duke_Tank_Mode,
            Hero_Edmund_Duke_Siege_Mode, Hero_Gerard_DuGalle, Hero_Gui_Montag, Hero_Hyperion,
            Hero_Jim_Raynor_Marine, Hero_Jim_Raynor_Vulture, Hero_Magellan, Hero_Norad_II, Hero_Samir_Duran,
            Hero_Sarah_Kerrigan, Hero_Tom_Kazansky, Terran_Civilian, Terran_Academy, Terran_Armory,
            Terran_Barracks, Terran_Bunker, Terran_Command_Center, Terran_Engineering_Bay, Terran_Factory,
            Terran_Missile_Turret, Terran_Refinery, Terran_Science_Facility, Terran_Starport, Terran_Supply_Depot,
            Terran_Comsat_Station, Terran_Control_Tower, Terran_Covert_Ops, Terran_Machine_Shop,
            Terran_Nuclear_Silo, Terran_Physics_Lab, Special_Crashed_Norad_II, Special_Ion_Cannon, Special_Power_Generator,
            Special_Psi_Disrupter, Protoss_Archon, Protoss_Dark_Archon, Protoss_Dark_Templar, Protoss_Dragoon,
            Protoss_High_Templar, Protoss_Probe, Protoss_Reaver, Protoss_Scarab, Protoss_Zealot, Protoss_Arbiter,
            Protoss_Carrier, Protoss_Corsair, Protoss_Interceptor, Protoss_Observer, Protoss_Scout, Protoss_Shuttle,
            Hero_Aldaris, Hero_Artanis, Hero_Danimoth, Hero_Dark_Templar, Hero_Fenix_Dragoon, Hero_Fenix_Zealot,
            Hero_Gantrithor, Hero_Mojo, Hero_Raszagal, Hero_Tassadar, Hero_Tassadar_Zeratul_Archon, Hero_Warbringer,
            Hero_Zeratul, Protoss_Arbiter_Tribunal, Protoss_Assimilator, Protoss_Citadel_of_Adun, Protoss_Cybernetics_Core,
            Protoss_Fleet_Beacon, Protoss_Forge, Protoss_Gateway, Protoss_Nexus, Protoss_Observatory, Protoss_Photon_Cannon,
            Protoss_Pylon, Protoss_Robotics_Facility, Protoss_Robotics_Support_Bay, Protoss_Shield_Battery,
            Protoss_Stargate, Protoss_Templar_Archives, Special_Khaydarin_Crystal_Form, Special_Protoss_Temple,
            Special_Stasis_Cell_Prison, Special_Warp_Gate, Special_XelNaga_Temple, Zerg_Broodling, Zerg_Defiler,
            Zerg_Drone, Zerg_Egg, Zerg_Hydralisk, Zerg_Infested_Terran, Zerg_Larva, Zerg_Lurker, Zerg_Lurker_Egg,
            Zerg_Ultralisk, Zerg_Zergling, Zerg_Cocoon, Zerg_Devourer, Zerg_Guardian, Zerg_Mutalisk, Zerg_Overlord,
            Zerg_Queen, Zerg_Scourge, Hero_Devouring_One, Hero_Hunter_Killer, Hero_Infested_Duran, Hero_Infested_Kerrigan,
            Hero_Kukulza_Guardian, Hero_Kukulza_Mutalisk, Hero_Matriarch, Hero_Torrasque, Hero_Unclean_One,
            Hero_Yggdrasill, Zerg_Creep_Colony, Zerg_Defiler_Mound, Zerg_Evolution_Chamber, Zerg_Extractor,
            Zerg_Greater_Spire, Zerg_Hatchery, Zerg_Hive, Zerg_Hydralisk_Den, Zerg_Infested_Command_Center, Zerg_Lair,
            Zerg_Nydus_Canal, Zerg_Queens_Nest, Zerg_Spawning_Pool, Zerg_Spire, Zerg_Spore_Colony, Zerg_Sunken_Colony,
            Zerg_Ultralisk_Cavern, Special_Cerebrate, Special_Cerebrate_Daggoth, Special_Mature_Chrysalis,
            Special_Overmind, Special_Overmind_Cocoon, Special_Overmind_With_Shell, Critter_Bengalaas, Critter_Kakaru,
            Critter_Ragnasaur, Critter_Rhynadon, Critter_Scantid, Critter_Ursadon, Resource_Mineral_Field, Resource_Mineral_Field_Type_2,
            Resource_Mineral_Field_Type_3, Resource_Vespene_Geyser, Spell_Dark_Swarm, Spell_Disruption_Web,
            Spell_Scanner_Sweep, Special_Protoss_Beacon, Special_Protoss_Flag_Beacon, Special_Terran_Beacon,
            Special_Terran_Flag_Beacon, Special_Zerg_Beacon, Special_Zerg_Flag_Beacon, Powerup_Data_Disk,
            Powerup_Flag, Powerup_Khalis_Crystal, Powerup_Khaydarin_Crystal, Powerup_Mineral_Cluster_Type_1,
            Powerup_Mineral_Cluster_Type_2, Powerup_Protoss_Gas_Orb_Type_1, Powerup_Protoss_Gas_Orb_Type_2, Powerup_Psi_Emitter,
            Powerup_Terran_Gas_Tank_Type_1, Powerup_Terran_Gas_Tank_Type_2, Powerup_Uraj_Crystal, Powerup_Young_Chrysalis,
            Powerup_Zerg_Gas_Sac_Type_1, Powerup_Zerg_Gas_Sac_Type_2, Special_Floor_Gun_Trap, Special_Floor_Missile_Trap,
            Special_Right_Wall_Flame_Trap, Special_Right_Wall_Missile_Trap, Special_Wall_Flame_Trap,
            Special_Wall_Missile_Trap, Special_Pit_Door, Special_Right_Pit_Door, Special_Right_Upper_Level_Door,
            Special_Upper_Level_Door, Special_Cargo_Ship, Special_Floor_Hatch, Special_Independant_Starport,
            Special_Map_Revealer, Special_Mercenary_Gunship, Special_Start_Location, None, AllUnits,
            Men, Buildings, Factories, Unknown);

    public WUnitType(UnitType scInstance) {
        super(scInstance, getIndexInList(UNIT_TYPES, scInstance));
    }
}
