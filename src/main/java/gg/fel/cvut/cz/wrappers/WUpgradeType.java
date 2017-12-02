package gg.fel.cvut.cz.wrappers;

import bwapi.UpgradeType;
import gg.fel.cvut.cz.enums.UpgradeTypeEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WUpgradeType extends WrapperForType<UpgradeType, UpgradeTypeEnum> {

  private static final Map<UpgradeTypeEnum, WUpgradeType> register = new HashMap<>();

  private WUpgradeType(UpgradeTypeEnum upgradeType) {
    super(upgradeType.getBWType(), upgradeType);
  }

  public static WUpgradeType getOrCreateWrapper(UpgradeTypeEnum upgradeType) {
    return getOrCreateWrapper(upgradeType, register, WUpgradeType::new, lock);
  }

  public static Stream<WUpgradeType> getAllWrappedTypes() {
    return Arrays.stream(UpgradeTypeEnum.values()).map(WUpgradeType::getOrCreateWrapper);
  }

}
