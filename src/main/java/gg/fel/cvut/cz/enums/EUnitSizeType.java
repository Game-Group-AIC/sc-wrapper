package gg.fel.cvut.cz.enums;

/**
 * Size types are used by unit types in Broodwar to determine how much damage will be applied. This
 * corresponds with EDamageType for several different damage reduction applications. See also
 * EDamageType, IUnitType, UnitSizeTypes View on Starcraft Campendium (Official Website)
 */
public enum EUnitSizeType implements IGameTypes {
  Independent,
  Small,
  Medium,
  Large,
  None,
  Unknown
}
