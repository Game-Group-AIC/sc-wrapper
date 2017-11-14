package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.api.enums.Order;

/**
 * The TechType (or Technology Type, also referred to as an Ability) represents a Unit's ability which can be researched with UnitInterface::research or used with UnitInterface::useTech. In order for a Unit to use its own specialized ability, it must first be available and researched. See also TechTypes
 */
public interface TechType {

    /**
     * Retrieves the race that is required to research or use the TechType. Note There is an exception where Infested Kerrigan can use Psionic Storm. This does not apply to the behavior of this function. Returns Race object indicating which race is designed to use this technology type.
     */
    Race getRace();

    /**
     * Retrieves the mineral cost of researching this technology. Returns Amount of minerals needed in order to research this technology.
     */
    int mineralPrice();

    /**
     * Retrieves the vespene gas cost of researching this technology. Returns Amount of vespene gas needed in order to research this technology.
     */
    int gasPrice();

    /**
     * Retrieves the number of frames needed to research the tech type. Returns The time, in frames, it will take for the research to complete. See also UnitInterface::getRemainingResearchTime
     */
    int researchTime();

    /**
     * Retrieves the amount of energy needed to use this TechType as an ability. Returns Energy cost of the ability. See also UnitInterface::getEnergy
     */
    int energyCost();

    /**
     * Retrieves the UnitType that can research this technology. Returns UnitType that is able to research the technology in the game. Return values UnitTypes::None If the technology/ability is either provided for free or never available.
     */
    UnitType whatResearches();

    /**
     * Retrieves the Weapon that is attached to this tech type. A technology's WeaponType is used to indicate the range and behaviour of the ability when used by a Unit. Returns WeaponType containing information about the ability's behavior. Return values WeaponTypes::None If there is no corresponding WeaponType.
     */
    WeaponType getWeapon();

    /**
     * Checks if this ability can be used on other units. Returns true if the ability can be used on other units, and false if it can not.
     */
    boolean targetsUnit();

    /**
     * Checks if this ability can be used on the terrain (ground). Returns true if the ability can be used on the terrain.
     */
    boolean targetsPosition();

    /**
     * Retrieves the Order that a Unit uses when using this ability. Returns Order representing the action a Unit uses to perform this ability
     */
    Order getOrder();

    /**
     * Retrieves the UnitType required to research this technology. The required unit type must be a completed unit owned by the player researching the technology. Returns UnitType that is needed to research this tech type. Return values UnitTypes::None if no unit is required to research this tech type. See also PlayerInterface::completedUnitCount Since 4.1.2
     */
    UnitType requiredUnit();

}
