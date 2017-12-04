package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.api.IWeaponType;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.DamageTypeEnum;
import gg.fel.cvut.cz.enums.ExplosionTypeEnum;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class WeaponType extends AContainer implements IWeaponType, Serializable {

  public WeaponType(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public WeaponTypeEnum getWeaponType() {
    return null;
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

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return new HashSet<>();
  }
}
