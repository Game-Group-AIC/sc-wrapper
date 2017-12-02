package gg.fel.cvut.cz.enums;

import bwapi.BulletType;
import java.util.List;

/**
 * This class represents a type of bullet. Note Internally, these are the same IDs as flingy types
 * in Broodwar. See also BulletTypes
 */
public enum BulletTypeEnum implements IGameTypes<BulletType, BulletTypeEnum> {
  Melee,
  FusionCutterHit,
  GaussRifleHit,
  C10CanisterRifleHit,
  GeminiMissiles,
  FragmentationGrenade,
  LongboltMissile,
  ATSATALaserBattery,
  BurstLasers,
  ArcliteShockCannonHit,
  EMPMissile,
  DualPhotonBlastersHit,
  ParticleBeamHit,
  AntiMatterMissile,
  PulseCannon,
  PsionicShockwaveHit,
  PsionicStorm,
  YamatoGun,
  PhaseDisruptor,
  STASTSCannonOverlay,
  SunkenColonyTentacle,
  AcidSpore,
  GlaveWurm,
  SeekerSpores,
  QueenSpellCarrier,
  PlagueCloud,
  Consume,
  Ensnare,
  NeedleSpineHit,
  Invisible,
  OpticalFlareGrenade,
  HaloRockets,
  SubterraneanSpines,
  CorrosiveAcidShot,
  NeutronFlare,
  None,
  Unknown;


  @Override
  public List<BulletType> getTypes() {
    return BULLET_TYPES;
  }

  @Override
  public BulletTypeEnum[] getValues() {
    return BulletTypeEnum.values();
  }
}
