package gg.fel.cvut.cz.enums;

import bwapi.UnitSizeType;
import java.util.List;

/**
 * Size types are used by unit types in Broodwar to determine how much damage will be applied. This
 * corresponds with DamageTypeEnum for several different damage reduction applications. See also
 * DamageTypeEnum, IUnitType, UnitSizeTypes View on Starcraft Campendium (Official Website)
 */
public enum UnitSizeTypeEnum implements IGameTypes<UnitSizeType, UnitSizeTypeEnum> {
  Independent,
  Small,
  Medium,
  Large,
  None,
  Unknown;


  @Override
  public List<UnitSizeType> getTypes() {
    return UNIT_SIZE_TYPES;
  }

  @Override
  public UnitSizeTypeEnum[] getValues() {
    return UnitSizeTypeEnum.values();
  }
}
