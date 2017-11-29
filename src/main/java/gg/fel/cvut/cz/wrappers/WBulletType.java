package gg.fel.cvut.cz.wrappers;

import static bwapi.BulletType.ATS_ATA_Laser_Battery;
import static bwapi.BulletType.Acid_Spore;
import static bwapi.BulletType.Anti_Matter_Missile;
import static bwapi.BulletType.Arclite_Shock_Cannon_Hit;
import static bwapi.BulletType.Burst_Lasers;
import static bwapi.BulletType.C_10_Canister_Rifle_Hit;
import static bwapi.BulletType.Consume;
import static bwapi.BulletType.Corrosive_Acid_Shot;
import static bwapi.BulletType.Dual_Photon_Blasters_Hit;
import static bwapi.BulletType.EMP_Missile;
import static bwapi.BulletType.Ensnare;
import static bwapi.BulletType.Fragmentation_Grenade;
import static bwapi.BulletType.Fusion_Cutter_Hit;
import static bwapi.BulletType.Gauss_Rifle_Hit;
import static bwapi.BulletType.Gemini_Missiles;
import static bwapi.BulletType.Glave_Wurm;
import static bwapi.BulletType.Halo_Rockets;
import static bwapi.BulletType.Invisible;
import static bwapi.BulletType.Longbolt_Missile;
import static bwapi.BulletType.Melee;
import static bwapi.BulletType.Needle_Spine_Hit;
import static bwapi.BulletType.Neutron_Flare;
import static bwapi.BulletType.None;
import static bwapi.BulletType.Optical_Flare_Grenade;
import static bwapi.BulletType.Particle_Beam_Hit;
import static bwapi.BulletType.Phase_Disruptor;
import static bwapi.BulletType.Plague_Cloud;
import static bwapi.BulletType.Psionic_Shockwave_Hit;
import static bwapi.BulletType.Psionic_Storm;
import static bwapi.BulletType.Pulse_Cannon;
import static bwapi.BulletType.Queen_Spell_Carrier;
import static bwapi.BulletType.STA_STS_Cannon_Overlay;
import static bwapi.BulletType.Seeker_Spores;
import static bwapi.BulletType.Subterranean_Spines;
import static bwapi.BulletType.Sunken_Colony_Tentacle;
import static bwapi.BulletType.Unknown;
import static bwapi.BulletType.Yamato_Gun;

import bwapi.BulletType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WBulletType extends WrapperForClassWithID<BulletType> {

  private final static List<BulletType> BULLET_TYPES = Arrays
      .asList(Melee, Fusion_Cutter_Hit, Gauss_Rifle_Hit,
          C_10_Canister_Rifle_Hit, Gemini_Missiles, Fragmentation_Grenade, Longbolt_Missile,
          ATS_ATA_Laser_Battery,
          Burst_Lasers, Arclite_Shock_Cannon_Hit, EMP_Missile, Dual_Photon_Blasters_Hit,
          Particle_Beam_Hit,
          Anti_Matter_Missile, Pulse_Cannon, Psionic_Shockwave_Hit, Psionic_Storm, Yamato_Gun,
          Phase_Disruptor,
          STA_STS_Cannon_Overlay, Sunken_Colony_Tentacle, Acid_Spore, Glave_Wurm, Seeker_Spores,
          Queen_Spell_Carrier,
          Plague_Cloud, Consume, Ensnare, Needle_Spine_Hit, Invisible, Optical_Flare_Grenade,
          Halo_Rockets,
          Subterranean_Spines, Corrosive_Acid_Shot, Neutron_Flare, None, Unknown);
  private static final Map<Key, WBulletType> register = new HashMap<>();

  private WBulletType(BulletType scInstance) {
    super(scInstance, getIndexInList(BULLET_TYPES, scInstance));
  }

  public static WBulletType getOrCreateWrapper(BulletType bulletType) {
    return getOrCreateWrapper(bulletType, register,
        scInstance -> new Key(getIndexInList(BULLET_TYPES, scInstance)), WBulletType::new);
  }
}
