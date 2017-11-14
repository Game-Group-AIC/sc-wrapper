package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.api.enums.DamageType;
import gg.fel.cvut.cz.api.enums.ExplosionType;

/**
 * This object identifies a weapon type used by a unit to attack and deal damage. Some weapon types can be upgraded while others are used for special abilities. See also WeaponTypes
 */
public interface WeaponType {

    /**
     * Retrieves the technology type that must be researched before this weapon can be used. Returns TechType required by this weapon. Return values TechTypes::None if no tech type is required to use this weapon. See also TechType::getWeapon
     */
    TechType getTech();

    /**
     * Retrieves the unit type that is intended to use this weapon type. Note There is a rare case where some hero unit types use the same weapon. Returns The UnitType that uses this weapon. See also UnitType::groundWeapon, UnitType::airWeapon
     */
    UnitType whatUses();

    /**
     * Retrieves the base amount of damage that this weapon can deal per attack. Note That this damage amount must go through a DamageType and UnitSizeType filter before it is applied to a unit. Returns Amount of base damage that this weapon deals.
     */
    int damageAmount();

    /**
     * Determines the bonus amount of damage that this weapon type increases by for every upgrade to this type. See also upgradeType Returns Amount of damage added for every weapon upgrade.
     */
    int damageBonus();

    /**
     * Retrieves the base amount of cooldown time between each attack, in frames. Returns The amount of base cooldown applied to the unit after an attack. See also UnitInterface::getGroundWeaponCooldown, UnitInterface::getAirWeaponCooldown
     */
    int damageCooldown();

    /**
     * Obtains the intended number of missiles/attacks that are used. This is used to multiply with the damage amount to obtain the full amount of damage for an attack. Returns The damage factor multiplied by the amount to obtain the total damage. See also damageAmount
     */
    int damageFactor();

    /**
     * Retrieves the upgrade type that increases this weapon's damage output. Returns The UpgradeType used to upgrade this weapon's damage. See also damageBonus
     */
    UpgradeType upgradeType();

    /**
     * Retrieves the damage type that this weapon applies to a unit type. Returns DamageType used for damage calculation. See also DamageType, UnitSizeType
     */
    DamageType damageType();

    /**
     * Retrieves the explosion type that indicates how the weapon deals damage. Returns ExplosionType identifying how damage is applied to a target location.
     */
    ExplosionType explosionType();

    /**
     * Retrieves the minimum attack range of the weapon, measured in pixels. This value is 0 for almost all weapon types, except for WeaponTypes::Arclite_Shock_Cannon and WeaponTypes::Arclite_Shock_Cannon_Edmund_Duke. Returns Minimum attack range, in pixels.
     */
    int minRange();

    /**
     * Retrieves the maximum attack range of the weapon, measured in pixels. Returns Maximum attack range, in pixels.
     */
    int maxRange();

    /**
     * Retrieves the inner radius used for splash damage calculations, in pixels. Returns Radius of the inner splash area, in pixels.
     */
    int innerSplashRadius();

    /**
     * Retrieves the middle radius used for splash damage calculations, in pixels. Returns Radius of the middle splash area, in pixels.
     */
    int medianSplashRadius();

    /**
     * Retrieves the outer radius used for splash damage calculations, in pixels. Returns Radius of the outer splash area, in pixels.
     */
    int outerSplashRadius();

    /**
     * Checks if this weapon type can target air units. Returns true if this weapon type can target air units, and false otherwise. See also UnitInterface::isFlying, UnitType::isFlyer
     */
    boolean targetsAir();

    /**
     * Checks if this weapon type can target ground units. Returns true if this weapon type can target ground units, and false otherwise. See also UnitInterface::isFlying, UnitType::isFlyer
     */
    boolean targetsGround();

    /**
     * Checks if this weapon type can only target mechanical units. Returns true if this weapon type can only target mechanical units, and false otherwise. See also targetsOrgOrMech, UnitType::isMechanical
     */
    boolean targetsMechanical();

    /**
     * Checks if this weapon type can only target organic units. Returns true if this weapon type can only target organic units, and false otherwise. See also targetsOrgOrMech, UnitType::isOrganic
     */
    boolean targetsOrganic();

    /**
     * Checks if this weapon type cannot target structures. Returns true if this weapon type cannot target buildings, and false if it can. See also UnitType::isBuilding
     */
    boolean targetsNonBuilding();

    /**
     * Checks if this weapon type cannot target robotic units. Returns true if this weapon type cannot target robotic units, and false if it can. See also UnitType::isRobotic
     */
    boolean targetsNonRobotic();

    /**
     * Checks if this weapon type can target the ground. Note This is more for attacks like Psionic Storm which can target a location, not to be confused with attack move. Returns true if this weapon type can target a location, and false otherwise.
     */
    boolean targetsTerrain();

    /**
     * Checks if this weapon type can only target organic or mechanical units. Returns true if this weapon type can only target organic or mechanical units, and false otherwise. See also targetsOrganic, targetsMechanical, UnitType::isOrganic, UnitType::isMechanical
     */
    boolean targetsOrgOrMech();

    /**
     * Checks if this weapon type can only target units owned by the same player. This is used for WeaponTypes::Consume. Returns true if this weapon type can only target your own units, and false otherwise. See also UnitInterface::getPlayer
     */
    boolean targetsOwn();

}
