package gg.fel.cvut.cz.enums;

/**
 * Damage types are used in Broodwar to determine the amount of damage that will be done to a unit.
 * This corresponds with UnitSizeTypeEnum to determine the damage done to a unit. See also
 * IWeaponType, DamageTypes, UnitSizeTypeEnum View on Liquipedia View on Starcraft Campendium
 * (Official Website) View on Starcraft Wikia
 */
public enum DamageTypeEnum implements IGameTypes {
  Independent,
  Explosive,
  Concussive,
  Normal,
  Ignore_Armor,
  None,
  Unknown
}