package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.api.enums.UnitSizeType;

import java.util.List;
import java.util.Map;

/**
 * The UnitType is used to get information about a particular type of unit, such as its cost, build time, weapon, hit points, abilities, etc. See also UnitInterface::getType, UnitTypes
 */
public interface UnitType {

    /**
     * Retrieves the Race that the unit type belongs to. Returns Race indicating the race that owns this unit type. Return values Race::None indicating that the unit type does not belong to any particular race (a critter for example).
     */
    Race getRace();

    /**
     * Obtains the source unit type that is used to build or train this unit type, as well as the amount of them that are required. Returns std::pair in which the first value is the UnitType that builds this unit type, and the second value is the number of those types that are required (this value is 2 for Archons, and 1 for all other types). Return values pair(UnitTypes::None,0) If this unit type cannot be made by the player.
     */
    Pair<UnitType, Integer> whatBuilds();

    /**
     * Retrieves the immediate technology tree requirements to make this unit type. Returns std::map containing a UnitType to number mapping of UnitTypes required.
     */
    Map<UnitType, Integer> requiredUnits();

    /**
     * Identifies the required TechType in order to create certain units. Note The only unit that requires a technology is the Lurker, which needs Lurker Aspect. Returns TechType indicating the technology that must be researched in order to create this unit type. Return values TechTypes::None If creating this unit type does not require a technology to be researched.
     */
    TechType requiredTech();

    /**
     * Retrieves the cloaking technology associated with certain units. Returns TechType referring to the cloaking technology that this unit type uses as an ability. Return values TechTypes::None If this unit type does not have an active cloak ability.
     */
    TechType cloakingTech();

    /**
     * Retrieves the set of abilities that this unit can use, provided it is available to you in the game. Returns Set of TechTypes containing ability information.
     */
    List<TechType> abilities();

    /**
     * Retrieves the set of upgrades that this unit can use to enhance its fighting ability. Returns Set of UpgradeTypes containing upgrade types that will impact this unit type.
     */
    List<UpgradeType> upgrades();

    /**
     * Retrieves the upgrade type used to increase the armor of this unit type. For each upgrade, this unit type gains +1 additional armor. Returns UpgradeType indicating the upgrade that increases this unit type's armor amount.
     */
    UpgradeType armorUpgrade();

    /**
     * Retrieves the default maximum amount of hit points that this unit type can have. Note This value may not necessarily match the value seen in the Use Map Settings game type. Returns Integer indicating the maximum amount of hit points for this unit type.
     */
    int maxHitPoints();

    /**
     * Retrieves the default maximum amount of shield points that this unit type can have. Note This value may not necessarily match the value seen in the Use Map Settings game type. Returns Integer indicating the maximum amount of shield points for this unit type. Return values 0 If this unit type does not have shields.
     */
    int maxShields();

    /**
     * Retrieves the maximum amount of energy this unit type can have by default. Returns Integer indicating the maximum amount of energy for this unit type. Return values 0 If this unit does not gain energy for abilities.
     */
    int maxEnergy();

    /**
     * Retrieves the default amount of armor that the unit type starts with, excluding upgrades. Note This value may not necessarily match the value seen in the Use Map Settings game type. Returns The amount of armor the unit type has.
     */
    int armor();

    /**
     * Retrieves the default mineral price of purchasing the unit. Note This value may not necessarily match the value seen in the Use Map Settings game type. Returns Mineral cost of the unit.
     */
    int mineralPrice();

    /**
     * Retrieves the default vespene gas price of purchasing the unit. Note This value may not necessarily match the value seen in the Use Map Settings game type. Returns Vespene gas cost of the unit.
     */
    int gasPrice();

    /**
     * Retrieves the default time, in frames, needed to train, morph, or build the unit. Note This value may not necessarily match the value seen in the Use Map Settings game type. Returns Number of frames needed in order to build the unit. See also UnitInterface::getRemainingBuildTime
     */
    int buildTime();

    /**
     * Retrieves the amount of supply that this unit type will use when created. It will use the supply pool that is appropriate for its Race. Note In Starcraft programming, the managed supply values are double than what they appear in the game. The reason for this is because Zerglings use 0.5 visible supply. Returns Integer containing the supply required to build this unit. See also supplyProvided, PlayerInterface::supplyTotal, PlayerInterface::supplyUsed
     */
    int supplyRequired();

    /**
     * Retrieves the amount of supply that this unit type produces for its appropriate Race's supply pool. Note In Starcraft programming, the managed supply values are double than what they appear in the game. The reason for this is because Zerglings use 0.5 visible supply. See also supplyRequired, PlayerInterface::supplyTotal, PlayerInterface::supplyUsed
     */
    int supplyProvided();

    /**
     * Retrieves the amount of space required by this unit type to fit inside a Bunker or Transport(Dropship, Shuttle, Overlord ). Returns Amount of space required by this unit type for transport. Return values 255 If this unit type can not be transported. See also spaceProvided
     */
    int spaceRequired();

    /**
     * Retrieves the amount of space provided by this Bunker or Transport(Dropship, Shuttle, Overlord ) for unit transportation. Returns The number of slots provided by this unit type. See also spaceRequired
     */
    int spaceProvided();

    /**
     * Retrieves the amount of score points awarded for constructing this unit type. This value is used for calculating scores in the post-game score screen. Returns Number of points awarded for constructing this unit type. See also destroyScore
     */
    int buildScore();

    /**
     * Retrieves the amount of score points awarded for killing this unit type. This value is used for calculating scores in the post-game score screen. Returns Number of points awarded for killing this unit type. See also buildScore
     */
    int destroyScore();

    /**
     * Retrieves the UnitSizeType of this unit, which is used in calculations along with weapon damage types to determine the amount of damage that will be dealt to this type. Returns UnitSizeType indicating the conceptual size of the unit type. See also WeaponType::damageType
     */
    UnitSizeType size();

    /**
     * Retrieves the width of this unit type, in tiles. Used for determining the tile size of structures. Returns Width of this unit type, in tiles.
     */
    int tileWidth();

    /**
     * Retrieves the height of this unit type, in tiles. Used for determining the tile size of structures. Returns Height of this unit type, in tiles.
     */
    int tileHeight();

    /**
     * Retrieves the tile size of this unit type. Used for determining the tile size of structures. Returns TilePosition containing the width (x) and height (y) of the unit type, in tiles.
     */
    TilePosition tileSize();

    /**
     * Retrieves the distance from the center of the unit type to its left edge. Returns Distance to this unit type's left edge from its center, in pixels.
     */
    int dimensionLeft();

    /**
     * Retrieves the distance from the center of the unit type to its top edge. Returns Distance to this unit type's top edge from its center, in pixels.
     */
    int dimensionUp();

    /**
     * Retrieves the distance from the center of the unit type to its right edge. Returns Distance to this unit type's right edge from its center, in pixels.
     */
    int dimensionRight();

    /**
     * Retrieves the distance from the center of the unit type to its bottom edge. Returns Distance to this unit type's bottom edge from its center, in pixels.
     */
    int dimensionDown();

    /**
     * A macro for retrieving the width of the unit type, which is calculated using dimensionLeft + dimensionRight + 1. Returns Width of the unit, in pixels.
     */
    int width();

    /**
     * A macro for retrieving the height of the unit type, which is calculated using dimensionUp + dimensionDown + 1. Returns Height of the unit, in pixels.
     */
    int height();

    /**
     * Retrieves the range at which this unit type will start targeting enemy units. Returns Distance at which this unit type begins to seek out enemy units, in pixels.
     */
    int seekRange();

    /**
     * Retrieves the sight range of this unit type. Returns Sight range of this unit type, measured in pixels.
     */
    int sightRange();

    /**
     * Retrieves this unit type's weapon type used when attacking targets on the ground. Returns WeaponType used as this unit type's ground weapon. See also maxGroundHits, airWeapon
     */
    WeaponType groundWeapon();

    /**
     * Retrieves the maximum number of hits this unit can deal to a ground target using its ground weapon. This value is multiplied by the ground weapon's damage to calculate the unit type's damage potential. Returns Maximum number of hits given to ground targets. See also groundWeapon, maxAirHits
     */
    int maxGroundHits();

    /**
     * Retrieves this unit type's weapon type used when attacking targets in the air. Returns WeaponType used as this unit type's air weapon. See also maxAirHits, groundWeapon
     */
    WeaponType airWeapon();

    /**
     * Retrieves the maximum number of hits this unit can deal to a flying target using its air weapon. This value is multiplied by the air weapon's damage to calculate the unit type's damage potential. Returns Maximum number of hits given to air targets. See also airWeapon, maxGroundHits
     */
    int maxAirHits();

    /**
     * Retrieves this unit type's top movement speed with no upgrades. Note That some units have inconsistent movement and this value is sometimes an approximation. Returns The approximate top speed, in pixels per frame, as a double. For liftable Terran structures, this function returns their movement speed while lifted.
     */
    double topSpeed();

    /**
     * Retrieves the unit's acceleration amount. Returns How fast the unit can accelerate to its top speed.
     */
    int acceleration();

    /**
     * Retrieves the unit's halting distance. This determines how fast a unit can stop moving. Returns A halting distance value.
     */
    int haltDistance();

    /**
     * Retrieves a unit's turning radius. This determines how fast a unit can turn. Returns A turn radius value.
     */
    int turnRadius();

    /**
     * Determines if a unit can train other units. For example, UnitTypes::Terran_Barracks.canProduce() will return true, while UnitTypes::Terran_Marine.canProduce() will return false. This is also true for two non-structures: Carrier (can produce interceptors) and Reaver (can produce scarabs). Returns true if this unit type can have a production queue, and false otherwise.
     */
    boolean canProduce();

    /**
     * Checks if this unit is capable of attacking. Note This function returns false for units that can only inflict damage via special abilities, such as the High Templar. Returns true if this unit type is capable of damaging other units with a standard attack, and false otherwise.
     */
    boolean canAttack();

    /**
     * Checks if this unit type is capable of movement. Note Buildings will return false, including Terran liftable buildings which are capable of moving when lifted. Returns true if this unit can use a movement command, and false if they cannot move.
     */
    boolean canMove();

    /**
     * Checks if this unit type is a flying unit. Flying units ignore ground pathing and collisions. Returns true if this unit type is in the air by default, and false otherwise.
     */
    boolean isFlyer();

    /**
     * Checks if this unit type can regenerate hit points. This generally applies to Zerg units. Returns true if this unit type regenerates its hit points, and false otherwise.
     */
    boolean regeneratesHP();

    /**
     * Checks if this unit type has the capacity to store energy and use it for special abilities. Returns true if this unit type generates energy, and false if it does not have an energy pool.
     */
    boolean isSpellcaster();

    /**
     * Checks if this unit type is permanently cloaked. This means the unit type is always cloaked and requires a detector in order to see it. Returns true if this unit type is permanently cloaked, and false otherwise.
     */
    boolean hasPermanentCloak();

    /**
     * Checks if this unit type is invincible by default. Invincible units cannot take damage. Returns true if this unit type is invincible, and false if it is vulnerable to attacks.
     */
    boolean isInvincible();

    /**
     * Checks if this unit is an organic unit. The organic property is required for some abilities such as Heal. Returns true if this unit type has the organic property, and false otherwise.
     */
    boolean isOrganic();

    /**
     * Checks if this unit is mechanical. The mechanical property is required for some actions such as Repair. Returns true if this unit type has the mechanical property, and false otherwise.
     */
    boolean isMechanical();

    /**
     * Checks if this unit is robotic. The robotic property is applied to robotic units such as the Probe which prevents them from taking damage from Irradiate. Returns true if this unit type has the robotic property, and false otherwise.
     */
    boolean isRobotic();

    /**
     * Checks if this unit type is capable of detecting units that are cloaked or burrowed. Returns true if this unit type is a detector by default, false if it does not have this property
     */
    boolean isDetector();

    /**
     * Checks if this unit type is capable of storing resources such as Mineral Fields. Resources are harvested from resource containers. Returns true if this unit type may contain resources that can be harvested, false otherwise.
     */
    boolean isResourceContainer();

    /**
     * Checks if this unit type is a resource depot. Resource depots must be placed a certain distance from resources. Resource depots are typically the main building for any particular race. Workers will return resources to the nearest resource depot. Example: if ( BWAPI::Broodwar->self() ) { BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u : myUnits ) { if ( u->isIdle() && u->getType().isResourceDepot() ) u->train( u->getType().getRace().getWorker() ); } } Returns true if the unit type is a resource depot, false if it is not.
     */
    boolean isResourceDepot();

    /**
     * Checks if this unit type is a refinery. A refinery is a structure that is placed on top of a Vespene Geyser . Refinery types are Refinery , Extractor , and Assimilator. Example: if ( BWAPI::Broodwar->self() ) { BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u : myUnits ) { if ( u->getType().isRefinery() ) { int nWorkersAssigned = u->getClientInfo<int>('work'); if ( nWorkersAssigned < 3 ) { Unit pClosestIdleWorker = u->getClosestUnit(BWAPI::Filter::IsWorker && BWAPI::Filter::IsIdle); if ( pClosestIdleWorker ) { // gather from the refinery (and check if successful) if ( pClosestIdleWorker->gather(u) ) { // set a back reference for when the unit is killed or re-assigned (code not provided) pClosestIdleWorker->setClientInfo(u, 'ref'); // Increment the number of workers assigned and associate it with the refinery ++nWorkersAssigned; u->setClientInfo(nWorkersAssigned, 'work'); } } } // workers < 3 } // isRefinery } // for } Returns true if this unit type is a refinery, and false if it is not.
     */
    boolean isRefinery();

    /**
     * Checks if this unit type is a worker unit. Worker units can harvest resources and build structures. Worker unit types include the SCV , Probe, and Drone. Returns true if this unit type is a worker, and false if it is not.
     */
    boolean isWorker();

    /**
     * Checks if this structure is powered by a psi field. Structures powered by psi can only be placed near a Pylon. If the Pylon is destroyed, then this unit will lose power. Returns true if this unit type can only be placed in a psi field, false otherwise. Note If this function returns a successful state, then the following function calls will also return a successful state: isBuilding(), getRace() == Races::Protoss
     */
    boolean requiresPsi();

    /**
     * Checks if this structure must be placed on Zerg creep. Returns true if this unit type requires creep, false otherwise. Note If this function returns a successful state, then the following function calls will also return a successful state: isBuilding(), getRace() == Races::Zerg
     */
    boolean requiresCreep();

    /**
     * Checks if this unit type spawns two units when being hatched from an Egg. This is only applicable to Zerglings and Scourges. Returns true if morphing this unit type will spawn two of them, and false if only one is spawned.
     */
    boolean isTwoUnitsInOneEgg();

    /**
     * Checks if this unit type has the capability to use the Burrow technology when it is researched. Note The Lurker can burrow even without researching the ability. See also TechTypes::Burrow Returns true if this unit can use the Burrow ability, and false otherwise. Note If this function returns a successful state, then the following function calls will also return a successful state: getRace() == Races::Zerg, !isBuilding(), canMove()
     */
    boolean isBurrowable();

    /**
     * Checks if this unit type has the capability to use a cloaking ability when it is researched. This applies only to Wraiths and Ghosts, and does not include units which are permanently cloaked. Returns true if this unit has a cloaking ability, false otherwise. See also hasPermanentCloak, TechTypes::Cloaking_Field, TechTypes::Personnel_Cloaking
     */
    boolean isCloakable();

    /**
     * Checks if this unit is a structure. This includes Mineral Fields and Vespene Geysers. Returns true if this unit is a building, and false otherwise.
     */
    boolean isBuilding();

    /**
     * Checks if this unit is an add-on. Add-ons are attachments used by some Terran structures such as the Comsat Station. Returns true if this unit is an add-on, and false otherwise. Note If this function returns a successful state, then the following function calls will also return a successful state: getRace() == Races::Terran, isBuilding()
     */
    boolean isAddon();

    /**
     * Checks if this structure has the capability to use the lift-off command. Returns true if this unit type is a flyable building, false otherwise. Note If this function returns a successful state, then the following function calls will also return a successful state: isBuilding()
     */
    boolean isFlyingBuilding();

    /**
     * Checks if this unit type is a neutral type, such as critters and resources. Returns true if this unit is intended to be neutral, and false otherwise.
     */
    boolean isNeutral();

    /**
     * Checks if this unit type is a hero. Heroes are types that the player cannot obtain normally, and are identified by the white border around their icon when selected with a group. Note There are two non-hero units included in this set, the Civilian and Dark Templar Hero. Returns true if this unit type is a hero type, and false otherwise.
     */
    boolean isHero();

    /**
     * Checks if this unit type is a powerup. Powerups can be picked up and carried by workers. They are usually only seen in campaign maps and Capture the Flag. Returns true if this unit type is a powerup type, and false otherwise.
     */
    boolean isPowerup();

    /**
     * Checks if this unit type is a beacon. Each race has exactly one beacon each. They are UnitTypes::Special_Zerg_Beacon, UnitTypes::Special_Terran_Beacon, and UnitTypes::Special_Protoss_Beacon. See also isFlagBeacon Returns true if this unit type is one of the three race beacons, and false otherwise.
     */
    boolean isBeacon();

    /**
     * Checks if this unit type is a flag beacon. Each race has exactly one flag beacon each. They are UnitTypes::Special_Zerg_Flag_Beacon, UnitTypes::Special_Terran_Flag_Beacon, and UnitTypes::Special_Protoss_Flag_Beacon. Flag beacons spawn a Flag after some ARBITRARY I FORGOT AMOUNT OF FRAMES. See also isBeacon Returns true if this unit type is one of the three race flag beacons, and false otherwise.
     */
    boolean isFlagBeacon();

    /**
     * Checks if this structure is special and cannot be obtained normally within the game. Returns true if this structure is a special building, and false otherwise. Note If this function returns a successful state, then the following function calls will also return a successful state: isBuilding()
     */
    boolean isSpecialBuilding();

    /**
     * Identifies if this unit type is used to complement some abilities. These include UnitTypes::Spell_Dark_Swarm, UnitTypes::Spell_Disruption_Web, and UnitTypes::Spell_Scanner_Sweep, which correspond to TechTypes::Dark_Swarm, TechTypes::Disruption_Web, and TechTypes::Scanner_Sweep respectively. Returns true if this unit type is used for an ability, and false otherwise.
     */
    boolean isSpell();

    /**
     * Checks if this structure type produces creep. That is, the unit type spreads creep over a wide area so that Zerg structures can be placed on it. Returns true if this unit type spreads creep. Note If this function returns a successful state, then the following function calls will also return a successful state: getRace() == Races::Zerg, isBuilding() Since 4.1.2
     */
    boolean producesCreep();

    /**
     * Checks if this unit type produces larva. This is essentially used to check if the unit type is a Hatchery, Lair, or Hive. Returns true if this unit type produces larva. Note If this function returns a successful state, then the following function calls will also return a successful state: getRace() == Races::Zerg, isBuilding()
     */
    boolean producesLarva();

    /**
     * Checks if this unit type is a mineral field and contains a resource amount. This indicates that the unit type is either UnitTypes::Resource_Mineral_Field, UnitTypes::Resource_Mineral_Field_Type_2, or UnitTypes::Resource_Mineral_Field_Type_3. Returns true if this unit type is a mineral field resource.
     */
    boolean isMineralField();

    /**
     * Checks if this unit type is a neutral critter. Returns true if this unit type is a critter, and false otherwise. Example usage: BWAPI::Position myBasePosition( BWAPI::Broodwar->self()->getStartLocation() ); BWAPI::UnitSet unitsAroundTheBase = BWAPI::Broodwar->getUnitsInRadius(myBasePosition, 1024, !BWAPI::Filter::IsOwned && !BWAPI::Filter::IsParasited); for ( auto u : unitsAroundTheBase ) { if ( u->getType().isCritter() && !u->isInvincible() ) { BWAPI::Unit myQueen = u->getClosestUnit(BWAPI::Filter::GetType == BWAPI::UnitTypes::Zerg_Queen && BWAPI::Filter::IsOwned); if ( myQueen ) myQueen->useTech(BWAPI::TechTypes::Parasite, u); } }
     */
    boolean isCritter();

    /**
     * Checks if this unit type is capable of constructing an add-on. An add-on is an extension or attachment for Terran structures, specifically the Command Center, Factory, Starport, and Science Facility. Returns true if this unit type can construct an add-on, and false if it can not. See also isAddon
     */
    boolean canBuildAddon();

    /**
     * Retrieves the set of technologies that this unit type is capable of researching. Note Some maps have special parameters that disable certain technologies. Use PlayerInterface::isResearchAvailable to determine if a technology is actually available in the current game for a specific player. Returns TechType::set containing the technology types that can be researched. See also PlayerInterface::isResearchAvailable Since 4.1.2
     */
    List<TechType> researchesWhat();

    /**
     * Retrieves the set of upgrades that this unit type is capable of upgrading. Note Some maps have special upgrade limitations. Use PlayerInterface::getMaxUpgradeLevel to check if an upgrade is available. Returns UpgradeType::set containing the upgrade types that can be upgraded. See also PlayerInterface::getMaxUpgradeLevel Since 4.1.2
     */
    List<UpgradeType> upgradesWhat();
}
