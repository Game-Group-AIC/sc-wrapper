package gg.fel.cvut.cz.enums;

import bwapi.ExplosionType;
import java.util.List;

/**
 * A representation of a weapon's explosion type. This indicates how the weapon behaves, such as if
 * it deals splash damage or causes an effect to occur. See also ExplosionTypes
 */
public enum ExplosionTypeEnum implements IGameTypes<ExplosionType, ExplosionTypeEnum> {
  None,
  Normal,
  RadialSplash,
  EnemySplash,
  Lockdown,
  NuclearMissile,
  Parasite,
  Broodlings,
  EMPShockwave,
  Irradiate,
  Ensnare,
  Plague,
  StasisField,
  DarkSwarm,
  Consume,
  YamatoGun,
  Restoration,
  DisruptionWeb,
  CorrosiveAcid,
  MindControl,
  Feedback,
  OpticalFlare,
  Maelstrom,
  AirSplash,
  Unknown;

  @Override
  public List<ExplosionType> getTypes() {
    return EXPLOSION_TYPES;
  }

  @Override
  public ExplosionTypeEnum[] getValues() {
    return ExplosionTypeEnum.values();
  }
}
