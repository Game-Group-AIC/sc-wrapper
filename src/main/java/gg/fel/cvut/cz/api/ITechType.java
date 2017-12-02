package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.OrderEnum;
import gg.fel.cvut.cz.enums.TechTypeEnum;
import java.io.Serializable;
import java.util.Optional;

/**
 * The ITechType (or Technology Type, also referred to as an Ability) represents a IUnit's ability
 * which can be researched with UnitInterface::research or used with UnitInterface::useTech. In
 * order for a IUnit to use its own specialized ability, it must first be available and researched.
 * See also TechTypes
 */
public interface ITechType extends InGameInterface, Serializable {

  TechTypeEnum getTechType();

  /**
   * Retrieves the race that is required to research or use the ITechType. Note There is an
   * exception where Infested Kerrigan can use Psionic Storm. This does not apply to the behavior of
   * this function. Returns IRace object indicating which race is designed to use this technology
   * type.
   */
  Optional<IRace> getRace();

  /**
   * Retrieves the mineral cost of researching this technology. Returns Amount of minerals needed in
   * order to research this technology.
   */
  Optional<Integer> mineralPrice();

  /**
   * Retrieves the vespene gas cost of researching this technology. Returns Amount of vespene gas
   * needed in order to research this technology.
   */
  Optional<Integer> gasPrice();

  /**
   * Retrieves the number of frames needed to research the tech type. Returns The time, in frames,
   * it will take for the research to complete. See also UnitInterface::getRemainingResearchTime
   */
  Optional<Integer> researchTime();

  /**
   * Retrieves the amount of energy needed to use this ITechType as an ability. Returns Energy cost
   * of the ability. See also UnitInterface::getEnergy
   */
  Optional<Integer> energyCost();

  /**
   * Retrieves the Weapon that is attached to this tech type. A technology's IWeaponType is used to
   * indicate the range and behaviour of the ability when used by a IUnit. Returns IWeaponType
   * containing information about the ability's behavior. Return values WeaponTypes::None If there
   * is no corresponding IWeaponType.
   */
  Optional<IWeaponType> getWeapon();

  /**
   * Checks if this ability can be used on other units. Returns true if the ability can be used on
   * other units, and false if it can not.
   */
  Optional<Boolean> targetsUnit();

  /**
   * Checks if this ability can be used on the terrain (ground). Returns true if the ability can be
   * used on the terrain.
   */
  Optional<Boolean> targetsPosition();

  /**
   * Retrieves the OrderEnum that a IUnit uses when using this ability. Returns OrderEnum
   * representing the action a IUnit uses to perform this ability
   */
  Optional<OrderEnum> getOrder();

  /**
   * Retrieves the IUnitType required to research this technology. The required unit type must be a
   * completed unit owned by the player researching the technology. Returns IUnitType that is needed
   * to research this tech type. Return values UnitTypes::None if no unit is required to research
   * this tech type. See also PlayerInterface::completedUnitCount Since 4.1.2
   */
  Optional<IUnitType> requiredUnit();

}
