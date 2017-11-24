package gg.fel.cvut.cz.enums;

/**
 * Size types are used by unit types in Broodwar to determine how much damage will be applied. This corresponds with DamageType for several different damage reduction applications. See also DamageType, IUnitType, UnitSizeTypes View on Starcraft Campendium (Official Website)
 */
public enum UnitSizeType implements IGameTypes {
    Independent,
    Small,
    Medium,
    Large,
    None,
    Unknown
}
