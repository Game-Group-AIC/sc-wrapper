package gg.fel.cvut.cz.enums;

import bwapi.DamageType;
import java.util.List;

/**
 * Damage types are used in Broodwar to determine the amount of damage that will be done to a unit.
 * This corresponds with UnitSizeTypeEnum to determine the damage done to a unit. See also
 * IWeaponType, DamageTypes, UnitSizeTypeEnum View on Liquipedia View on Starcraft Campendium
 * (Official Website) View on Starcraft Wikia
 */
public enum DamageTypeEnum implements IGameTypes<DamageType, DamageTypeEnum> {
  Independent,
  Explosive,
  Concussive,
  Normal,
  IgnoreArmor,
  None,
  Unknown;

  @Override
  public List<DamageType> getTypes() {
    return DAMAGE_TYPES;
  }

  @Override
  public DamageTypeEnum[] getValues() {
    return DamageTypeEnum.values();
  }
}
