package gg.fel.cvut.cz.wrappers;

import bwapi.TechType;
import gg.fel.cvut.cz.enums.TechTypeEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WTechType extends WrapperForType<TechType, TechTypeEnum> {

  private static final Map<TechTypeEnum, WTechType> register = new HashMap<>();

  private WTechType(TechTypeEnum techType) {
    super(techType.getBWType(), techType);
  }

  public static WTechType getOrCreateWrapper(TechTypeEnum techTypeEnum) {
    return getOrCreateWrapper(techTypeEnum, register, WTechType::new, lock);
  }

  public static Stream<WTechType> getAllWrappedTypes() {
    return Arrays.stream(TechTypeEnum.values()).map(WTechType::getOrCreateWrapper);
  }
}
