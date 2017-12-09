package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.api.IWeaponType;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForType;
import gg.fel.cvut.cz.enums.DamageTypeEnum;
import gg.fel.cvut.cz.enums.ExplosionTypeEnum;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import java.io.Serializable;
import java.util.Optional;

//TODO implement
public class WeaponType extends AContainerForType<bwapi.WeaponType, WeaponTypeEnum> implements
    IWeaponType, Serializable {

  public WeaponType(BWReplayCounter bwCounter, WeaponTypeEnum weaponType) {
    super(bwCounter, weaponType);
  }

  @Override
  public WeaponTypeEnum getWeaponType() {
    return type;
  }

  @Override
  public Optional<ITechType> getTech() {
    return null;
  }

  @Override
  public Optional<IUnitType> whatUses() {
    return null;
  }

  @Override
  public Optional<Integer> damageAmount() {
    return null;
  }

  @Override
  public Optional<Integer> damageBonus() {
    return null;
  }

  @Override
  public Optional<Integer> damageCoolDown() {
    return null;
  }

  @Override
  public Optional<Integer> damageFactor() {
    return null;
  }

  @Override
  public Optional<IUpgradeType> upgradeType() {
    return null;
  }

  @Override
  public Optional<DamageTypeEnum> damageType() {
    return null;
  }

  @Override
  public Optional<ExplosionTypeEnum> explosionType() {
    return null;
  }

  @Override
  public Optional<Integer> minRange() {
    return null;
  }

  @Override
  public Optional<Integer> maxRange() {
    return null;
  }

  @Override
  public Optional<Integer> innerSplashRadius() {
    return null;
  }

  @Override
  public Optional<Integer> medianSplashRadius() {
    return null;
  }

  @Override
  public Optional<Integer> outerSplashRadius() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsAir() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsGround() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsMechanical() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsOrganic() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsNonBuilding() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsNonRobotic() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsTerrain() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsOrgOrMech() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsOwn() {
    return null;
  }
}
