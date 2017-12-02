package gg.fel.cvut.cz.enums;

import bwapi.BulletType;
import bwapi.DamageType;
import bwapi.ExplosionType;
import bwapi.GameType;
import bwapi.Order;
import bwapi.PlayerType;
import bwapi.Race;
import bwapi.TechType;
import bwapi.UnitCommandType;
import bwapi.UnitSizeType;
import bwapi.UnitType;
import bwapi.UpgradeType;
import bwapi.WeaponType;
import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 * Common interface for all types in game with lists of those types
 */
public interface IGameTypes<T, V extends IGameTypes<T, V>> {

  List<Order> ORDERS = ImmutableList
      .of(Order.Die, Order.Stop, Order.Guard, Order.PlayerGuard, Order.TurretGuard,
          Order.BunkerGuard, Order.Move, Order.AttackUnit, Order.AttackTile, Order.Hover,
          Order.AttackMove, Order.InfestedCommandCenter, Order.UnusedNothing, Order.UnusedPowerup,
          Order.TowerGuard, Order.VultureMine, Order.Nothing, Order.CastInfestation,
          Order.InfestingCommandCenter, Order.PlaceBuilding, Order.CreateProtossBuilding,
          Order.ConstructingBuilding, Order.Repair, Order.PlaceAddon, Order.BuildAddon, Order.Train,
          Order.RallyPointUnit, Order.RallyPointTile, Order.ZergBirth, Order.ZergUnitMorph,
          Order.ZergBuildingMorph, Order.IncompleteBuilding, Order.BuildNydusExit,
          Order.EnterNydusCanal, Order.Follow, Order.Carrier, Order.ReaverCarrierMove,
          Order.CarrierIgnore2, Order.Reaver, Order.TrainFighter, Order.InterceptorAttack,
          Order.ScarabAttack, Order.RechargeShieldsUnit, Order.RechargeShieldsBattery,
          Order.ShieldBattery, Order.InterceptorReturn, Order.BuildingLand, Order.BuildingLiftOff,
          Order.DroneLiftOff, Order.LiftingOff, Order.ResearchTech, Order.Upgrade, Order.Larva,
          Order.SpawningLarva, Order.Harvest1, Order.Harvest2, Order.MoveToGas, Order.WaitForGas,
          Order.HarvestGas, Order.ReturnGas, Order.MoveToMinerals, Order.WaitForMinerals,
          Order.MiningMinerals, Order.Harvest3, Order.Harvest4, Order.ReturnMinerals,
          Order.Interrupted, Order.EnterTransport, Order.PickupIdle, Order.PickupTransport,
          Order.PickupBunker, Order.Pickup4, Order.PowerupIdle, Order.Sieging, Order.Unsieging,
          Order.InitCreepGrowth, Order.SpreadCreep, Order.StoppingCreepGrowth, Order.GuardianAspect,
          Order.ArchonWarp, Order.CompletingArchonSummon, Order.HoldPosition, Order.Cloak,
          Order.Decloak, Order.Unload, Order.MoveUnload, Order.FireYamatoGun, Order.CastLockdown,
          Order.Burrowing, Order.Burrowed, Order.Unburrowing, Order.CastDarkSwarm,
          Order.CastParasite, Order.CastSpawnBroodlings, Order.CastEMPShockwave, Order.NukeWait,
          Order.NukeTrain, Order.NukeLaunch, Order.NukePaint, Order.NukeUnit,
          Order.CastNuclearStrike, Order.NukeTrack, Order.CloakNearbyUnits, Order.PlaceMine,
          Order.RightClickAction, Order.CastRecall, Order.Teleport, Order.CastScannerSweep,
          Order.Scanner, Order.CastDefensiveMatrix, Order.CastPsionicStorm, Order.CastIrradiate,
          Order.CastPlague, Order.CastConsume, Order.CastEnsnare, Order.CastStasisField,
          Order.CastHallucination, Order.Hallucination2, Order.ResetCollision, Order.Patrol,
          Order.CTFCOPInit, Order.CTFCOPStarted, Order.CTFCOP2, Order.ComputerAI, Order.AtkMoveEP,
          Order.HarassMove, Order.AIPatrol, Order.GuardPost, Order.RescuePassive, Order.Neutral,
          Order.ComputerReturn, Order.SelfDestructing, Order.Critter, Order.HiddenGun,
          Order.OpenDoor, Order.CloseDoor, Order.HideTrap, Order.RevealTrap, Order.EnableDoodad,
          Order.DisableDoodad, Order.WarpIn, Order.Medic, Order.MedicHeal, Order.HealMove,
          Order.MedicHealToIdle, Order.CastRestoration, Order.CastDisruptionWeb,
          Order.CastMindControl, Order.DarkArchonMeld, Order.CastFeedback, Order.CastOpticalFlare,
          Order.CastMaelstrom, Order.JunkYardDog, Order.Fatal, Order.None, Order.Unknown);
  List<UpgradeType> UPGRADE_TYPES = ImmutableList
      .of(UpgradeType.Terran_Infantry_Armor, UpgradeType.Terran_Vehicle_Plating,
          UpgradeType.Terran_Ship_Plating, UpgradeType.Terran_Infantry_Weapons,
          UpgradeType.Terran_Vehicle_Weapons, UpgradeType.Terran_Ship_Weapons,
          UpgradeType.U_238_Shells, UpgradeType.Ion_Thrusters, UpgradeType.Titan_Reactor,
          UpgradeType.Ocular_Implants, UpgradeType.Moebius_Reactor, UpgradeType.Apollo_Reactor,
          UpgradeType.Colossus_Reactor, UpgradeType.Caduceus_Reactor, UpgradeType.Charon_Boosters,
          UpgradeType.Zerg_Carapace, UpgradeType.Zerg_Flyer_Carapace,
          UpgradeType.Zerg_Melee_Attacks, UpgradeType.Zerg_Missile_Attacks,
          UpgradeType.Zerg_Flyer_Attacks, UpgradeType.Ventral_Sacs, UpgradeType.Antennae,
          UpgradeType.Pneumatized_Carapace, UpgradeType.Metabolic_Boost, UpgradeType.Adrenal_Glands,
          UpgradeType.Muscular_Augments, UpgradeType.Grooved_Spines, UpgradeType.Gamete_Meiosis,
          UpgradeType.Metasynaptic_Node, UpgradeType.Chitinous_Plating,
          UpgradeType.Anabolic_Synthesis, UpgradeType.Protoss_Ground_Armor,
          UpgradeType.Protoss_Air_Armor, UpgradeType.Protoss_Ground_Weapons,
          UpgradeType.Protoss_Air_Weapons, UpgradeType.Protoss_Plasma_Shields,
          UpgradeType.Singularity_Charge, UpgradeType.Leg_Enhancements, UpgradeType.Scarab_Damage,
          UpgradeType.Reaver_Capacity, UpgradeType.Gravitic_Drive, UpgradeType.Sensor_Array,
          UpgradeType.Gravitic_Boosters, UpgradeType.Khaydarin_Amulet, UpgradeType.Apial_Sensors,
          UpgradeType.Gravitic_Thrusters, UpgradeType.Carrier_Capacity, UpgradeType.Khaydarin_Core,
          UpgradeType.Argus_Jewel, UpgradeType.Argus_Talisman, UpgradeType.Upgrade_60,
          UpgradeType.None, UpgradeType.Unknown);
  List<BulletType> BULLET_TYPES = ImmutableList
      .of(BulletType.Melee, BulletType.Fusion_Cutter_Hit, BulletType.Gauss_Rifle_Hit,
          BulletType.C_10_Canister_Rifle_Hit, BulletType.Gemini_Missiles,
          BulletType.Fragmentation_Grenade, BulletType.Longbolt_Missile,
          BulletType.ATS_ATA_Laser_Battery, BulletType.Burst_Lasers,
          BulletType.Arclite_Shock_Cannon_Hit, BulletType.EMP_Missile,
          BulletType.Dual_Photon_Blasters_Hit, BulletType.Particle_Beam_Hit,
          BulletType.Anti_Matter_Missile, BulletType.Pulse_Cannon, BulletType.Psionic_Shockwave_Hit,
          BulletType.Psionic_Storm, BulletType.Yamato_Gun, BulletType.Phase_Disruptor,
          BulletType.STA_STS_Cannon_Overlay, BulletType.Sunken_Colony_Tentacle,
          BulletType.Acid_Spore, BulletType.Glave_Wurm, BulletType.Seeker_Spores,
          BulletType.Queen_Spell_Carrier, BulletType.Plague_Cloud, BulletType.Consume,
          BulletType.Ensnare, BulletType.Needle_Spine_Hit, BulletType.Invisible,
          BulletType.Optical_Flare_Grenade, BulletType.Halo_Rockets, BulletType.Subterranean_Spines,
          BulletType.Corrosive_Acid_Shot, BulletType.Neutron_Flare, BulletType.None,
          BulletType.Unknown);
  List<PlayerType> PLAYER_TYPES = ImmutableList
      .of(PlayerType.None, PlayerType.Computer, PlayerType.Player, PlayerType.RescuePassive,
          PlayerType.EitherPreferComputer, PlayerType.EitherPreferHuman, PlayerType.Neutral,
          PlayerType.Closed, PlayerType.PlayerLeft, PlayerType.ComputerLeft, PlayerType.Unknown);
  List<UnitSizeType> UNIT_SIZE_TYPES = ImmutableList
      .of(UnitSizeType.Independent, UnitSizeType.Small, UnitSizeType.Medium, UnitSizeType.Large,
          UnitSizeType.None, UnitSizeType.Unknown);
  List<GameType> GAME_TYPES = ImmutableList
      .of(GameType.Melee, GameType.Free_For_All, GameType.One_on_One, GameType.Capture_The_Flag,
          GameType.Greed, GameType.Slaughter, GameType.Sudden_Death, GameType.Ladder,
          GameType.Use_Map_Settings, GameType.Team_Melee, GameType.Team_Free_For_All,
          GameType.Team_Capture_The_Flag, GameType.Top_vs_Bottom, GameType.None, GameType.Unknown);
  List<DamageType> DAMAGE_TYPES = ImmutableList
      .of(DamageType.Independent, DamageType.Explosive, DamageType.Concussive,
          DamageType.Normal, DamageType.Ignore_Armor, DamageType.None, DamageType.Unknown);
  List<ExplosionType> EXPLOSION_TYPES = ImmutableList
      .of(ExplosionType.None, ExplosionType.Normal, ExplosionType.Radial_Splash,
          ExplosionType.Enemy_Splash, ExplosionType.Lockdown, ExplosionType.Nuclear_Missile,
          ExplosionType.Parasite, ExplosionType.Broodlings, ExplosionType.EMP_Shockwave,
          ExplosionType.Irradiate, ExplosionType.Ensnare, ExplosionType.Plague,
          ExplosionType.Stasis_Field, ExplosionType.Dark_Swarm, ExplosionType.Consume,
          ExplosionType.Yamato_Gun, ExplosionType.Restoration, ExplosionType.Disruption_Web,
          ExplosionType.Corrosive_Acid, ExplosionType.Mind_Control, ExplosionType.Feedback,
          ExplosionType.Optical_Flare, ExplosionType.Maelstrom, ExplosionType.Air_Splash,
          ExplosionType.Unknown);
  List<UnitCommandType> UNIT_COMMAND_TYPES = ImmutableList
      .of(UnitCommandType.Attack_Move, UnitCommandType.Attack_Unit, UnitCommandType.Build,
          UnitCommandType.Build_Addon, UnitCommandType.Train, UnitCommandType.Morph,
          UnitCommandType.Research, UnitCommandType.Upgrade, UnitCommandType.Set_Rally_Position,
          UnitCommandType.Set_Rally_Unit, UnitCommandType.Move, UnitCommandType.Patrol,
          UnitCommandType.Hold_Position, UnitCommandType.Stop, UnitCommandType.Follow,
          UnitCommandType.Gather, UnitCommandType.Return_Cargo, UnitCommandType.Repair,
          UnitCommandType.Burrow, UnitCommandType.Unburrow, UnitCommandType.Cloak,
          UnitCommandType.Decloak, UnitCommandType.Siege, UnitCommandType.Unsiege,
          UnitCommandType.Lift, UnitCommandType.Land, UnitCommandType.Load, UnitCommandType.Unload,
          UnitCommandType.Unload_All, UnitCommandType.Unload_All_Position,
          UnitCommandType.Right_Click_Position, UnitCommandType.Right_Click_Unit,
          UnitCommandType.Halt_Construction, UnitCommandType.Cancel_Construction,
          UnitCommandType.Cancel_Addon, UnitCommandType.Cancel_Train,
          UnitCommandType.Cancel_Train_Slot, UnitCommandType.Cancel_Morph,
          UnitCommandType.Cancel_Research, UnitCommandType.Cancel_Upgrade, UnitCommandType.Use_Tech,
          UnitCommandType.Use_Tech_Position, UnitCommandType.Use_Tech_Unit,
          UnitCommandType.Place_COP, UnitCommandType.None, UnitCommandType.Unknown);
  List<UnitType> UNIT_TYPES = ImmutableList
      .of(UnitType.Terran_Firebat, UnitType.Terran_Ghost, UnitType.Terran_Goliath,
          UnitType.Terran_Marine, UnitType.Terran_Medic, UnitType.Terran_SCV,
          UnitType.Terran_Siege_Tank_Siege_Mode, UnitType.Terran_Siege_Tank_Tank_Mode,
          UnitType.Terran_Vulture, UnitType.Terran_Vulture_Spider_Mine,
          UnitType.Terran_Battlecruiser, UnitType.Terran_Dropship, UnitType.Terran_Nuclear_Missile,
          UnitType.Terran_Science_Vessel, UnitType.Terran_Valkyrie, UnitType.Terran_Wraith,
          UnitType.Hero_Alan_Schezar, UnitType.Hero_Alexei_Stukov, UnitType.Hero_Arcturus_Mengsk,
          UnitType.Hero_Edmund_Duke_Tank_Mode, UnitType.Hero_Edmund_Duke_Siege_Mode,
          UnitType.Hero_Gerard_DuGalle, UnitType.Hero_Gui_Montag, UnitType.Hero_Hyperion,
          UnitType.Hero_Jim_Raynor_Marine, UnitType.Hero_Jim_Raynor_Vulture, UnitType.Hero_Magellan,
          UnitType.Hero_Norad_II, UnitType.Hero_Samir_Duran, UnitType.Hero_Sarah_Kerrigan,
          UnitType.Hero_Tom_Kazansky, UnitType.Terran_Civilian, UnitType.Terran_Academy,
          UnitType.Terran_Armory, UnitType.Terran_Barracks, UnitType.Terran_Bunker,
          UnitType.Terran_Command_Center, UnitType.Terran_Engineering_Bay, UnitType.Terran_Factory,
          UnitType.Terran_Missile_Turret, UnitType.Terran_Refinery,
          UnitType.Terran_Science_Facility, UnitType.Terran_Starport, UnitType.Terran_Supply_Depot,
          UnitType.Terran_Comsat_Station, UnitType.Terran_Control_Tower, UnitType.Terran_Covert_Ops,
          UnitType.Terran_Machine_Shop, UnitType.Terran_Nuclear_Silo, UnitType.Terran_Physics_Lab,
          UnitType.Special_Crashed_Norad_II, UnitType.Special_Ion_Cannon,
          UnitType.Special_Power_Generator, UnitType.Special_Psi_Disrupter, UnitType.Protoss_Archon,
          UnitType.Protoss_Dark_Archon, UnitType.Protoss_Dark_Templar, UnitType.Protoss_Dragoon,
          UnitType.Protoss_High_Templar, UnitType.Protoss_Probe, UnitType.Protoss_Reaver,
          UnitType.Protoss_Scarab, UnitType.Protoss_Zealot, UnitType.Protoss_Arbiter,
          UnitType.Protoss_Carrier, UnitType.Protoss_Corsair, UnitType.Protoss_Interceptor,
          UnitType.Protoss_Observer, UnitType.Protoss_Scout, UnitType.Protoss_Shuttle,
          UnitType.Hero_Aldaris, UnitType.Hero_Artanis, UnitType.Hero_Danimoth,
          UnitType.Hero_Dark_Templar, UnitType.Hero_Fenix_Dragoon, UnitType.Hero_Fenix_Zealot,
          UnitType.Hero_Gantrithor, UnitType.Hero_Mojo, UnitType.Hero_Raszagal,
          UnitType.Hero_Tassadar, UnitType.Hero_Tassadar_Zeratul_Archon, UnitType.Hero_Warbringer,
          UnitType.Hero_Zeratul, UnitType.Protoss_Arbiter_Tribunal, UnitType.Protoss_Assimilator,
          UnitType.Protoss_Citadel_of_Adun, UnitType.Protoss_Cybernetics_Core,
          UnitType.Protoss_Fleet_Beacon, UnitType.Protoss_Forge, UnitType.Protoss_Gateway,
          UnitType.Protoss_Nexus, UnitType.Protoss_Observatory, UnitType.Protoss_Photon_Cannon,
          UnitType.Protoss_Pylon, UnitType.Protoss_Robotics_Facility,
          UnitType.Protoss_Robotics_Support_Bay, UnitType.Protoss_Shield_Battery,
          UnitType.Protoss_Stargate, UnitType.Protoss_Templar_Archives,
          UnitType.Special_Khaydarin_Crystal_Form, UnitType.Special_Protoss_Temple,
          UnitType.Special_Stasis_Cell_Prison, UnitType.Special_Warp_Gate,
          UnitType.Special_XelNaga_Temple, UnitType.Zerg_Broodling, UnitType.Zerg_Defiler,
          UnitType.Zerg_Drone, UnitType.Zerg_Egg, UnitType.Zerg_Hydralisk,
          UnitType.Zerg_Infested_Terran, UnitType.Zerg_Larva, UnitType.Zerg_Lurker,
          UnitType.Zerg_Lurker_Egg, UnitType.Zerg_Ultralisk, UnitType.Zerg_Zergling,
          UnitType.Zerg_Cocoon, UnitType.Zerg_Devourer, UnitType.Zerg_Guardian,
          UnitType.Zerg_Mutalisk, UnitType.Zerg_Overlord, UnitType.Zerg_Queen,
          UnitType.Zerg_Scourge, UnitType.Hero_Devouring_One, UnitType.Hero_Hunter_Killer,
          UnitType.Hero_Infested_Duran, UnitType.Hero_Infested_Kerrigan,
          UnitType.Hero_Kukulza_Guardian, UnitType.Hero_Kukulza_Mutalisk, UnitType.Hero_Matriarch,
          UnitType.Hero_Torrasque, UnitType.Hero_Unclean_One, UnitType.Hero_Yggdrasill,
          UnitType.Zerg_Creep_Colony, UnitType.Zerg_Defiler_Mound, UnitType.Zerg_Evolution_Chamber,
          UnitType.Zerg_Extractor, UnitType.Zerg_Greater_Spire, UnitType.Zerg_Hatchery,
          UnitType.Zerg_Hive, UnitType.Zerg_Hydralisk_Den, UnitType.Zerg_Infested_Command_Center,
          UnitType.Zerg_Lair, UnitType.Zerg_Nydus_Canal, UnitType.Zerg_Queens_Nest,
          UnitType.Zerg_Spawning_Pool, UnitType.Zerg_Spire, UnitType.Zerg_Spore_Colony,
          UnitType.Zerg_Sunken_Colony, UnitType.Zerg_Ultralisk_Cavern, UnitType.Special_Cerebrate,
          UnitType.Special_Cerebrate_Daggoth, UnitType.Special_Mature_Chrysalis,
          UnitType.Special_Overmind, UnitType.Special_Overmind_Cocoon,
          UnitType.Special_Overmind_With_Shell, UnitType.Critter_Bengalaas, UnitType.Critter_Kakaru,
          UnitType.Critter_Ragnasaur, UnitType.Critter_Rhynadon, UnitType.Critter_Scantid,
          UnitType.Critter_Ursadon, UnitType.Resource_Mineral_Field,
          UnitType.Resource_Mineral_Field_Type_2, UnitType.Resource_Mineral_Field_Type_3,
          UnitType.Resource_Vespene_Geyser, UnitType.Spell_Dark_Swarm,
          UnitType.Spell_Disruption_Web, UnitType.Spell_Scanner_Sweep,
          UnitType.Special_Protoss_Beacon, UnitType.Special_Protoss_Flag_Beacon,
          UnitType.Special_Terran_Beacon, UnitType.Special_Terran_Flag_Beacon,
          UnitType.Special_Zerg_Beacon, UnitType.Special_Zerg_Flag_Beacon,
          UnitType.Powerup_Data_Disk, UnitType.Powerup_Flag, UnitType.Powerup_Khalis_Crystal,
          UnitType.Powerup_Khaydarin_Crystal, UnitType.Powerup_Mineral_Cluster_Type_1,
          UnitType.Powerup_Mineral_Cluster_Type_2, UnitType.Powerup_Protoss_Gas_Orb_Type_1,
          UnitType.Powerup_Protoss_Gas_Orb_Type_2, UnitType.Powerup_Psi_Emitter,
          UnitType.Powerup_Terran_Gas_Tank_Type_1, UnitType.Powerup_Terran_Gas_Tank_Type_2,
          UnitType.Powerup_Uraj_Crystal, UnitType.Powerup_Young_Chrysalis,
          UnitType.Powerup_Zerg_Gas_Sac_Type_1, UnitType.Powerup_Zerg_Gas_Sac_Type_2,
          UnitType.Special_Floor_Gun_Trap, UnitType.Special_Floor_Missile_Trap,
          UnitType.Special_Right_Wall_Flame_Trap, UnitType.Special_Right_Wall_Missile_Trap,
          UnitType.Special_Wall_Flame_Trap, UnitType.Special_Wall_Missile_Trap,
          UnitType.Special_Pit_Door, UnitType.Special_Right_Pit_Door,
          UnitType.Special_Right_Upper_Level_Door, UnitType.Special_Upper_Level_Door,
          UnitType.Special_Cargo_Ship, UnitType.Special_Floor_Hatch,
          UnitType.Special_Independant_Starport, UnitType.Special_Map_Revealer,
          UnitType.Special_Mercenary_Gunship, UnitType.Special_Start_Location, UnitType.None,
          UnitType.AllUnits, UnitType.Men, UnitType.Buildings, UnitType.Factories,
          UnitType.Unknown);
  List<Race> RACES = ImmutableList
      .of(Race.Zerg, Race.Terran, Race.Protoss, Race.Random, Race.None, Race.Unknown);
  List<WeaponType> WEAPON_TYPES = ImmutableList
      .of(WeaponType.Gauss_Rifle, WeaponType.Gauss_Rifle_Jim_Raynor,
          WeaponType.C_10_Canister_Rifle, WeaponType.C_10_Canister_Rifle_Sarah_Kerrigan,
          WeaponType.C_10_Canister_Rifle_Samir_Duran, WeaponType.C_10_Canister_Rifle_Infested_Duran,
          WeaponType.C_10_Canister_Rifle_Alexei_Stukov, WeaponType.Fragmentation_Grenade,
          WeaponType.Fragmentation_Grenade_Jim_Raynor, WeaponType.Spider_Mines,
          WeaponType.Twin_Autocannons, WeaponType.Twin_Autocannons_Alan_Schezar,
          WeaponType.Hellfire_Missile_Pack, WeaponType.Hellfire_Missile_Pack_Alan_Schezar,
          WeaponType.Arclite_Cannon, WeaponType.Arclite_Cannon_Edmund_Duke,
          WeaponType.Fusion_Cutter, WeaponType.Gemini_Missiles,
          WeaponType.Gemini_Missiles_Tom_Kazansky, WeaponType.Burst_Lasers,
          WeaponType.Burst_Lasers_Tom_Kazansky, WeaponType.ATS_Laser_Battery,
          WeaponType.ATS_Laser_Battery_Hero, WeaponType.ATS_Laser_Battery_Hyperion,
          WeaponType.ATA_Laser_Battery, WeaponType.ATA_Laser_Battery_Hero,
          WeaponType.ATA_Laser_Battery_Hyperion, WeaponType.Flame_Thrower,
          WeaponType.Flame_Thrower_Gui_Montag, WeaponType.Arclite_Shock_Cannon,
          WeaponType.Arclite_Shock_Cannon_Edmund_Duke, WeaponType.Longbolt_Missile,
          WeaponType.Claws, WeaponType.Claws_Devouring_One, WeaponType.Claws_Infested_Kerrigan,
          WeaponType.Needle_Spines, WeaponType.Needle_Spines_Hunter_Killer,
          WeaponType.Kaiser_Blades, WeaponType.Kaiser_Blades_Torrasque, WeaponType.Toxic_Spores,
          WeaponType.Spines, WeaponType.Acid_Spore, WeaponType.Acid_Spore_Kukulza,
          WeaponType.Glave_Wurm, WeaponType.Glave_Wurm_Kukulza, WeaponType.Seeker_Spores,
          WeaponType.Subterranean_Tentacle, WeaponType.Suicide_Infested_Terran,
          WeaponType.Suicide_Scourge, WeaponType.Particle_Beam, WeaponType.Psi_Blades,
          WeaponType.Psi_Blades_Fenix, WeaponType.Phase_Disruptor, WeaponType.Phase_Disruptor_Fenix,
          WeaponType.Psi_Assault, WeaponType.Psionic_Shockwave,
          WeaponType.Psionic_Shockwave_TZ_Archon, WeaponType.Dual_Photon_Blasters,
          WeaponType.Dual_Photon_Blasters_Mojo, WeaponType.Dual_Photon_Blasters_Artanis,
          WeaponType.Anti_Matter_Missiles, WeaponType.Anti_Matter_Missiles_Mojo,
          WeaponType.Anti_Matter_Missiles_Artanis, WeaponType.Phase_Disruptor_Cannon,
          WeaponType.Phase_Disruptor_Cannon_Danimoth, WeaponType.Pulse_Cannon,
          WeaponType.STS_Photon_Cannon, WeaponType.STA_Photon_Cannon, WeaponType.Scarab,
          WeaponType.Neutron_Flare, WeaponType.Halo_Rockets, WeaponType.Corrosive_Acid,
          WeaponType.Subterranean_Spines, WeaponType.Warp_Blades, WeaponType.Warp_Blades_Hero,
          WeaponType.Warp_Blades_Zeratul, WeaponType.Independant_Laser_Battery,
          WeaponType.Twin_Autocannons_Floor_Trap, WeaponType.Hellfire_Missile_Pack_Wall_Trap,
          WeaponType.Flame_Thrower_Wall_Trap, WeaponType.Hellfire_Missile_Pack_Floor_Trap,
          WeaponType.Yamato_Gun, WeaponType.Nuclear_Strike, WeaponType.Lockdown,
          WeaponType.EMP_Shockwave, WeaponType.Irradiate, WeaponType.Parasite,
          WeaponType.Spawn_Broodlings, WeaponType.Ensnare, WeaponType.Dark_Swarm, WeaponType.Plague,
          WeaponType.Consume, WeaponType.Stasis_Field, WeaponType.Psionic_Storm,
          WeaponType.Disruption_Web, WeaponType.Restoration, WeaponType.Mind_Control,
          WeaponType.Feedback, WeaponType.Optical_Flare, WeaponType.Maelstrom, WeaponType.None,
          WeaponType.Unknown);
  List<TechType> TECH_TYPES = ImmutableList
      .of(TechType.Stim_Packs, TechType.Lockdown, TechType.EMP_Shockwave, TechType.Spider_Mines,
          TechType.Scanner_Sweep, TechType.Tank_Siege_Mode, TechType.Defensive_Matrix,
          TechType.Irradiate, TechType.Yamato_Gun, TechType.Cloaking_Field,
          TechType.Personnel_Cloaking, TechType.Restoration, TechType.Optical_Flare,
          TechType.Healing, TechType.Nuclear_Strike, TechType.Burrowing, TechType.Infestation,
          TechType.Spawn_Broodlings, TechType.Dark_Swarm, TechType.Plague, TechType.Consume,
          TechType.Ensnare, TechType.Parasite, TechType.Lurker_Aspect, TechType.Psionic_Storm,
          TechType.Hallucination, TechType.Recall, TechType.Stasis_Field, TechType.Archon_Warp,
          TechType.Disruption_Web, TechType.Mind_Control, TechType.Dark_Archon_Meld,
          TechType.Feedback, TechType.Maelstrom, TechType.None, TechType.Unknown);

  int ordinal();

  default T getBWType() {
    return getTypes().get(ordinal());
  }

  List<T> getTypes();

  V[] getValues();

  /**
   * Get corresponding type to bwapi type
   */
  default V getOurType(T scType) {
    return getValues()[getIndexInList(getTypes(), scType)];
  }

  /**
   * Hack - for types there are static readonly - to use some meaningful ID for wrappers of those
   * readonly, their order in list is used
   */
  static <V> int getIndexInList(List<V> listWithInstances, V instance) {
    for (int i = 0; i < listWithInstances.size(); i++) {
      if (listWithInstances.get(i) == instance) {
        return i;
      }
    }
    return -1;
  }

}
