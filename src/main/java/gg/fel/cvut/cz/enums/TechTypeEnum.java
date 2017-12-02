package gg.fel.cvut.cz.enums;

import bwapi.TechType;
import java.util.List;

public enum TechTypeEnum implements IGameTypes<TechType, TechTypeEnum> {
  StimPacks,
  Lockdown,
  EMPShockwave,
  SpiderMines,
  ScannerSweep,
  TankSiegeMode,
  DefensiveMatrix,
  Irradiate,
  YamatoGun,
  CloakingField,
  PersonnelCloaking,
  Restoration,
  OpticalFlare,
  Healing,
  NuclearStrike,
  Burrowing,
  Infestation,
  SpawnBroodlings,
  DarkSwarm,
  Plague,
  Consume,
  Ensnare,
  Parasite,
  LurkerAspect,
  PsionicStorm,
  Hallucination,
  Recall,
  StasisField,
  ArchonWarp,
  DisruptionWeb,
  MindControl,
  DarkArchonMeld,
  Feedback,
  Maelstrom,
  None,
  Unknown;

  @Override
  public List<TechType> getTypes() {
    return TECH_TYPES;
  }

  @Override
  public TechTypeEnum[] getValues() {
    return TechTypeEnum.values();
  }
}
