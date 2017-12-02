package gg.fel.cvut.cz.wrappers;


import bwapi.UnitType;
import gg.fel.cvut.cz.enums.UnitTypeEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WUnitType extends WrapperForType<UnitType, UnitTypeEnum> {

  private static final Map<UnitTypeEnum, WUnitType> register = new HashMap<>();

  private WUnitType(UnitTypeEnum unitType) {
    super(unitType.getBWType(), unitType);
  }

  public static WUnitType getOrCreateWrapper(UnitTypeEnum unitType) {
    return getOrCreateWrapper(unitType, register, WUnitType::new, lock);
  }

  public static Stream<WUnitType> getAllWrappedTypes() {
    return Arrays.stream(UnitTypeEnum.values()).map(WUnitType::getOrCreateWrapper);
  }

}
