package gg.fel.cvut.cz.enums;

import bwapi.UpgradeType;
import java.util.List;

public enum UpgradeTypeEnum implements IGameTypes<UpgradeType, UpgradeTypeEnum> {
  TerranInfantryArmor,
  TerranVehiclePlating,
  TerranShipPlating,
  TerranInfantryWeapons,
  TerranVehicleWeapons,
  TerranShipWeapons,
  U238Shells,
  IonThrusters,
  TitanReactor,
  OcularImplants,
  MoebiusReactor,
  ApolloReactor,
  ColossusReactor,
  CaduceusReactor,
  CharonBoosters,
  ZergCarapace,
  ZergFlyerCarapace,
  ZergMeleeAttacks,
  ZergMissileAttacks,
  ZergFlyerAttacks,
  VentralSacs,
  Antennae,
  PneumatizedCarapace,
  MetabolicBoost,
  AdrenalGlands,
  MuscularAugments,
  GroovedSpines,
  GameteMeiosis,
  MetasynapticNode,
  ChitinousPlating,
  AnabolicSynthesis,
  ProtossGroundArmor,
  ProtossAirArmor,
  ProtossGroundWeapons,
  ProtossAirWeapons,
  ProtossPlasmaShields,
  SingularityCharge,
  LegEnhancements,
  ScarabDamage,
  ReaverCapacity,
  GraviticDrive,
  SensorArray,
  GraviticBoosters,
  KhaydarinAmulet,
  ApialSensors,
  GraviticThrusters,
  CarrierCapacity,
  KhaydarinCore,
  ArgusJewel,
  ArgusTalisman,
  Upgrade60,
  None,
  Unknown;

  @Override
  public List<UpgradeType> getTypes() {
    return UPGRADE_TYPES;
  }

  @Override
  public UpgradeTypeEnum[] getValues() {
    return UpgradeTypeEnum.values();
  }
}
