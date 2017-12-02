package gg.fel.cvut.cz.api;

import static gg.fel.cvut.cz.api.ITilePosition.SIZE_IN_PIXELS;

import gg.fel.cvut.cz.enums.UnitSizeTypeEnum;
import gg.fel.cvut.cz.enums.UnitTypeEnum;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * The IUnitType is used to get information about a particular type of unit, such as its cost, build
 * time, weapon, hit points, abilities, etc. See also UnitInterface::getType, UnitTypes
 */
public interface IUnitType extends InGameInterface, Serializable {

  /**
   * Retrieves the IRace that the unit type belongs to. Returns IRace indicating the race that owns
   * this unit type. Return values IRace::None indicating that the unit type does not belong to any
   * particular race (a critter for example).
   */
  Optional<IRace> getRace();

  UnitTypeEnum getUnitType();

  /**
   * Obtains the source unit type that is used to build or train this unit type, as well as the
   * amount of them that are required. Returns std::pair in which the first value is the IUnitType
   * that builds this unit type, and the second value is the number of those types that are required
   * (this value is 2 for Archons, and 1 for all other types). Return values pair(UnitTypes::None,0)
   * If this unit type cannot be made by the player.
   */
  Optional<Tuple<IUnitType, Integer>> whatBuilds();

  /**
   * Retrieves the immediate technology tree requirements to make this unit type. Returns std::map
   * containing a IUnitType to number mapping of UnitTypes required.
   */
  Optional<Map<IUnitType, Integer>> requiredUnits();

  /**
   * Identifies the required ITechType in order to create certain units. Note The only unit that
   * requires a technology is the Lurker, which needs Lurker Aspect. Returns ITechType indicating
   * the technology that must be researched in order to create this unit type. Return values
   * TechTypes::None If creating this unit type does not require a technology to be researched.
   */
  Optional<ITechType> requiredTech();

  /**
   * Retrieves the cloaking technology associated with certain units. Returns ITechType referring to
   * the cloaking technology that this unit type uses as an ability. Return values TechTypes::None
   * If this unit type does not have an active cloak ability.
   */
  Optional<ITechType> cloakingTech();

  /**
   * Retrieves the set of abilities that this unit can use, provided it is available to you in the
   * game. Returns Set of TechTypes containing ability information.
   */
  Optional<Set<ITechType>> abilities();

  /**
   * Retrieves the set of upgrades that this unit can use to enhance its fighting ability. Returns
   * Set of UpgradeTypes containing upgrade types that will impact this unit type.
   */
  Optional<Set<IUpgradeType>> upgrades();

  /**
   * Retrieves the upgrade type used to increase the armor of this unit type. For each upgrade, this
   * unit type gains +1 additional armor. Returns IUpgradeType indicating the upgrade that increases
   * this unit type's armor amount.
   */
  Optional<IUpgradeType> armorUpgrade();

  /**
   * Retrieves the default maximum amount of hit points that this unit type can have. Note This
   * value may not necessarily match the value seen in the Use Map Settings game type. Returns
   * Integer indicating the maximum amount of hit points for this unit type.
   */
  Optional<Integer> maxHitPoints();

  /**
   * Retrieves the default maximum amount of shield points that this unit type can have. Note This
   * value may not necessarily match the value seen in the Use Map Settings game type. Returns
   * Integer indicating the maximum amount of shield points for this unit type. Return values 0 If
   * this unit type does not have shields.
   */
  Optional<Integer> maxShields();

  /**
   * Retrieves the maximum amount of energy this unit type can have by default. Returns Integer
   * indicating the maximum amount of energy for this unit type. Return values 0 If this unit does
   * not gain energy for abilities.
   */
  Optional<Integer> maxEnergy();

  /**
   * Retrieves the default amount of armor that the unit type starts with, excluding upgrades. Note
   * This value may not necessarily match the value seen in the Use Map Settings game type. Returns
   * The amount of armor the unit type has.
   */
  Optional<Integer> armor();

  /**
   * Retrieves the default mineral price of purchasing the unit. Note This value may not necessarily
   * match the value seen in the Use Map Settings game type. Returns Mineral cost of the unit.
   */
  Optional<Integer> mineralPrice();

  /**
   * Retrieves the default vespene gas price of purchasing the unit. Note This value may not
   * necessarily match the value seen in the Use Map Settings game type. Returns Vespene gas cost of
   * the unit.
   */
  Optional<Integer> gasPrice();

  /**
   * Retrieves the default time, in frames, needed to train, morph, or build the unit. Note This
   * value may not necessarily match the value seen in the Use Map Settings game type. Returns
   * Number of frames needed in order to build the unit. See also UnitInterface::getRemainingBuildTime
   */
  Optional<Integer> buildTime();

  /**
   * Retrieves the amount of supply that this unit type will use when created. It will use the
   * supply pool that is appropriate for its IRace. Note In Starcraft programming, the managed
   * supply values are double than what they appear in the game. The reason for this is because
   * Zerglings use 0.5 visible supply. Returns Integer containing the supply required to build this
   * unit. See also supplyProvided, PlayerInterface::supplyTotal, PlayerInterface::supplyUsed
   */
  Optional<Integer> supplyRequired();

  /**
   * Retrieves the amount of supply that this unit type produces for its appropriate IRace's supply
   * pool. Note In Starcraft programming, the managed supply values are double than what they appear
   * in the game. The reason for this is because Zerglings use 0.5 visible supply. See also
   * supplyRequired, PlayerInterface::supplyTotal, PlayerInterface::supplyUsed
   */
  Optional<Integer> supplyProvided();

  /**
   * Retrieves the amount of space required by this unit type to fit inside a Bunker or
   * Transport(Dropship, Shuttle, Overlord ). Returns Amount of space required by this unit type for
   * transport. Return values 255 If this unit type can not be transported. See also spaceProvided
   */
  Optional<Integer> spaceRequired();

  /**
   * Retrieves the amount of space provided by this Bunker or Transport(Dropship, Shuttle, Overlord
   * ) for unit transportation. Returns The number of slots provided by this unit type. See also
   * spaceRequired
   */
  Optional<Integer> spaceProvided();

  /**
   * Retrieves the amount of score points awarded for constructing this unit type. This value is
   * used for calculating scores in the post-game score screen. Returns Number of points awarded for
   * constructing this unit type. See also destroyScore
   */
  Optional<Integer> buildScore();

  /**
   * Retrieves the amount of score points awarded for killing this unit type. This value is used for
   * calculating scores in the post-game score screen. Returns Number of points awarded for killing
   * this unit type. See also buildScore
   */
  Optional<Integer> destroyScore();

  /**
   * Retrieves the UnitSizeTypeEnum of this unit, which is used in calculations along with weapon
   * damage types to determine the amount of damage that will be dealt to this type. Returns
   * UnitSizeTypeEnum indicating the conceptual size of the unit type. See also
   * IWeaponType::damageType
   */
  Optional<UnitSizeTypeEnum> size();

  /**
   * Retrieves the width of this unit type, in tiles. Used for determining the tile size of
   * structures. Returns Width of this unit type, in tiles.
   */
  Optional<Integer> tileWidth();

  /**
   * Retrieves the height of this unit type, in tiles. Used for determining the tile size of
   * structures. Returns Height of this unit type, in tiles.
   */
  Optional<Integer> tileHeight();

  /**
   * A macro for retrieving the width of the unit type, which is calculated using dimensionLeft +
   * dimensionRight + 1. Returns Width of the unit, in pixels.
   */
  Optional<Integer> width();

  /**
   * A macro for retrieving the height of the unit type, which is calculated using dimensionUp +
   * dimensionDown + 1. Returns Height of the unit, in pixels.
   */
  Optional<Integer> height();

  /**
   * Retrieves the range at which this unit type will start targeting enemy units. Returns Distance
   * at which this unit type begins to seek out enemy units, in pixels.
   */
  Optional<Integer> seekRange();

  /**
   * Retrieves the sight range of this unit type. Returns Sight range of this unit type, measured in
   * pixels.
   */
  Optional<Integer> sightRange();

  /**
   * Retrieves this unit type's weapon type used when attacking targets on the ground. Returns
   * IWeaponType used as this unit type's ground weapon. See also maxGroundHits, airWeapon
   */
  Optional<IWeaponType> groundWeapon();

  /**
   * Retrieves the maximum number of hits this unit can deal to a ground target using its ground
   * weapon. This value is multiplied by the ground weapon's damage to calculate the unit type's
   * damage potential. Returns Maximum number of hits given to ground targets. See also
   * groundWeapon, maxAirHits
   */
  Optional<Integer> maxGroundHits();

  /**
   * Retrieves this unit type's weapon type used when attacking targets in the air. Returns
   * IWeaponType used as this unit type's air weapon. See also maxAirHits, groundWeapon
   */
  Optional<IWeaponType> airWeapon();

  /**
   * Retrieves the maximum number of hits this unit can deal to a flying target using its air
   * weapon. This value is multiplied by the air weapon's damage to calculate the unit type's damage
   * potential. Returns Maximum number of hits given to air targets. See also airWeapon,
   * maxGroundHits
   */
  Optional<Integer> maxAirHits();

  /**
   * Retrieves this unit type's top movement speed with no upgrades. Note That some units have
   * inconsistent movement and this value is sometimes an approximation. Returns The approximate top
   * speed, in pixels per frame, as a double. For liftable Terran structures, this function returns
   * their movement speed while lifted.
   */
  Optional<Double> topSpeed();

  /**
   * Retrieves the unit's acceleration amount. Returns How fast the unit can accelerate to its top
   * speed.
   */
  Optional<Integer> acceleration();

  /**
   * Retrieves the unit's halting distance. This determines how fast a unit can stop moving. Returns
   * A halting distance value.
   */
  Optional<Integer> haltDistance();

  /**
   * Retrieves a unit's turning radius. This determines how fast a unit can turn. Returns A turn
   * radius value.
   */
  Optional<Integer> turnRadius();

  /**
   * Determines if a unit can train other units. For example, UnitTypes::Terran_Barracks.canProduce()
   * will return true, while UnitTypes::Terran_Marine.canProduce() will return false. This is also
   * true for two non-structures: Carrier (can produce interceptors) and Reaver (can produce
   * scarabs). Returns true if this unit type can have a production queue, and false otherwise.
   */
  Optional<Boolean> canProduce();

  /**
   * Checks if this unit is capable of attacking. Note This function returns false for units that
   * can only inflict damage via special abilities, such as the High Templar. Returns true if this
   * unit type is capable of damaging other units with a standard attack, and false otherwise.
   */
  Optional<Boolean> canAttack();

  /**
   * Checks if this unit type is capable of movement. Note Buildings will return false, including
   * Terran liftable buildings which are capable of moving when lifted. Returns true if this unit
   * can use a movement command, and false if they cannot move.
   */
  Optional<Boolean> canMove();

  /**
   * Checks if this unit type is a flying unit. Flying units ignore ground pathing and collisions.
   * Returns true if this unit type is in the air by default, and false otherwise.
   */
  Optional<Boolean> isFlyer();

  /**
   * Checks if this unit type can regenerate hit points. This generally applies to Zerg units.
   * Returns true if this unit type regenerates its hit points, and false otherwise.
   */
  Optional<Boolean> regeneratesHP();

  /**
   * Checks if this unit type has the capacity to store energy and use it for special abilities.
   * Returns true if this unit type generates energy, and false if it does not have an energy pool.
   */
  Optional<Boolean> isSpellcaster();

  /**
   * Checks if this unit type is permanently cloaked. This means the unit type is always cloaked and
   * requires a detector in order to see it. Returns true if this unit type is permanently cloaked,
   * and false otherwise.
   */
  Optional<Boolean> hasPermanentCloak();

  /**
   * Checks if this unit type is invincible by default. Invincible units cannot take damage. Returns
   * true if this unit type is invincible, and false if it is vulnerable to attacks.
   */
  Optional<Boolean> isInvincible();

  /**
   * Checks if this unit is an organic unit. The organic property is required for some abilities
   * such as Heal. Returns true if this unit type has the organic property, and false otherwise.
   */
  Optional<Boolean> isOrganic();

  /**
   * Checks if this unit is mechanical. The mechanical property is required for some actions such as
   * Repair. Returns true if this unit type has the mechanical property, and false otherwise.
   */
  Optional<Boolean> isMechanical();

  /**
   * Checks if this unit is robotic. The robotic property is applied to robotic units such as the
   * Probe which prevents them from taking damage from Irradiate. Returns true if this unit type has
   * the robotic property, and false otherwise.
   */
  Optional<Boolean> isRobotic();

  /**
   * Checks if this unit type is capable of detecting units that are cloaked or burrowed. Returns
   * true if this unit type is a detector by default, false if it does not have this property
   */
  Optional<Boolean> isDetector();

  /**
   * Checks if this unit type is capable of storing resources such as Mineral Fields. Resources are
   * harvested from resource containers. Returns true if this unit type may contain resources that
   * can be harvested, false otherwise.
   */
  Optional<Boolean> isResourceContainer();

  /**
   * Checks if this unit type is a resource depot. Resource depots must be placed a certain distance
   * from resources. Resource depots are typically the main building for any particular race.
   * Workers will return resources to the nearest resource depot. Example: if (
   * BWAPI::Broodwar->self() ) { BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for (
   * auto u : myUnits ) { if ( u->isIdle() && u->getType().isResourceDepot() ) u->train(
   * u->getType().getRace().getWorker() ); } } Returns true if the unit type is a resource depot,
   * false if it is not.
   */
  Optional<Boolean> isResourceDepot();

  /**
   * Checks if this unit type is a refinery. A refinery is a structure that is placed on top of a
   * Vespene Geyser . Refinery types are Refinery , Extractor , and Assimilator. Example: if (
   * BWAPI::Broodwar->self() ) { BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for (
   * auto u : myUnits ) { if ( u->getType().isRefinery() ) { int nWorkersAssigned =
   * u->getClientInfo<int>('work'); if ( nWorkersAssigned < 3 ) { IUnit pClosestIdleWorker =
   * u->getClosestUnit(BWAPI::Filter::IsWorker && BWAPI::Filter::IsIdle); if ( pClosestIdleWorker )
   * { // gather from the refinery (and check if successful) if ( pClosestIdleWorker->gather(u) ) {
   * // set a back reference for when the unit is killed or re-assigned (code not provided)
   * pClosestIdleWorker->setClientInfo(u, 'ref'); // Increment the number of workers assigned and
   * associate it with the refinery ++nWorkersAssigned; u->setClientInfo(nWorkersAssigned, 'work');
   * } } } // workers < 3 } // isRefinery } // for } Returns true if this unit type is a refinery,
   * and false if it is not.
   */
  Optional<Boolean> isRefinery();

  /**
   * Checks if this unit type is a worker unit. Worker units can harvest resources and build
   * structures. Worker unit types include the SCV , Probe, and Drone. Returns true if this unit
   * type is a worker, and false if it is not.
   */
  Optional<Boolean> isWorker();

  /**
   * Checks if this structure is powered by a psi field. Structures powered by psi can only be
   * placed near a Pylon. If the Pylon is destroyed, then this unit will lose power. Returns true if
   * this unit type can only be placed in a psi field, false otherwise. Note If this function
   * returns a successful state, then the following function calls will also return a successful
   * state: isBuilding(), getRace() == Races::Protoss
   */
  Optional<Boolean> requiresPsi();

  /**
   * Checks if this structure must be placed on Zerg creep. Returns true if this unit type requires
   * creep, false otherwise. Note If this function returns a successful state, then the following
   * function calls will also return a successful state: isBuilding(), getRace() == Races::Zerg
   */
  Optional<Boolean> requiresCreep();

  /**
   * Checks if this unit type spawns two units when being hatched from an Egg. This is only
   * applicable to Zerglings and Scourges. Returns true if morphing this unit type will spawn two of
   * them, and false if only one is spawned.
   */
  Optional<Boolean> isTwoUnitsInOneEgg();

  /**
   * Checks if this unit type has the capability to use the Burrow technology when it is researched.
   * Note The Lurker can burrow even without researching the ability. See also TechTypes::Burrow
   * Returns true if this unit can use the Burrow ability, and false otherwise. Note If this
   * function returns a successful state, then the following function calls will also return a
   * successful state: getRace() == Races::Zerg, !isBuilding(), canMove()
   */
  Optional<Boolean> isBurrowable();

  /**
   * Checks if this unit type has the capability to use a cloaking ability when it is researched.
   * This applies only to Wraiths and Ghosts, and does not include units which are permanently
   * cloaked. Returns true if this unit has a cloaking ability, false otherwise. See also
   * hasPermanentCloak, TechTypes::Cloaking_Field, TechTypes::Personnel_Cloaking
   */
  Optional<Boolean> isCloakable();

  /**
   * Checks if this unit is a structure. This includes Mineral Fields and Vespene Geysers. Returns
   * true if this unit is a building, and false otherwise.
   */
  Optional<Boolean> isBuilding();

  /**
   * Checks if this unit is an add-on. Add-ons are attachments used by some Terran structures such
   * as the Comsat Station. Returns true if this unit is an add-on, and false otherwise. Note If
   * this function returns a successful state, then the following function calls will also return a
   * successful state: getRace() == Races::Terran, isBuilding()
   */
  Optional<Boolean> isAddon();

  /**
   * Checks if this structure has the capability to use the lift-off command. Returns true if this
   * unit type is a flyable building, false otherwise. Note If this function returns a successful
   * state, then the following function calls will also return a successful state: isBuilding()
   */
  Optional<Boolean> isFlyingBuilding();

  /**
   * Checks if this unit type is a neutral type, such as critters and resources. Returns true if
   * this unit is intended to be neutral, and false otherwise.
   */
  Optional<Boolean> isNeutral();

  /**
   * Checks if this unit type is a hero. Heroes are types that the player cannot obtain normally,
   * and are identified by the white border around their icon when selected with a group. Note There
   * are two non-hero units included in this set, the Civilian and Dark Templar Hero. Returns true
   * if this unit type is a hero type, and false otherwise.
   */
  Optional<Boolean> isHero();

  /**
   * Checks if this unit type is a powerup. Powerups can be picked up and carried by workers. They
   * are usually only seen in campaign maps and Capture the Flag. Returns true if this unit type is
   * a powerup type, and false otherwise.
   */
  Optional<Boolean> isPowerup();

  /**
   * Checks if this unit type is a beacon. Each race has exactly one beacon each. They are
   * UnitTypes::Special_Zerg_Beacon, UnitTypes::Special_Terran_Beacon, and
   * UnitTypes::Special_Protoss_Beacon. See also isFlagBeacon Returns true if this unit type is one
   * of the three race beacons, and false otherwise.
   */
  Optional<Boolean> isBeacon();

  /**
   * Checks if this unit type is a flag beacon. Each race has exactly one flag beacon each. They are
   * UnitTypes::Special_Zerg_Flag_Beacon, UnitTypes::Special_Terran_Flag_Beacon, and
   * UnitTypes::Special_Protoss_Flag_Beacon. Flag beacons spawn a Flag after some ARBITRARY I FORGOT
   * AMOUNT OF FRAMES. See also isBeacon Returns true if this unit type is one of the three race
   * flag beacons, and false otherwise.
   */
  Optional<Boolean> isFlagBeacon();

  /**
   * Checks if this structure is special and cannot be obtained normally within the game. Returns
   * true if this structure is a special building, and false otherwise. Note If this function
   * returns a successful state, then the following function calls will also return a successful
   * state: isBuilding()
   */
  Optional<Boolean> isSpecialBuilding();

  /**
   * Identifies if this unit type is used to complement some abilities. These include
   * UnitTypes::Spell_Dark_Swarm, UnitTypes::Spell_Disruption_Web, and
   * UnitTypes::Spell_Scanner_Sweep, which correspond to TechTypes::Dark_Swarm,
   * TechTypes::Disruption_Web, and TechTypes::Scanner_Sweep respectively. Returns true if this unit
   * type is used for an ability, and false otherwise.
   */
  Optional<Boolean> isSpell();

  /**
   * Checks if this structure type produces creep. That is, the unit type spreads creep over a wide
   * area so that Zerg structures can be placed on it. Returns true if this unit type spreads creep.
   * Note If this function returns a successful state, then the following function calls will also
   * return a successful state: getRace() == Races::Zerg, isBuilding() Since 4.1.2
   */
  Optional<Boolean> producesCreep();

  /**
   * Checks if this unit type produces larva. This is essentially used to check if the unit type is
   * a Hatchery, Lair, or Hive. Returns true if this unit type produces larva. Note If this function
   * returns a successful state, then the following function calls will also return a successful
   * state: getRace() == Races::Zerg, isBuilding()
   */
  Optional<Boolean> producesLarva();

  /**
   * Checks if this unit type is a mineral field and contains a resource amount. This indicates that
   * the unit type is either UnitTypes::Resource_Mineral_Field, UnitTypes::Resource_Mineral_Field_Type_2,
   * or UnitTypes::Resource_Mineral_Field_Type_3. Returns true if this unit type is a mineral field
   * resource.
   */
  Optional<Boolean> isMineralField();

  /**
   * Checks if this unit type is a neutral critter. Returns true if this unit type is a critter, and
   * false otherwise. Example usage: BWAPI::UpdatablePosition myBasePosition(
   * BWAPI::Broodwar->self()->getStartLocation() ); BWAPI::UnitSet unitsAroundTheBase =
   * BWAPI::Broodwar->getUnitsInRadius(myBasePosition, 1024, !BWAPI::Filter::IsOwned &&
   * !BWAPI::Filter::IsParasited); for ( auto u : unitsAroundTheBase ) { if (
   * u->getType().isCritter() && !u->isInvincible() ) { BWAPI::IUnit myQueen =
   * u->getClosestUnit(BWAPI::Filter::GetType == BWAPI::UnitTypes::Zerg_Queen &&
   * BWAPI::Filter::IsOwned); if ( myQueen ) myQueen->useTech(BWAPI::TechTypes::Parasite, u); } }
   */
  Optional<Boolean> isCritter();

  /**
   * Checks if this unit type is capable of constructing an add-on. An add-on is an extension or
   * attachment for Terran structures, specifically the Command Center, Factory, Starport, and
   * Science Facility. Returns true if this unit type can construct an add-on, and false if it can
   * not. See also isAddon
   */
  Optional<Boolean> canBuildAddon();

  /**
   * Retrieves the set of technologies that this unit type is capable of researching. Note Some maps
   * have special parameters that disable certain technologies. Use PlayerInterface::isResearchAvailable
   * to determine if a technology is actually available in the current game for a specific player.
   * Returns ITechType::set containing the technology types that can be researched. See also
   * PlayerInterface::isResearchAvailable Since 4.1.2
   */
  Optional<Set<ITechType>> researchesWhat();

  /**
   * Retrieves the set of upgrades that this unit type is capable of upgrading. Note Some maps have
   * special upgrade limitations. Use PlayerInterface::getMaxUpgradeLevel to check if an upgrade is
   * available. Returns IUpgradeType::set containing the upgrade types that can be upgraded. See
   * also PlayerInterface::getMaxUpgradeLevel Since 4.1.2
   */
  Optional<Set<IUpgradeType>> upgradesWhat();

  /**
   * Returns max shoot range (in build tiles) of this unit against land targets.
   */
  default Optional<Double> getShootRangeGround() {
    if (!groundWeapon().isPresent() || !groundWeapon().get().maxRange().isPresent()) {
      return Optional.empty();
    }
    return Optional.of(((double) groundWeapon().get().maxRange().get()) / SIZE_IN_PIXELS);
  }

  /**
   * Returns max shoot range (in build tiles) of this unit against air targets.
   */
  default Optional<Double> getShootRangeAir() {
    if (!airWeapon().isPresent() || !airWeapon().get().maxRange().isPresent()) {
      return Optional.empty();
    }
    return Optional.of(((double) airWeapon().get().maxRange().get()) / SIZE_IN_PIXELS);
  }

  /**
   * Returns max shoot range (in build tiles) of this unit against given <b>opponentUnit</b>.
   */
  default Optional<Double> getShootRangeAgainst(IUnitType type) {
    if (type.isFlyer().isPresent() && type.isFlyer().get()) {
      return getShootRangeAir();
    } else {
      return getShootRangeGround();
    }
  }

  default boolean isBunker() {
    return hasType(UnitTypeEnum.TerranBunker);
  }

  default boolean isSpiderMine() {
    return hasType(UnitTypeEnum.TerranVultureSpiderMine);
  }

  default boolean isLarvaOrEgg() {
    return isEgg() || isLarva();
  }

  default boolean isLarva() {
    return hasType(UnitTypeEnum.ZergLarva);
  }

  default boolean isEgg() {
    return hasType(UnitTypeEnum.ZergEgg);
  }

  /**
   * Not that we're racists, but spider mines and larvas aren't really units...
   */
  default boolean isNotActuallyUnit() {
    return isSpiderMine() || isLarvaOrEgg();
  }

  /**
   * Returns true if unit has anti-ground weapon.
   */
  default Optional<Boolean> canAttackGroundUnits() {
    if (!groundWeapon().isPresent()) {
      return Optional.empty();
    }
    return Optional.of(!groundWeapon().get().getWeaponType().equals(WeaponTypeEnum.None));
  }

  /**
   * Returns true if unit has anti-air weapon.
   */
  default Optional<Boolean> canAttackAirUnits() {
    if (!airWeapon().isPresent()) {
      return Optional.empty();
    }
    return Optional.of(!airWeapon().get().getWeaponType().equals(WeaponTypeEnum.None));
  }

  /**
   * Returns true if unit has anti-air weapon.
   */
  default boolean isMedic() {
    return hasType(UnitTypeEnum.TerranMedic);
  }

  default Optional<Boolean> isRepairableMechanically() {
    return Optional
        .of((isBuilding().isPresent() && isBuilding().get()) || (isMechanical().isPresent()
            && isMechanical().get()));
  }

  default Optional<Boolean> isHealable() {
    return Optional
        .of((isOrganic().isPresent() && isOrganic().get()) || (isWorker().isPresent() && isWorker()
            .get()));
  }

  default Optional<Boolean> canBeHealed() {
    return Optional
        .of((isRepairableMechanically().isPresent() && isRepairableMechanically().get()) || (
            isHealable().isPresent() && isHealable().get()));
  }

  default boolean hasType(UnitTypeEnum... UnitTypeEnums) {
    return Stream.of(UnitTypeEnums).anyMatch(UnitTypeEnum -> UnitTypeEnum.equals(getUnitType()));
  }

  default boolean isBase() {
    return hasType(UnitTypeEnum.TerranCommandCenter, UnitTypeEnum.ProtossNexus,
        UnitTypeEnum.ZergHatchery,
        UnitTypeEnum.ZergLair, UnitTypeEnum.ZergHive);
  }

  default boolean isGasBuilding() {
    return hasType(UnitTypeEnum.TerranRefinery, UnitTypeEnum.ProtossAssimilator,
        UnitTypeEnum.ZergExtractor);
  }

  default boolean isSupplyUnit() {
    return hasType(UnitTypeEnum.ProtossPylon, UnitTypeEnum.TerranSupplyDepot,
        UnitTypeEnum.ZergOverlord);
  }

  default boolean isMilitaryBuilding() {
    return hasType(UnitTypeEnum.TerranBunker, UnitTypeEnum.TerranMissileTurret,
        UnitTypeEnum.ProtossPhotonCannon,
        UnitTypeEnum.ZergSunkenColony, UnitTypeEnum.ZergSporeColony,
        UnitTypeEnum.ZergCreepColony);
  }

  default boolean isMilitaryBuildingAntiAir() {
    return hasType(UnitTypeEnum.TerranBunker, UnitTypeEnum.ProtossPhotonCannon,
        UnitTypeEnum.ZergSporeColony);
  }

  default boolean isMilitaryBuildingAntiGround() {
    return hasType(UnitTypeEnum.TerranBunker, UnitTypeEnum.ProtossPhotonCannon,
        UnitTypeEnum.ZergSunkenColony);
  }

  default boolean isMelee() {
    return hasType(UnitTypeEnum.TerranSCV, UnitTypeEnum.TerranFirebat, UnitTypeEnum.ProtossProbe,
        UnitTypeEnum.ProtossZealot, UnitTypeEnum.ProtossDarkTemplar, UnitTypeEnum.ZergDrone,
        UnitTypeEnum.ZergZergling,
        UnitTypeEnum.ZergBroodling);
  }

}
