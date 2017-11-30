package gg.fel.cvut.cz.enums;

/**
 * Size types are used by unit types in Broodwar to determine how much damage will be applied. This
 * corresponds with DamageTypeEnum for several different damage reduction applications. See also
 * DamageTypeEnum, IUnitType, UnitSizeTypes View on Starcraft Campendium (Official Website)
 */
public enum UnitSizeTypeEnum implements IGameTypes {
  Independent,
  Small,
  Medium,
  Large,
  None,
  Unknown
}
