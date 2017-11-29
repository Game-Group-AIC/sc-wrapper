package gg.fel.cvut.cz.wrappers;


import static bwapi.WeaponType.ATA_Laser_Battery;
import static bwapi.WeaponType.ATA_Laser_Battery_Hero;
import static bwapi.WeaponType.ATA_Laser_Battery_Hyperion;
import static bwapi.WeaponType.ATS_Laser_Battery;
import static bwapi.WeaponType.ATS_Laser_Battery_Hero;
import static bwapi.WeaponType.ATS_Laser_Battery_Hyperion;
import static bwapi.WeaponType.Acid_Spore;
import static bwapi.WeaponType.Acid_Spore_Kukulza;
import static bwapi.WeaponType.Anti_Matter_Missiles;
import static bwapi.WeaponType.Anti_Matter_Missiles_Artanis;
import static bwapi.WeaponType.Anti_Matter_Missiles_Mojo;
import static bwapi.WeaponType.Arclite_Cannon;
import static bwapi.WeaponType.Arclite_Cannon_Edmund_Duke;
import static bwapi.WeaponType.Arclite_Shock_Cannon;
import static bwapi.WeaponType.Arclite_Shock_Cannon_Edmund_Duke;
import static bwapi.WeaponType.Burst_Lasers;
import static bwapi.WeaponType.Burst_Lasers_Tom_Kazansky;
import static bwapi.WeaponType.C_10_Canister_Rifle;
import static bwapi.WeaponType.C_10_Canister_Rifle_Alexei_Stukov;
import static bwapi.WeaponType.C_10_Canister_Rifle_Infested_Duran;
import static bwapi.WeaponType.C_10_Canister_Rifle_Samir_Duran;
import static bwapi.WeaponType.C_10_Canister_Rifle_Sarah_Kerrigan;
import static bwapi.WeaponType.Claws;
import static bwapi.WeaponType.Claws_Devouring_One;
import static bwapi.WeaponType.Claws_Infested_Kerrigan;
import static bwapi.WeaponType.Consume;
import static bwapi.WeaponType.Corrosive_Acid;
import static bwapi.WeaponType.Dark_Swarm;
import static bwapi.WeaponType.Disruption_Web;
import static bwapi.WeaponType.Dual_Photon_Blasters;
import static bwapi.WeaponType.Dual_Photon_Blasters_Artanis;
import static bwapi.WeaponType.Dual_Photon_Blasters_Mojo;
import static bwapi.WeaponType.EMP_Shockwave;
import static bwapi.WeaponType.Ensnare;
import static bwapi.WeaponType.Feedback;
import static bwapi.WeaponType.Flame_Thrower;
import static bwapi.WeaponType.Flame_Thrower_Gui_Montag;
import static bwapi.WeaponType.Flame_Thrower_Wall_Trap;
import static bwapi.WeaponType.Fragmentation_Grenade;
import static bwapi.WeaponType.Fragmentation_Grenade_Jim_Raynor;
import static bwapi.WeaponType.Fusion_Cutter;
import static bwapi.WeaponType.Gauss_Rifle;
import static bwapi.WeaponType.Gauss_Rifle_Jim_Raynor;
import static bwapi.WeaponType.Gemini_Missiles;
import static bwapi.WeaponType.Gemini_Missiles_Tom_Kazansky;
import static bwapi.WeaponType.Glave_Wurm;
import static bwapi.WeaponType.Glave_Wurm_Kukulza;
import static bwapi.WeaponType.Halo_Rockets;
import static bwapi.WeaponType.Hellfire_Missile_Pack;
import static bwapi.WeaponType.Hellfire_Missile_Pack_Alan_Schezar;
import static bwapi.WeaponType.Hellfire_Missile_Pack_Floor_Trap;
import static bwapi.WeaponType.Hellfire_Missile_Pack_Wall_Trap;
import static bwapi.WeaponType.Independant_Laser_Battery;
import static bwapi.WeaponType.Irradiate;
import static bwapi.WeaponType.Kaiser_Blades;
import static bwapi.WeaponType.Kaiser_Blades_Torrasque;
import static bwapi.WeaponType.Lockdown;
import static bwapi.WeaponType.Longbolt_Missile;
import static bwapi.WeaponType.Maelstrom;
import static bwapi.WeaponType.Mind_Control;
import static bwapi.WeaponType.Needle_Spines;
import static bwapi.WeaponType.Needle_Spines_Hunter_Killer;
import static bwapi.WeaponType.Neutron_Flare;
import static bwapi.WeaponType.None;
import static bwapi.WeaponType.Nuclear_Strike;
import static bwapi.WeaponType.Optical_Flare;
import static bwapi.WeaponType.Parasite;
import static bwapi.WeaponType.Particle_Beam;
import static bwapi.WeaponType.Phase_Disruptor;
import static bwapi.WeaponType.Phase_Disruptor_Cannon;
import static bwapi.WeaponType.Phase_Disruptor_Cannon_Danimoth;
import static bwapi.WeaponType.Phase_Disruptor_Fenix;
import static bwapi.WeaponType.Plague;
import static bwapi.WeaponType.Psi_Assault;
import static bwapi.WeaponType.Psi_Blades;
import static bwapi.WeaponType.Psi_Blades_Fenix;
import static bwapi.WeaponType.Psionic_Shockwave;
import static bwapi.WeaponType.Psionic_Shockwave_TZ_Archon;
import static bwapi.WeaponType.Psionic_Storm;
import static bwapi.WeaponType.Pulse_Cannon;
import static bwapi.WeaponType.Restoration;
import static bwapi.WeaponType.STA_Photon_Cannon;
import static bwapi.WeaponType.STS_Photon_Cannon;
import static bwapi.WeaponType.Scarab;
import static bwapi.WeaponType.Seeker_Spores;
import static bwapi.WeaponType.Spawn_Broodlings;
import static bwapi.WeaponType.Spider_Mines;
import static bwapi.WeaponType.Spines;
import static bwapi.WeaponType.Stasis_Field;
import static bwapi.WeaponType.Subterranean_Spines;
import static bwapi.WeaponType.Subterranean_Tentacle;
import static bwapi.WeaponType.Suicide_Infested_Terran;
import static bwapi.WeaponType.Suicide_Scourge;
import static bwapi.WeaponType.Toxic_Spores;
import static bwapi.WeaponType.Twin_Autocannons;
import static bwapi.WeaponType.Twin_Autocannons_Alan_Schezar;
import static bwapi.WeaponType.Twin_Autocannons_Floor_Trap;
import static bwapi.WeaponType.Unknown;
import static bwapi.WeaponType.Warp_Blades;
import static bwapi.WeaponType.Warp_Blades_Hero;
import static bwapi.WeaponType.Warp_Blades_Zeratul;
import static bwapi.WeaponType.Yamato_Gun;

import bwapi.WeaponType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WWeaponType extends WrapperForClassWithID<WeaponType> {

  private final static List<WeaponType> WEAPON_TYPES = Arrays
      .asList(Gauss_Rifle, Gauss_Rifle_Jim_Raynor, C_10_Canister_Rifle,
          C_10_Canister_Rifle_Sarah_Kerrigan, C_10_Canister_Rifle_Samir_Duran,
          C_10_Canister_Rifle_Infested_Duran, C_10_Canister_Rifle_Alexei_Stukov,
          Fragmentation_Grenade, Fragmentation_Grenade_Jim_Raynor, Spider_Mines, Twin_Autocannons,
          Twin_Autocannons_Alan_Schezar,
          Hellfire_Missile_Pack, Hellfire_Missile_Pack_Alan_Schezar, Arclite_Cannon,
          Arclite_Cannon_Edmund_Duke, Fusion_Cutter,
          Gemini_Missiles, Gemini_Missiles_Tom_Kazansky, Burst_Lasers, Burst_Lasers_Tom_Kazansky,
          ATS_Laser_Battery,
          ATS_Laser_Battery_Hero, ATS_Laser_Battery_Hyperion, ATA_Laser_Battery,
          ATA_Laser_Battery_Hero, ATA_Laser_Battery_Hyperion,
          Flame_Thrower, Flame_Thrower_Gui_Montag, Arclite_Shock_Cannon,
          Arclite_Shock_Cannon_Edmund_Duke, Longbolt_Missile,
          Claws, Claws_Devouring_One, Claws_Infested_Kerrigan, Needle_Spines,
          Needle_Spines_Hunter_Killer, Kaiser_Blades, Kaiser_Blades_Torrasque,
          Toxic_Spores, Spines, Acid_Spore, Acid_Spore_Kukulza, Glave_Wurm, Glave_Wurm_Kukulza,
          Seeker_Spores, Subterranean_Tentacle,
          Suicide_Infested_Terran, Suicide_Scourge, Particle_Beam, Psi_Blades, Psi_Blades_Fenix,
          Phase_Disruptor, Phase_Disruptor_Fenix,
          Psi_Assault, Psionic_Shockwave, Psionic_Shockwave_TZ_Archon, Dual_Photon_Blasters,
          Dual_Photon_Blasters_Mojo,
          Dual_Photon_Blasters_Artanis, Anti_Matter_Missiles, Anti_Matter_Missiles_Mojo,
          Anti_Matter_Missiles_Artanis,
          Phase_Disruptor_Cannon, Phase_Disruptor_Cannon_Danimoth, Pulse_Cannon, STS_Photon_Cannon,
          STA_Photon_Cannon,
          Scarab, Neutron_Flare, Halo_Rockets, Corrosive_Acid, Subterranean_Spines, Warp_Blades,
          Warp_Blades_Hero, Warp_Blades_Zeratul,
          Independant_Laser_Battery, Twin_Autocannons_Floor_Trap, Hellfire_Missile_Pack_Wall_Trap,
          Flame_Thrower_Wall_Trap,
          Hellfire_Missile_Pack_Floor_Trap, Yamato_Gun, Nuclear_Strike, Lockdown, EMP_Shockwave,
          Irradiate, Parasite,
          Spawn_Broodlings, Ensnare, Dark_Swarm, Plague, Consume, Stasis_Field, Psionic_Storm,
          Disruption_Web, Restoration,
          Mind_Control, Feedback, Optical_Flare, Maelstrom, None, Unknown);
  private static final Map<Key, WWeaponType> register = new HashMap<>();

  private WWeaponType(WeaponType scInstance) {
    super(scInstance, getIndexInList(WEAPON_TYPES, scInstance));
  }

  public static WWeaponType getOrCreateWrapper(WeaponType weaponType) {
    return getOrCreateWrapper(weaponType, register,
        scInstance -> new Key(getIndexInList(WEAPON_TYPES, scInstance)), WWeaponType::new);
  }

}
