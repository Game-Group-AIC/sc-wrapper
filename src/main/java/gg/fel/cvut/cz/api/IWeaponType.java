package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.WeaponType;
import gg.fel.cvut.cz.enums.DamageTypeEnum;
import gg.fel.cvut.cz.enums.ExplosionTypeEnum;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import java.io.Serializable;
import java.util.Optional;

/**
 * This object identifies a weapon type used by a unit to attack and deal damage. Some weapon types
 * can be upgraded while others are used for special abilities. See also WeaponTypes
 */
@JsonDeserialize(as = WeaponType.class)
public interface IWeaponType extends InGameInterface, Serializable {

  WeaponTypeEnum getWeaponType();

  /**
   * Retrieves the technology type that must be researched before this weapon can be used. Returns
   * ITechType required by this weapon. Return values TechTypes::None if no tech type is required to
   * use this weapon. See also ITechType::getWeapon
   */
  Optional<ITechType> getTech();

  /**
   * Retrieves the unit type that is intended to use this weapon type. Note There is a rare case
   * where some hero unit types use the same weapon. Returns The IUnitType that uses this weapon.
   * See also IUnitType::groundWeapon, IUnitType::airWeapon
   */
  Optional<IUnitType> whatUses();

  /**
   * Retrieves the base amount of damage that this weapon can deal per attack. Note That this damage
   * amount must go through a DamageTypeEnum and UnitSizeTypeEnum filter before it is applied to a
   * unit. Returns Amount of base damage that this weapon deals.
   */
  Optional<Integer> damageAmount();

  /**
   * Determines the bonus amount of damage that this weapon type increases by for every upgrade to
   * this type. See also upgradeType Returns Amount of damage added for every weapon upgrade.
   */
  Optional<Integer> damageBonus();

  /**
   * Retrieves the base amount of cooldown time between each attack, in frames. Returns The amount
   * of base cooldown applied to the unit after an attack. See also UnitInterface::getGroundWeaponCooldown,
   * UnitInterface::getAirWeaponCooldown
   */
  Optional<Integer> damageCoolDown();

  /**
   * Obtains the intended number of missiles/attacks that are used. This is used to multiply with
   * the damage amount to obtain the full amount of damage for an attack. Returns The damage factor
   * multiplied by the amount to obtain the total damage. See also damageAmount
   */
  Optional<Integer> damageFactor();

  /**
   * Retrieves the upgrade type that increases this weapon's damage output. Returns The IUpgradeType
   * used to upgrade this weapon's damage. See also damageBonus
   */
  Optional<IUpgradeType> upgradeType();

  /**
   * Retrieves the damage type that this weapon applies to a unit type. Returns DamageTypeEnum used
   * for damage calculation. See also DamageTypeEnum, UnitSizeTypeEnum
   */
  Optional<DamageTypeEnum> damageType();

  /**
   * Retrieves the explosion type that indicates how the weapon deals damage. Returns
   * ExplosionTypeEnum identifying how damage is applied to a target location.
   */
  Optional<ExplosionTypeEnum> explosionType();

  /**
   * Retrieves the minimum attack range of the weapon, measured in pixels. This value is 0 for
   * almost all weapon types, except for WeaponTypes::Arclite_Shock_Cannon and
   * WeaponTypes::Arclite_Shock_Cannon_Edmund_Duke. Returns Minimum attack range, in pixels.
   */
  Optional<Integer> minRange();

  /**
   * Retrieves the maximum attack range of the weapon, measured in pixels. Returns Maximum attack
   * range, in pixels.
   */
  Optional<Integer> maxRange();

  /**
   * Retrieves the inner radius used for splash damage calculations, in pixels. Returns Radius of
   * the inner splash area, in pixels.
   */
  Optional<Integer> innerSplashRadius();

  /**
   * Retrieves the middle radius used for splash damage calculations, in pixels. Returns Radius of
   * the middle splash area, in pixels.
   */
  Optional<Integer> medianSplashRadius();

  /**
   * Retrieves the outer radius used for splash damage calculations, in pixels. Returns Radius of
   * the outer splash area, in pixels.
   */
  Optional<Integer> outerSplashRadius();

  /**
   * Checks if this weapon type can target air units. Returns true if this weapon type can target
   * air units, and false otherwise. See also UnitInterface::isFlying, IUnitType::isFlyer
   */
  Optional<Boolean> targetsAir();

  /**
   * Checks if this weapon type can target ground units. Returns true if this weapon type can target
   * ground units, and false otherwise. See also UnitInterface::isFlying, IUnitType::isFlyer
   */
  Optional<Boolean> targetsGround();

  /**
   * Checks if this weapon type can only target mechanical units. Returns true if this weapon type
   * can only target mechanical units, and false otherwise. See also targetsOrgOrMech,
   * IUnitType::isMechanical
   */
  Optional<Boolean> targetsMechanical();

  /**
   * Checks if this weapon type can only target organic units. Returns true if this weapon type can
   * only target organic units, and false otherwise. See also targetsOrgOrMech,
   * IUnitType::isOrganic
   */
  Optional<Boolean> targetsOrganic();

  /**
   * Checks if this weapon type cannot target structures. Returns true if this weapon type cannot
   * target buildings, and false if it can. See also IUnitType::isBuilding
   */
  Optional<Boolean> targetsNonBuilding();

  /**
   * Checks if this weapon type cannot target robotic units. Returns true if this weapon type cannot
   * target robotic units, and false if it can. See also IUnitType::isRobotic
   */
  Optional<Boolean> targetsNonRobotic();

  /**
   * Checks if this weapon type can target the ground. Note This is more for attacks like Psionic
   * Storm which can target a location, not to be confused with attack move. Returns true if this
   * weapon type can target a location, and false otherwise.
   */
  Optional<Boolean> targetsTerrain();

  /**
   * Checks if this weapon type can only target organic or mechanical units. Returns true if this
   * weapon type can only target organic or mechanical units, and false otherwise. See also
   * targetsOrganic, targetsMechanical, IUnitType::isOrganic, IUnitType::isMechanical
   */
  Optional<Boolean> targetsOrgOrMech();

  /**
   * Checks if this weapon type can only target units owned by the same player. This is used for
   * WeaponTypes::Consume. Returns true if this weapon type can only target your own units, and
   * false otherwise. See also UnitInterface::getPlayer
   */
  Optional<Boolean> targetsOwn();

  default Optional<Double> getDamageNormalized() {
    if (getWeaponType().equals(WeaponTypeEnum.PsiBlades)) {
      return Optional.of(16.0);
    } else {
      if (!damageAmount().isPresent() || !damageFactor().isPresent()) {
        return Optional.empty();
      }
      return Optional.of((double) damageAmount().get() * (double) damageFactor().get());
    }
  }

}
