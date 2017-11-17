package gg.fel.cvut.cz.wrappers;


import bwapi.WeaponType;

import java.util.Arrays;
import java.util.List;

import static bwapi.WeaponType.*;

public class WWeaponType extends WrapperForClassWithID<WeaponType> {
    private final static List<WeaponType> WEAPON_TYPES = Arrays.asList(Gauss_Rifle, Gauss_Rifle_Jim_Raynor, C_10_Canister_Rifle,
            C_10_Canister_Rifle_Sarah_Kerrigan, C_10_Canister_Rifle_Samir_Duran, C_10_Canister_Rifle_Infested_Duran, C_10_Canister_Rifle_Alexei_Stukov,
            Fragmentation_Grenade, Fragmentation_Grenade_Jim_Raynor, Spider_Mines, Twin_Autocannons, Twin_Autocannons_Alan_Schezar,
            Hellfire_Missile_Pack, Hellfire_Missile_Pack_Alan_Schezar, Arclite_Cannon, Arclite_Cannon_Edmund_Duke, Fusion_Cutter,
            Gemini_Missiles, Gemini_Missiles_Tom_Kazansky, Burst_Lasers, Burst_Lasers_Tom_Kazansky, ATS_Laser_Battery,
            ATS_Laser_Battery_Hero, ATS_Laser_Battery_Hyperion, ATA_Laser_Battery, ATA_Laser_Battery_Hero, ATA_Laser_Battery_Hyperion,
            Flame_Thrower, Flame_Thrower_Gui_Montag, Arclite_Shock_Cannon, Arclite_Shock_Cannon_Edmund_Duke, Longbolt_Missile,
            Claws, Claws_Devouring_One, Claws_Infested_Kerrigan, Needle_Spines, Needle_Spines_Hunter_Killer, Kaiser_Blades, Kaiser_Blades_Torrasque,
            Toxic_Spores, Spines, Acid_Spore, Acid_Spore_Kukulza, Glave_Wurm, Glave_Wurm_Kukulza, Seeker_Spores, Subterranean_Tentacle,
            Suicide_Infested_Terran, Suicide_Scourge, Particle_Beam, Psi_Blades, Psi_Blades_Fenix, Phase_Disruptor, Phase_Disruptor_Fenix,
            Psi_Assault, Psionic_Shockwave, Psionic_Shockwave_TZ_Archon, Dual_Photon_Blasters, Dual_Photon_Blasters_Mojo,
            Dual_Photon_Blasters_Artanis, Anti_Matter_Missiles, Anti_Matter_Missiles_Mojo, Anti_Matter_Missiles_Artanis,
            Phase_Disruptor_Cannon, Phase_Disruptor_Cannon_Danimoth, Pulse_Cannon, STS_Photon_Cannon, STA_Photon_Cannon,
            Scarab, Neutron_Flare, Halo_Rockets, Corrosive_Acid, Subterranean_Spines, Warp_Blades, Warp_Blades_Hero, Warp_Blades_Zeratul,
            Independant_Laser_Battery, Twin_Autocannons_Floor_Trap, Hellfire_Missile_Pack_Wall_Trap, Flame_Thrower_Wall_Trap,
            Hellfire_Missile_Pack_Floor_Trap, Yamato_Gun, Nuclear_Strike, Lockdown, EMP_Shockwave, Irradiate, Parasite,
            Spawn_Broodlings, Ensnare, Dark_Swarm, Plague, Consume, Stasis_Field, Psionic_Storm, Disruption_Web, Restoration,
            Mind_Control, Feedback, Optical_Flare, Maelstrom, None, Unknown);

    public WWeaponType(WeaponType scInstance) {
        super(scInstance, getIndexInList(WEAPON_TYPES, scInstance));
    }
}
