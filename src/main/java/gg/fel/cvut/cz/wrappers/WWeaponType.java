package gg.fel.cvut.cz.wrappers;


import bwapi.WeaponType;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class WWeaponType extends WrapperForType<WeaponType, WeaponTypeEnum> {

  private static final Map<WeaponTypeEnum, WWeaponType> register = new HashMap<>();

  private WWeaponType(WeaponTypeEnum weaponType) {
    super(weaponType.getBWType(), weaponType);
  }

  public static WWeaponType getOrCreateWrapper(WeaponTypeEnum weaponType) {
    return getOrCreateWrapper(weaponType, register, WWeaponType::new, lock);
  }

  public static Stream<WWeaponType> getAllWrappedTypes() {
    return Arrays.stream(WeaponTypeEnum.values()).map(WWeaponType::getOrCreateWrapper);
  }

}
