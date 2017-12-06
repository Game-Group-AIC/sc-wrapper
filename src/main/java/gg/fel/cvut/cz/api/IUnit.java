package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.Unit;
import gg.fel.cvut.cz.enums.OrderEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The IUnit class is used to get information about individual units as well as issue orders to
 * units. Each unit in the game has a unique IUnit object, and IUnit objects are not deleted until
 * the end of the match (so you don't need to worry about unit pointers becoming invalid). Every
 * IUnit in the game is either accessible or inaccessible. To determine if an AI can readonly a
 * particular unit, BWAPI checks to see if Flag::CompleteMapInformation is enabled. So there are two
 * cases to consider - either the flag is enabled, or it is disabled: If
 * Flag::CompleteMapInformation is disabled, then a unit is accessible if and only if it is visible.
 * Note Some properties of visible enemy units will not be made available to the AI (such as the
 * contents of visible enemy dropships). If a unit is not visible, UnitInterface::exists will return
 * false, regardless of whether or not the unit exists. This is because absolutely no state
 * information on invisible enemy units is made available to the AI. To determine if an enemy unit
 * has been destroyed, the AI must watch for AIModule::onUnitDestroy messages from BWAPI, which is
 * only called for visible units which get destroyed. If Flag::CompleteMapInformation is enabled,
 * then all units that exist in the game are accessible, and UnitInterface::exists is accurate for
 * all units. Similarly AIModule::onUnitDestroy messages are generated for all units that get
 * destroyed, not just visible ones. If a IUnit is not accessible, then only the getInitial__
 * functions will be available to the AI. However for units that were owned by the player, getPlayer
 * and getType will continue to work for units that have been destroyed.
 */
@JsonDeserialize(as = Unit.class)
public interface IUnit extends InGameInterface, Serializable {

  /**
   * Retrieves a unique identifier for this unit. Returns An integer containing the unit's
   * identifier. See also getReplayID
   */
  Optional<Integer> getID();

  /**
   * Checks if the IUnit exists in the view of the BWAPI player. This is used primarily to check if
   * BWAPI has readonly to a specific unit, or if the unit is alive. This function is more general
   * and would be synonymous to an isAlive function if such a function were necessary. Return values
   * true If the unit exists on the map and is visible according to BWAPI. false If the unit is not
   * accessible or the unit is dead. In the event that this function returns false, there are two
   * cases to consider: You own the unit. This means the unit is dead. Another player owns the unit.
   * This could either mean that you don't have readonly to the unit or that the unit has died. You
   * can specifically identify dead units by polling onUnitDestroy. See also isVisible, isCompleted
   */
  Optional<Boolean> exists();

  /**
   * Retrieves the player that owns this unit. Return values IGame::neutral() If the unit is a
   * neutral unit or inaccessible. Returns The owning IPlayer interface object.
   */
  Optional<IPlayer> getPlayer();

  /**
   * Retrieves the unit's type. Return values UnitTypes::Unknown if this unit is inaccessible or
   * cannot be determined. Returns A IUnitType objects representing the unit's type. See also
   * getInitialType
   */
  Optional<IUnitType> getType();

  /**
   * Retrieves the unit's position from the upper left corner of the map in pixels. The position
   * returned is roughly the center if the unit. Note The unit bounds are defined as this value
   * plus/minus the values of IUnitType::dimensionLeft, IUnitType::dimensionUp,
   * IUnitType::dimensionRight, and IUnitType::dimensionDown, which is conveniently expressed in
   * UnitInterface::getLeft, UnitInterface::getTop, UnitInterface::getRight, and
   * UnitInterface::getBottom respectively. Return values Positions::Unknown if this unit is
   * inaccessible. Returns IPosition object representing the unit's current position. See also
   * getTilePosition, getInitialPosition, getLeft, getTop
   */
  Optional<IPosition> getPosition();

  /**
   * Retrieves the unit's build position from the upper left corner of the map in tiles. Note : This
   * tile position is the tile that is at the top left corner of the structure. Return values
   * TilePositions::Unknown if this unit is inaccessible. Returns ITilePosition object representing
   * the unit's current tile position. See also getPosition, getInitialTilePosition
   */
  Optional<ITilePosition> getTilePosition();

  /**
   * Retrieves the unit's facing direction in radians. Note A value of 0.0 means the unit is facing
   * east. Returns A double with the angle measure in radians.
   */
  Optional<Double> getAngle();

  /**
   * Retrieves the x component of the unit's velocity, measured in pixels per frame. Returns A
   * double that represents the velocity's x component. See also getVelocityY
   */
  Optional<Double> getVelocityX();

  /**
   * Retrieves the y component of the unit's velocity, measured in pixels per frame. Returns A
   * double that represents the velocity's y component. See also getVelocityX
   */
  Optional<Double> getVelocityY();

  /**
   * Retrieves the unit's current Hit Points (HP) as seen in the game. Returns An integer
   * representing the amount of hit points a unit currently has. Note In Starcraft, a unit usually
   * dies when its HP reaches 0. It is possible however, to have abnormal HP values in the Use Map
   * Settings game type and as the result of a hack over Battle.net. Such values include units that
   * have 0 HP (can't be killed conventionally) or even negative HP (death in one hit). See also
   * IUnitType::maxHitPoints, getShields, getInitialHitPoints
   */
  Optional<Integer> getHitPoints();

  /**
   * Retrieves the unit's current Shield Points (Shields) as seen in the game. Returns An integer
   * representing the amount of shield points a unit currently has. See also IUnitType::maxShields,
   * getHitPoints
   */
  Optional<Integer> getShields();

  /**
   * Retrieves the unit's current Energy Points (Energy) as seen in the game. Returns An integer
   * representing the amount of energy points a unit currently has. Note Energy is required in order
   * for units to use abilities. See also IUnitType::maxEnergy
   */
  Optional<Integer> getEnergy();

  /**
   * Retrieves the resource amount from a resource container, such as a Mineral Field and Vespene
   * Geyser. If the unit is inaccessible, then the last known resource amount is returned. Returns
   * An integer representing the last known amount of resources remaining in this resource. See also
   * getInitialResources
   */
  Optional<Integer> getResources();

  /**
   * Retrieves the frame number that sent the last successful command. Note This value is comparable
   * to IGame::getFrameCount. Returns The frame number that sent the last successfully processed
   * command to BWAPI. See also IGame::getFrameCount, getLastCommand
   */
  Optional<Integer> getLastCommandFrame();

  /**
   * Retrieves the last successful command that was sent to BWAPI. Returns A UnitCommand object
   * containing information about the command that was processed. See also getLastCommandFrame
   */
  Optional<UnitCommand> getLastCommand();

  /**
   * Retrieves the IPlayer that last attacked this unit. Returns IPlayer interface object
   * representing the player that last attacked this unit. Return values nullptr If this unit was
   * not attacked. Note If this function returns a successful state, then the following function
   * calls will also return a successful state: exists()
   */
  Optional<IPlayer> getLastAttackingPlayer();

  /**
   * Retrieves the initial type of the unit. This is the type that the unit starts as in the
   * beginning of the game. This is used to readonly the types of static neutral units such as
   * mineral fields when they are not visible. Returns IUnitType of this unit as it was when it was
   * created. Return values UnitTypes::Unknown if this unit was not a static neutral unit in the
   * beginning of the game.
   */
  Optional<IUnitType> getInitialType();

  /**
   * Retrieves the initial position of this unit. This is the position that the unit starts at in
   * the beginning of the game. This is used to readonly the positions of static neutral units such
   * as mineral fields when they are not visible. Returns IPosition indicating the unit's initial
   * position when it was created. Return values Positions::Unknown if this unit was not a static
   * neutral unit in the beginning of the game.
   */
  Optional<IPosition> getInitialPosition();

  /**
   * Retrieves the initial build tile position of this unit. This is the tile position that the unit
   * starts at in the beginning of the game. This is used to readonly the tile positions of static
   * neutral units such as mineral fields when they are not visible. The build tile position
   * corresponds to the upper left corner of the unit. Returns ITilePosition indicating the unit's
   * initial tile position when it was created. Return values TilePositions::Unknown if this unit
   * was not a static neutral unit in the beginning of the game.
   */
  Optional<ITilePosition> getInitialTilePosition();

  /**
   * Retrieves the amount of hit points that this unit started off with at the beginning of the
   * game. The unit must be neutral. Returns Number of hit points that this unit started with.
   * Return values 0 if this unit was not a neutral unit at the beginning of the game. Note : It is
   * possible for the unit's initial hit points to differ from the maximum hit points. See also
   * IGame::getStaticNeutralUnits
   */
  Optional<Integer> getInitialHitPoints();

  /**
   * Retrieves the amount of resources contained in the unit at the beginning of the game. The unit
   * must be a neutral resource container. Returns Amount of resources that this unit started with.
   * Return values 0 if this unit was not a neutral unit at the beginning of the game, or if this
   * unit does not contain resources. It is possible that the unit simply contains 0 resources. See
   * also IGame::getStaticNeutralUnits
   */
  Optional<Integer> getInitialResources();

  /**
   * Retrieves the number of units that this unit has killed in total. Note The maximum amount of
   * recorded kills per unit is 255. Returns integer indicating this unit's kill count.
   */
  Optional<Integer> getKillCount();

  /**
   * Retrieves the number of acid spores that this unit is inflicted with. Returns Number of acid
   * spores on this unit.
   */
  Optional<Integer> getAcidSporeCount();

  /**
   * Retrieves the number of interceptors that this unit manages. This function is only for the
   * Carrier. Returns Number of interceptors in this unit.
   */
  Optional<Integer> getInterceptorCount();

  /**
   * Retrieves the number of scarabs that this unit has for use. This function is only for the
   * Reaver. Returns Number of scarabs this unit has ready.
   */
  Optional<Integer> getScarabCount();

  /**
   * Retrieves the amount of Spider Mines this unit has available. This function is only for the
   * Vulture. Returns Number of spider mines available for placement.
   */
  Optional<Integer> getSpiderMineCount();

  /**
   * Retrieves the unit's ground weapon cooldown. This value decreases every frame, until it reaches
   * 0. When the value is 0, this indicates that the unit is capable of using its ground weapon,
   * otherwise it must wait until it reaches 0. Note This value will vary, because Starcraft adds an
   * additional random value between (-1) and (+2) to the unit's weapon cooldown. Returns Number of
   * frames needed for the unit's ground weapon to become available again.
   */
  Optional<Integer> getGroundWeaponCooldown();

  /**
   * Retrieves the unit's air weapon cooldown. This value decreases every frame, until it reaches 0.
   * When the value is 0, this indicates that the unit is capable of using its air weapon, otherwise
   * it must wait until it reaches 0. Note This value will vary, because Starcraft adds an
   * additional random value between (-1) and (+2) to the unit's weapon cooldown. Returns Number of
   * frames needed for the unit's air weapon to become available again.
   */
  Optional<Integer> getAirWeaponCooldown();

  /**
   * Retrieves the unit's ability cooldown. This value decreases every frame, until it reaches 0.
   * When the value is 0, this indicates that the unit is capable of using one of its special
   * abilities, otherwise it must wait until it reaches 0. Note This value will vary, because
   * Starcraft adds an additional random value between (-1) and (+2) to the unit's ability cooldown.
   * Returns Number of frames needed for the unit's abilities to become available again.
   */
  Optional<Integer> getSpellCooldown();

  /**
   * Retrieves the amount of hit points remaining on the Defensive Matrix created by a Science
   * Vessel. The Defensive Matrix ability starts with 250 hit points when it is used. Returns Number
   * of hit points remaining on this unit's Defensive Matrix. See also getDefenseMatrixTimer,
   * isDefenseMatrixed
   */
  Optional<Integer> getDefenseMatrixPoints();

  /**
   * Retrieves the time, in frames, that the Defensive Matrix will remain active on the current
   * unit. Returns Number of frames remaining until the effect is removed. See also
   * getDefenseMatrixPoints, isDefenseMatrixed
   */
  Optional<Integer> getDefenseMatrixTimer();

  /**
   * Retrieves the time, in frames, that Ensnare will remain active on the current unit. Returns
   * Number of frames remaining until the effect is removed. See also isEnsnared
   */
  Optional<Integer> getEnsnareTimer();

  /**
   * Retrieves the time, in frames, that Irradiate will remain active on the current unit. Returns
   * Number of frames remaining until the effect is removed. See also isIrradiated
   */
  Optional<Integer> getIrradiateTimer();

  /**
   * Retrieves the time, in frames, that Lockdown will remain active on the current unit. Returns
   * Number of frames remaining until the effect is removed. See also isLockdowned
   */
  Optional<Integer> getLockdownTimer();

  /**
   * Retrieves the time, in frames, that Maelstrom will remain active on the current unit. Returns
   * Number of frames remaining until the effect is removed. See also isMaelstrommed
   */
  Optional<Integer> getMaelstromTimer();

  /**
   * Retrieves an internal timer used for the primary order. Its use is specific to the order type
   * that is currently assigned to the unit. Returns A value used as a timer for the primary order.
   * See also getOrder
   */
  Optional<Integer> getOrderTimer();

  /**
   * Retrieves the time, in frames, that Plague will remain active on the current unit. Returns
   * Number of frames remaining until the effect is removed. See also isPlagued
   */
  Optional<Integer> getPlagueTimer();

  /**
   * Retrieves the time, in frames, until this temporary unit is destroyed or removed. This is used
   * to determine the remaining time for the following units that were created by abilities:
   * Hallucination broodling Dark Swarm Disruption Web Scanner Sweep Once this value reaches 0, the
   * unit is destroyed.
   */
  Optional<Integer> getRemoveTimer();

  /**
   * Retrieves the time, in frames, that Stasis Field will remain active on the current unit.
   * Returns Number of frames remaining until the effect is removed. See also isPlagued
   */
  Optional<Integer> getStasisTimer();

  /**
   * Retrieves the time, in frames, that Stim Packs will remain active on the current unit. Returns
   * Number of frames remaining until the effect is removed. See also isPlagued
   */
  Optional<Integer> getStimTimer();

  /**
   * Retrieves the building type that a worker (SCV, Probe, Drone) is about to construct. If the
   * unit is morphing or is an incomplete structure, then this returns the IUnitType that it will
   * become when it has completed morphing/constructing. Returns IUnitType indicating the type that
   * a worker (SCV, Probe, Drone) is about to construct, or an incomplete unit will be when
   * completed.
   */
  Optional<IUnitType> getBuildType();

  /**
   * Retrieves the Set of units queued up to be trained. Returns a IUnitType::set containing all the
   * types that are in this facades's training queue. See also train, cancelTrain, isTraining
   */
  Optional<Stream<IUnitType>> getTrainingQueue();

  /**
   * Retrieves the technology that this unit is currently researching. Returns ITechType indicating
   * the technology being researched by this unit. Return values TechTypes::None if this unit is not
   * researching anything. See also research, cancelResearch, isResearching,
   * getRemainingResearchTime
   */
  Optional<ITechType> getTech();

  /**
   * Retrieves the upgrade that this unit is currently upgrading. Returns IUpgradeType indicating
   * the upgrade in progress by this unit. Return values UpgradeTypes::None if this unit is not
   * upgrading anything. See also upgrade, cancelUpgrade, isUpgrading, getRemainingUpgradeTime
   */
  Optional<IUpgradeType> getUpgrade();

  /**
   * Retrieves the remaining build time for a unit or structure that is being trained or
   * constructed. Returns Number of frames remaining until the unit's completion.
   */
  Optional<Integer> getRemainingBuildTime();

  /**
   * Retrieves the remaining time, in frames, of the unit that is currently being trained. Note If
   * the unit is a Hatchery, Lair, or Hive, this retrieves the amount of time until the next larva
   * spawns. Returns Number of frames remaining until the current training unit becomes completed,
   * or the number of frames remaining until the next larva spawns. Return values 0 If the unit is
   * not training or has three larvae. See also train, getTrainingQueue
   */
  Optional<Integer> getRemainingTrainTime();

  /**
   * Retrieves the amount of time until the unit is done researching its currently assigned
   * ITechType. Returns The remaining research time, in frames, for the current technology being
   * researched by this unit. Return values 0 If the unit is not researching anything. See also
   * research, cancelResearch, isResearching, getTech
   */
  Optional<Integer> getRemainingResearchTime();

  /**
   * Retrieves the amount of time until the unit is done upgrading its current upgrade. Returns The
   * remaining upgrade time, in frames, for the current upgrade. Return values 0 If the unit is not
   * upgrading anything. See also upgrade, cancelUpgrade, isUpgrading, getUpgrade
   */
  Optional<Integer> getRemainingUpgradeTime();

  /**
   * Retrieves the corresponding paired unit for SCVs and Terran structures. For example, if this
   * unit is a Factory under construction, this function will return the SCV that is constructing
   * it. If this unit is a SCV, then it will return the structure it is currently constructing.
   * Returns Paired build unit that is either constructing this unit, or being constructed by this
   * unit. Return values nullptr If there is no unit constructing this one, or this unit is not
   * constructing another unit.
   */
  Optional<IUnit> getBuildUnit();

  /**
   * Generally returns the appropriate target unit after issuing an order that accepts a target unit
   * (i.e. attack, repair, gather, etc.). To get a target that has been acquired automatically
   * without issuing an order, use getOrderTarget. Returns IUnit that is currently being targeted by
   * this unit. See also getOrderTarget
   */
  Optional<IUnit> getTarget();

  /**
   * Retrieves the target position the unit is moving to, provided a valid path to the target
   * position exists. Returns Target position of a movement action.
   */
  Optional<IPosition> getTargetPosition();

  /**
   * Retrieves the primary OrderEnum that the unit is assigned. Primary orders are distinct actions
   * such as Orders::AttackUnit and Orders::PlayerGuard. Returns The primary OrderEnum that the unit
   * is executing.
   */
  Optional<OrderEnum> getOrder();

  /**
   * Retrieves the secondary OrderEnum that the unit is assigned. Secondary orders are run in the
   * background as a sub-order. An example would be Orders::TrainFighter, because a Carrier can move
   * and train fighters at the same time. Returns The secondary OrderEnum that the unit is
   * executing.
   */
  Optional<OrderEnum> getSecondaryOrder();

  /**
   * Retrieves the unit's primary order target. This is usually set when the low level unit AI
   * acquires a new target automatically. For example if an enemy Probe comes in range of your
   * Marine, the Marine will start attacking it, and getOrderTarget will be set in this case, but
   * not getTarget. Returns The IUnit that this unit is currently targetting. See also getTarget,
   * getOrder
   */
  Optional<IUnit> getOrderTarget();

  /**
   * Retrieves the target position for the unit's order. For example, when Orders::Move is assigned,
   * getTargetPosition returns the end of the unit's path, but this returns the location that the
   * unit is trying to move to. Returns IPosition that this unit is currently targetting. See also
   * getTargetPosition, getOrder
   */
  Optional<IPosition> getOrderTargetPosition();

  /**
   * Retrieves the position the structure is rallying units to once they are completed. Returns
   * IPosition that a completed unit coming from this structure will travel to. Return values
   * Positions::None If this building does not produce units. Note If getRallyUnit is valid, then
   * this value is ignored. See also setRallyPoint, getRallyUnit
   */
  Optional<IPosition> getRallyPosition();

  /**
   * Retrieves the unit the structure is rallying units to once they are completed. Units will then
   * follow the targetted unit. Returns IUnit that a completed unit coming from this structure will
   * travel to. Return values nullptr If the structure is not rallied to a unit or it does not
   * produce units. Note A rallied unit takes precedence over a rallied position. That is if the
   * return value is valid(non-null), then getRallyPosition is ignored. See also setRallyPoint,
   * getRallyPosition
   */
  Optional<IUnit> getRallyUnit();

  /**
   * Retrieves the add-on that is attached to this unit. Returns IUnit interface that represents the
   * add-on that is attached to this unit. Return values nullptr if this unit does not have an
   * add-on.
   */
  Optional<IUnit> getAddon();

  /**
   * Retrieves the Nydus Canal that is attached to this one. Every Nydus Canal can place a "Nydus
   * Exit" which, when connected, can be travelled through by Zerg units. Returns IUnit interface
   * representing the Nydus Canal connected to this one. Return values nullptr if the unit is not a
   * Nydus Canal, is not owned, or has not placed a Nydus Exit.
   */
  Optional<IUnit> getNydusExit();

  /**
   * Retrieves the power-up that the worker unit is holding. Power-ups are special units such as the
   * Flag in the Capture The Flag game type, which can be picked up by worker units. Note If your
   * bot is strictly melee/1v1, then this method is not necessary. Returns The IUnit interface
   * object that represents the power-up. Return values nullptr If the unit is not carrying
   * anything. Example BWAPI::Unitset myUnits = BWAPI::Broodwar->self()getUnits(); for ( auto u =
   * myUnits.begin(); u != myUnits.end(); ++u ) { // If we are carrying a flag if ( u->getPowerUp()
   * && u->getPowerUp()->getType() == BWAPI::UnitTypes::Powerup_Flag ) u->move(
   * u->getClosestUnit(BWAPI::Filter::IsFlagBeacon && BWAPI::Filter::IsOwned) ); // return it to our
   * flag beacon to score } Note If this function returns a successful state, then the following
   * function calls will also return a successful state: getType().isWorker(), isCompleted()
   */
  Optional<IUnit> getPowerUp();

  /**
   * Retrieves the Transport(Dropship, Shuttle, Overlord ) or Bunker unit that has this unit loaded
   * inside of it. Returns IUnit interface object representing the Transport(Dropship, Shuttle,
   * Overlord ) containing this unit. Return values nullptr if this unit is not in a
   * Transport(Dropship, Shuttle, Overlord ).
   */
  Optional<IUnit> getTransport();

  /**
   * Retrieves the set of units that are contained within this Bunker or Transport(Dropship,
   * Shuttle, Overlord ). Returns A Unitset object containing all of the units that are loaded
   * inside of the current unit.
   */
  Optional<Stream<IUnit>> getLoadedUnits();

  /**
   * Retrieves the remaining unit-space available for Bunkers and Transports(Dropships, Shuttles,
   * Overlords ). Returns The number of spots available to transport a unit. See also
   * getLoadedUnits
   */
  Optional<Integer> getSpaceRemaining();

  /**
   * Retrieves the parent Carrier that owns this Interceptor. Returns The parent Carrier unit that
   * has ownership of this one. Return values nullptr if the current unit is not an Interceptor.
   */
  Optional<IUnit> getCarrier();

  /**
   * Retrieves the set of Interceptors controlled by this unit. This is intended for Carriers.
   * Returns Unitset containing Interceptor units owned by this one.
   */
  Optional<Stream<IUnit>> getInterceptors();

  /**
   * Retrieves the parent Hatchery, Lair, or Hive that owns this particular unit. This is intended
   * for Larvae. Returns Hatchery unit that has ownership of this larva. Return values nullptr if
   * the current unit is not a Larva or has no parent.
   */
  Optional<IUnit> getHatchery();

  /**
   * Retrieves the set of Larvae that were spawned by this unit. Only Hatcheries, Lairs, and Hives
   * are capable of spawning Larvae. This is like clicking the "Select Larva" button and getting the
   * selection of Larvae. Returns Unitset containing Larva units owned by this unit. The set will be
   * empty if there are none.
   */
  Optional<Stream<IUnit>> getLarva();

  /**
   * Checks if the current unit is housing a Nuke. This is only available for Nuclear Silos. Returns
   * true if this unit has a Nuke ready, and false if there is no Nuke.
   */
  Optional<Boolean> hasNuke();

  /**
   * Checks if the current unit is accelerating. Returns true if this unit is accelerating, and
   * false otherwise
   */
  Optional<Boolean> isAccelerating();

  /**
   * Checks if this unit is currently attacking something. Returns true if this unit is attacking
   * another unit, and false if it is not.
   */
  Optional<Boolean> isAttacking();

  /**
   * Checks if this unit is currently playing an attack animation. Issuing commands while this
   * returns true may interrupt the unit's next attack sequence. Returns true if this unit is
   * currently running an attack frame, and false if interrupting the unit is feasible. Note This
   * function is only available to some unit types, specifically those that play special animations
   * when they attack.
   */
  Optional<Boolean> isAttackFrame();

  /**
   * Checks if the current unit is being constructed. This is mostly applicable to Terran structures
   * which require an SCV to be constructing a structure. Return values true if this is either a
   * Protoss structure, Zerg structure, or Terran structure being constructed by an attached SCV.
   * false if this is either completed, not a structure, or has no SCV constructing it See also
   * build, cancelConstruction, haltConstruction, isConstructing
   */
  Optional<Boolean> isBeingConstructed();

  /**
   * Checks this Mineral Field or Refinery is currently being gathered from. Returns true if this
   * unit is a resource container and being harvested by a worker, and false otherwise
   */
  Optional<Boolean> isBeingGathered();

  /**
   * Checks if this unit is currently being healed by a Medic or repaired by a SCV. Returns true if
   * this unit is being healed, and false otherwise.
   */
  Optional<Boolean> isBeingHealed();

  /**
   * Checks if this unit is currently blinded by a Medic 's Optical Flare ability. Blinded units
   * have reduced sight range and cannot detect other units. Returns true if this unit is blind, and
   * false otherwise
   */
  Optional<Boolean> isBlind();

  /**
   * Checks if the current unit is slowing down to come to a stop. Returns true if this unit is
   * breaking, false if it has stopped or is still moving at full speed.
   */
  Optional<Boolean> isBraking();

  /**
   * Checks if the current unit is burrowed, either using the Burrow ability, or is an armed Spider
   * Mine. Returns true if this unit is burrowed, and false otherwise See also burrow, unburrow
   */
  Optional<Boolean> isBurrowed();

  /**
   * Checks if this worker unit is carrying some vespene gas. Returns true if this is a worker unit
   * carrying vespene gas, and false if it is either not a worker, or not carrying gas. Example
   * BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u
   * != myUnits.end(); ++u ) { if ( u->isIdle() && (u->isCarryingGas() || u->isCarryingMinerals()) )
   * u->returnCargo(); } Note If this function returns a successful state, then the following
   * function calls will also return a successful state: isCompleted(), getType().isWorker() See
   * also returnCargo, isGatheringGas, isCarryingMinerals
   */
  Optional<Boolean> isCarryingGas();

  /**
   * Checks if this worker unit is carrying some minerals. Returns true if this is a worker unit
   * carrying minerals, and false if it is either not a worker, or not carrying minerals. Example
   * BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u
   * != myUnits.end(); ++u ) { if ( u->isIdle() && (u->isCarryingGas() || u->isCarryingMinerals()) )
   * u->returnCargo(); } Note If this function returns a successful state, then the following
   * function calls will also return a successful state: isCompleted(), getType().isWorker() See
   * also returnCargo, isGatheringMinerals, isCarryingMinerals
   */
  Optional<Boolean> isCarryingMinerals();

  /**
   * Checks if this unit is currently cloaked. Returns true if this unit is cloaked, and false if it
   * is visible. See also cloak, decloak
   */
  Optional<Boolean> isCloaked();

  /**
   * Checks if this unit has finished being constructed, trained, morphed, or warped in, and can now
   * receive orders. Returns true if this unit is completed, and false if it is under construction
   * or inaccessible.
   */
  Optional<Boolean> isCompleted();

  /**
   * Checks if a unit is either constructing something or moving to construct something. Returns
   * true when a unit has been issued an order to build a structure and is moving to the build
   * location, or is currently constructing something. See also isBeingConstructed, build,
   * cancelConstruction, haltConstruction
   */
  Optional<Boolean> isConstructing();

  /**
   * Checks if this unit has the Defensive Matrix effect. Returns true if the Defensive Matrix
   * ability was used on this unit, and false otherwise.
   */
  Optional<Boolean> isDefenseMatrixed();

  /**
   * Checks if this unit is visible or revealed by a detector unit. If this is false and isVisible
   * is true, then the unit is only partially visible and requires a detector in order to be
   * targetted. Returns true if this unit is detected, and false if it needs a detector unit nearby
   * in order to see it. Note If this function returns a successful state, then the following
   * function calls will also return a successful state: isVisible
   */
  Optional<Boolean> isDetected();

  /**
   * Checks if the Queen ability Ensnare has been used on this unit. Returns true if the unit is
   * ensnared, and false if it is not
   */
  Optional<Boolean> isEnsnared();

  /**
   * This macro function checks if this unit is in the air. That is, the unit is either a flyer or a
   * flying building. Returns true if this unit is in the air, and false if it is on the ground See
   * also IUnitType::isFlyer, UnitInterface::isLifted
   */
  Optional<Boolean> isFlying();

  /**
   * Checks if this unit is following another unit. When a unit is following another unit, it simply
   * moves where the other unit does, and does not attack enemies when it is following. Returns true
   * if this unit is following another unit, and false if it is not Note If this function returns a
   * successful state, then the following function calls will also return a successful state:
   * isCompleted See also follow, getTarget
   */
  Optional<Boolean> isFollowing();

  /**
   * Checks if this unit is currently gathering gas. That is, the unit is either moving to a
   * refinery, waiting to enter a refinery, harvesting from the refinery, or returning gas to a
   * resource depot. Returns true if this unit is harvesting gas, and false if it is not Note If
   * this function returns a successful state, then the following function calls will also return a
   * successful state: isCompleted, getType().isWorker() See also isCarryingGas
   */
  Optional<Boolean> isGatheringGas();

  /**
   * Checks if this unit is currently harvesting minerals. That is, the unit is either moving to a
   * Mineral Field, waiting to mine, mining minerals, or returning minerals to a resource depot.
   * Returns true if this unit is gathering minerals, and false if it is not Note If this function
   * returns a successful state, then the following function calls will also return a successful
   * state: isCompleted, getType().isWorker() See also isCarryingMinerals
   */
  Optional<Boolean> isGatheringMinerals();

  /**
   * Checks if this unit is a hallucination. Hallucinations are created by the High Templar using
   * the Hallucination ability. Enemy hallucinations are unknown if Flag::CompleteMapInformation is
   * disabled. Hallucinations have a time limit until they are destroyed (see
   * UnitInterface::getRemoveTimer). Returns true if the unit is a hallucination and false
   * otherwise. See also getRemoveTimer
   */
  Optional<Boolean> isHallucination();

  /**
   * Checks if the unit is currently holding position. A unit that is holding position will attack
   * other units, but will not chase after them. Returns true if this unit is holding position, and
   * false if it is not. See also holdPosition
   */
  Optional<Boolean> isHoldingPosition();

  /**
   * Checks if this unit is running an idle order. This function is particularly useful when
   * checking for units that aren't doing any tasks that you assigned. A unit is considered idle if
   * it is not doing any of the following: Training Constructing Morphing Researching Upgrading In
   * addition to running one of the following orders: Orders::PlayerGuard: IPlayer unit idle.
   * Orders::Guard: Generic unit idle. Orders::Stop Orders::PickupIdle Orders::Nothing:
   * Structure/generic idle. Orders::Medic: Medic idle. Orders::Carrier: Carrier idle.
   * Orders::Reaver: Reaver idle. Orders::Critter: Critter idle. Orders::Neutral: Neutral unit idle.
   * Orders::TowerGuard: Turret structure idle. Orders::Burrowed: Burrowed unit idle.
   * Orders::NukeTrain Orders::Larva: Larva idle. BWAPI::Unitset myUnits =
   * BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u )
   * { // OrderEnum idle worker to gather from closest mineral field if ( u->getType().isWorker() &&
   * u->isIdle() ) u->gather( u->getClosestUnit( BWAPI::Filter::IsMineralField ) ); } Returns true
   * if this unit is idle, and false if this unit is performing any action, such as moving or
   * attacking Note If this function returns a successful state, then the following function calls
   * will also return a successful state: isCompleted See also UnitInterface::stop
   */
  Optional<Boolean> isIdle();

  /**
   * Checks if the unit can be interrupted. Returns true if this unit can be interrupted, or false
   * if this unit is uninterruptable
   */
  Optional<Boolean> isInterruptible();

  /**
   * Checks the invincibility state for this unit. Returns true if this unit is currently
   * invulnerable, and false if it is vulnerable
   */
  Optional<Boolean> isInvincible();

  /**
   * Checks if this unit is irradiated by a Science Vessel 's Irradiate ability. Returns true if
   * this unit is irradiated, and false otherwise Example usage: BWAPI::Unitset myUnits =
   * BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u )
   * { if ( u->isIrradiated() && u->getIrradiateTimer > 50 && BWAPI::Broodwar->self()->hasResearched(BWAPI::TechTypes::Restoration)
   * ) { BWAPI::IUnit medic = u->getClosestUnit( BWAPI::Filter::GetType ==
   * BWAPI::UnitTypes::Terran_Medic && BWAPI::Filter::Energy >= BWAPI::TechTypes::Restoration.energyCost()
   * ); if ( medic ) medic->useTech(BWAPI::TechTypes::Restoration, *u); } } See also
   * getIrradiateTimer
   */
  Optional<Boolean> isIrradiated();

  /**
   * Checks if this unit is a Terran building and lifted off the ground. This function generally
   * implies this->getType().isBuilding() and this->isCompleted() both return true. Returns true if
   * this unit is a Terran structure lifted off the ground. Note If this function returns a
   * successful state, then the following function calls will also return a successful state:
   * isCompleted, getType().isFlyingBuilding() See also isFlying
   */
  Optional<Boolean> isLifted();

  /**
   * Checks if this unit is currently loaded into another unit such as a Transport(Dropship,
   * Shuttle, Overlord ). Returns true if this unit is loaded in another one, and false otherwise
   * Note If this function returns a successful state, then the following function calls will also
   * return a successful state: isCompleted See also load, unload, unloadAll
   */
  Optional<Boolean> isLoaded();

  /**
   * Checks if this unit is currently locked by a Ghost. Returns true if this unit is locked down,
   * and false otherwise See also getLockdownTimer
   */
  Optional<Boolean> isLockedDown();

  /**
   * Checks if this unit has been maelstrommed by a Dark Archon. Returns true if this unit is
   * maelstrommed, and false otherwise See also getMaelstromTimer
   */
  Optional<Boolean> isMaelstrommed();

  /**
   * Finds out if the current unit is morphing or not. Zerg units and structures often have the
   * ability to morph into different types of units. This function allows you to identify when this
   * process is occurring. Return values true if the unit is currently morphing. false if the unit
   * is not morphing See also morph, cancelMorph, getBuildType, getRemainingBuildTime
   */
  Optional<Boolean> isMorphing();

  /**
   * Checks if this unit is currently moving. Returns true if this unit is moving, and false if it
   * is not See also stop
   */
  Optional<Boolean> isMoving();

  /**
   * Checks if this unit has been parasited by some other player. Returns true if this unit is
   * inflicted with Parasite, and false if it is clean
   */
  Optional<Boolean> isParasited();

  /**
   * Checks if this unit is patrolling between two positions. Returns true if this unit is
   * patrolling and false if it is not See also patrol
   */
  Optional<Boolean> isPatrolling();

  /**
   * Checks if this unit has been been plagued by a Defiler. Returns true if this unit is inflicted
   * with Plague and is taking damage, and false if it is clean See also getPlagueTimer
   */
  Optional<Boolean> isPlagued();

  /**
   * Checks if this unit is repairing or moving to repair another unit. This is only applicable to
   * SCVs. Returns true if this unit is currently repairing or moving to repair another unit, and
   * false if it is not
   */
  Optional<Boolean> isRepairing();

  /**
   * Checks if this unit is a structure that is currently researching a technology. See TechTypes
   * for a complete Set of technologies in Broodwar. Returns true if this structure is researching a
   * technology, false otherwise See also research, cancelResearch, getTech,
   * getRemainingResearchTime, Note If this function returns a successful state, then the following
   * function calls will also return a successful state: exists(), isCompleted(),
   * getType().isBuilding()
   */
  Optional<Boolean> isResearching();

  /**
   * Checks if this unit has been selected in the user interface. This function is only available if
   * the flag Flag::UserInput is enabled. Returns true if this unit is currently selected, and false
   * if this unit is not selected See also IGame::getSelectedUnits
   */
  Optional<Boolean> isSelected();

  /**
   * Checks if this unit is currently sieged. This is only applicable to Siege Tanks. Returns true
   * if the unit is in siege mode, and false if it is either not in siege mode or not a Siege Tank
   * See also siege, unsiege
   */
  Optional<Boolean> isSieged();

  /**
   * Checks if the unit is starting to attack. Returns true if this unit is starting an attack. See
   * also attack, getGroundWeaponCooldown, getAirWeaponCooldown
   */
  Optional<Boolean> isStartingAttack();

  /**
   * Checks if this unit is inflicted with Stasis Field by an Arbiter. Returns true if this unit is
   * locked in a Stasis Field and is unable to move, and false if it is free. Note This function
   * does not necessarily imply that the unit is invincible, since there is a feature in the Use Map
   * Settings game type that allows stasised units to be vulnerable. See also getStasisTimer
   */
  Optional<Boolean> isStasised();

  /**
   * Checks if this unit is currently under the influence of a Stim Packs. Returns true if this unit
   * has used a stim pack, false otherwise See also getStimTimer
   */
  Optional<Boolean> isStimmed();

  /**
   * Checks if this unit is currently trying to resolve a collision by randomly moving around.
   * Returns true if this unit is currently stuck and trying to resolve a collision, and false if
   * this unit is free
   */
  Optional<Boolean> isStuck();

  /**
   * Checks if this unit is training a new unit. For example, a Barracks training a Marine. Note It
   * is possible for a unit to remain in the training queue with no progress. In that case, this
   * function will return false because of supply or unit count limitations. Returns true if this
   * unit is currently training another unit, and false otherwise. See also train, getTrainingQueue,
   * cancelTrain, getRemainingTrainTime
   */
  Optional<Boolean> isTraining();

  /**
   * Checks if the current unit is being attacked. Has a small delay before this returns false again
   * when the unit is no longer being attacked. Returns true if this unit has been attacked within
   * the past few frames, and false if it has not
   */
  Optional<Boolean> isUnderAttack();

  /**
   * Checks if this unit is under the cover of a Dark Swarm. Returns true if this unit is protected
   * by a Dark Swarm, and false if it is not
   */
  Optional<Boolean> isUnderDarkSwarm();

  /**
   * Checks if this unit is currently being affected by a Disruption Web. Returns true if this unit
   * is under the effects of Disruption Web.
   */
  Optional<Boolean> isUnderDisruptionWeb();

  /**
   * Checks if this unit is currently taking damage from a Psionic Storm. Returns true if this unit
   * is losing hit points from a Psionic Storm, and false otherwise.
   */
  Optional<Boolean> isUnderStorm();

  /**
   * Checks if this unit has power. Most structures are powered by default, but Protoss structures
   * require a Pylon to be powered and functional. Returns true if this unit has power or is
   * inaccessible, and false if this unit is unpowered. Since 4.0.1 Beta (previously isUnpowered)
   */
  Optional<Boolean> isPowered();

  /**
   * Checks if this unit is a structure that is currently upgrading an upgrade. See UpgradeTypes for
   * a full Set of upgrades in Broodwar. Returns true if this structure is upgrading, false
   * otherwise See also upgrade, cancelUpgrade, getUpgrade, getRemainingUpgradeTime Note If this
   * function returns a successful state, then the following function calls will also return a
   * successful state: exists(), isCompleted(), getType().isBuilding()
   */
  Optional<Boolean> isUpgrading();

  default Optional<Boolean> isVisible(IPlayer player) {
    return player.getAllUnits().map(iUnits -> iUnits.anyMatch(iUnit -> iUnit.equals(this)));
  }

  /**
   * Performs some cheap checks to attempt to quickly detect whether the unit is unable to be
   * targetted as the target unit of an unspecified command. Return values true if BWAPI was unable
   * to determine whether the unit can be a target. false if an error occurred and the unit can not
   * be a target. See also IGame::getLastError, UnitInterface::canTargetUnit
   */
  Optional<Boolean> isTargetable();

  /**
   * Performs some cheap checks to attempt to quickly detect whether the unit is unable to execute
   * any commands (eg the unit is stasised). Return values true if BWAPI was unable to determine
   * whether the unit can be commanded. false if an error occurred and the unit can not be
   * commanded. See also IGame::getLastError, UnitInterface::canIssueCommand
   */
  Optional<Boolean> canCommand();

  /**
   * Performs some cheap checks to attempt to quickly detect whether the unit is unable to execute
   * any commands as part of a Unitset (eg buildings, critters). Return values true if BWAPI was
   * unable to determine whether the unit can be commanded grouped. false if an error occurred and
   * the unit can not be commanded grouped. See also IGame::getLastError,
   * UnitInterface::canIssueCommandGrouped, UnitInterface::canIssueCommand
   */
  Optional<Boolean> canCommandGrouped();

  /**
   * Cheap checks for whether the unit is able to execute an attack command to attack-move or attack
   * a unit. See also IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack,
   * UnitInterface::canAttackMove, UnitInterface::canAttackUnit
   */
  Optional<Boolean> canAttack();

  /**
   * Checks whether the unit is able to execute an attack command to attack-move. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack
   */
  Optional<Boolean> canAttackMove();

  /**
   * Checks whether the unit is able to execute an attack command to attack-move, as part of a
   * Unitset. See also IGame::getLastError, UnitInterface::canIssueCommandGrouped,
   * UnitInterface::canAttackMove
   */
  Optional<Boolean> canAttackMoveGrouped();

  /**
   * Cheap checks for whether the unit is able to execute an attack command to attack a unit. See
   * also IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack
   */
  Optional<Boolean> canAttackUnit();

  /**
   * Cheap checks for whether the unit is able to execute a build command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::build
   */
  Optional<Boolean> canBuild();

  /**
   * Cheap checks for whether the unit is able to execute a buildAddon command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::buildAddon
   */
  Optional<Boolean> canBuildAddon();

  /**
   * Cheap checks for whether the unit is able to execute a train command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::train
   */
  Optional<Boolean> canTrain();

  /**
   * Cheap checks for whether the unit is able to execute a morph command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::morph
   */
  Optional<Boolean> canMorph();

  /**
   * Cheap checks for whether the unit is able to execute a research command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::research
   */
  Optional<Boolean> canResearch();

  /**
   * Cheap checks for whether the unit is able to execute an upgrade command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::upgrade
   */
  Optional<Boolean> canUpgrade();

  /**
   * Cheap checks for whether the unit is able to execute a setRallyPoint command to a position or
   * unit. See also IGame::getLastError, UnitInterface::canIssueCommand,
   * UnitInterface::setRallyPoint, UnitInterface::canSetRallyPosition,
   * UnitInterface::canSetRallyUnit.
   */
  Optional<Boolean> canSetRallyPoint();

  /**
   * Checks whether the unit is able to execute a setRallyPoint command to a position. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::setRallyPoint
   */
  Optional<Boolean> canSetRallyPosition();

  /**
   * Cheap checks for whether the unit is able to execute a setRallyPoint command to a unit. See
   * also IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::setRallyPoint
   */
  Optional<Boolean> canSetRallyUnit();

  /**
   * Checks whether the unit is able to execute a move command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::move
   */
  Optional<Boolean> canMove();

  /**
   * Checks whether the unit is able to execute a patrol command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::patrol
   */
  Optional<Boolean> canPatrol();

  /**
   * Cheap checks for whether the unit is able to execute a follow command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::follow
   */
  Optional<Boolean> canFollow();

  /**
   * Cheap checks for whether the unit is able to execute a gather command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::gather
   */
  Optional<Boolean> canGather();

  /**
   * Checks whether the unit is able to execute a returnCargo command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::returnCargo
   */
  Optional<Boolean> canReturnCargo();

  /**
   * Checks whether the unit is able to execute a holdPosition command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::holdPosition
   */
  Optional<Boolean> canHoldPosition();

  /**
   * Checks whether the unit is able to execute a stop command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::stop
   */
  Optional<Boolean> canStop();

  /**
   * Cheap checks for whether the unit is able to execute a repair command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::repair
   */
  Optional<Boolean> canRepair();

  /**
   * Checks whether the unit is able to execute a burrow command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::burrow
   */
  Optional<Boolean> canBurrow();

  /**
   * Checks whether the unit is able to execute an unburrow command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::unburrow
   */
  Optional<Boolean> canUnburrow();

  /**
   * Checks whether the unit is able to execute a cloak command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::cloak
   */
  Optional<Boolean> canCloak();

  /**
   * Checks whether the unit is able to execute a decloak command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::decloak
   */
  Optional<Boolean> canDecloak();

  /**
   * Checks whether the unit is able to execute a siege command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::siege
   */
  Optional<Boolean> canSiege();

  /**
   * Checks whether the unit is able to execute an unsiege command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::unsiege
   */
  Optional<Boolean> canUnsiege();

  /**
   * Checks whether the unit is able to execute a lift command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::lift
   */
  Optional<Boolean> canLift();

  /**
   * Cheap checks for whether the unit is able to execute a land command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::land
   */
  Optional<Boolean> canLand();

  /**
   * Cheap checks for whether the unit is able to execute a load command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::load
   */
  Optional<Boolean> canLoad();

  /**
   * Cheap checks for whether the unit is able to execute an unload command or unloadAll at current
   * position command or unloadAll at a different position command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::unload, UnitInterface::unloadAll
   */
  Optional<Boolean> canUnloadWithOrWithoutTarget();

  /**
   * Cheap checks for whether the unit is able to execute an unload command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::unload
   */
  Optional<Boolean> canUnload();

  /**
   * Checks whether the unit is able to execute an unloadAll command for the current position. See
   * also IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::unloadAll
   */
  Optional<Boolean> canUnloadAll();

  /**
   * Cheap checks for whether the unit is able to execute an unloadAll command for a different
   * position. See also IGame::getLastError, UnitInterface::canIssueCommand,
   * UnitInterface::unloadAll
   */
  Optional<Boolean> canUnloadAllPosition();

  /**
   * Checks whether the unit is able to execute a haltConstruction command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::haltConstruction
   */
  Optional<Boolean> canHaltConstruction();

  /**
   * Checks whether the unit is able to execute a cancelConstruction command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelConstruction
   */
  Optional<Boolean> canCancelConstruction();

  /**
   * Checks whether the unit is able to execute a cancelAddon command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::cancelAddon
   */
  Optional<Boolean> canCancelAddon();

  /**
   * Checks whether the unit is able to execute a cancelTrain command for any slot. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelTrain
   */
  Optional<Boolean> canCancelTrain();

  /**
   * Cheap checks for whether the unit is able to execute a cancelTrain command for an unspecified
   * slot. See also IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelTrain
   */
  Optional<Boolean> canCancelTrainSlot();

  /**
   * Checks whether the unit is able to execute a cancelMorph command. See also IGame::getLastError,
   * UnitInterface::canIssueCommand, UnitInterface::cancelMorph
   */
  Optional<Boolean> canCancelMorph();

  /**
   * Checks whether the unit is able to execute a cancelResearch command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelResearch
   */
  Optional<Boolean> canCancelResearch();

  /**
   * Checks whether the unit is able to execute a cancelUpgrade command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelUpgrade
   */
  Optional<Boolean> canCancelUpgrade();

  /**
   * Cheap checks for whether the unit is able to execute a useTech command without a target or or a
   * useTech command with a target position or a useTech command with a target unit. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
   */
  Optional<Boolean> canUseTechWithOrWithoutTarget();

  /**
   * Cheap checks for whether the unit is able to execute a placeCOP command. See also
   * IGame::getLastError, UnitInterface::canIssueCommand, UnitInterface::placeCOP
   */
  Optional<Boolean> canPlaceCOP();

  default Optional<Boolean> isDoingSomething() {
    return Optional.of((isGatheringGas().isPresent() && isGatheringGas().get()) ||
        (isGatheringMinerals().isPresent() && isGatheringMinerals().get()) ||
        (isConstructing().isPresent() && isConstructing().get()) ||
        (isMoving().isPresent() && isMoving().get()) ||
        (isAccelerating().isPresent() && isAccelerating().get()) ||
        (isMorphing().isPresent() && isMorphing().get()) ||
        (isAttacking().isPresent() && isAttacking().get()));
  }

  default Optional<Boolean> isFullyHealthy() {
    if (!getHitPoints().isPresent() || getType()
        .map(unitType -> !unitType.maxHitPoints().isPresent()).orElse(false)) {
      return Optional.empty();
    }

    return Optional.of(getHitPoints().get() >= getType().get().maxHitPoints().get());
  }

  default Optional<Double> getHPPercent() {
    if (!getHitPoints().isPresent() || getType()
        .map(unitType -> !unitType.maxHitPoints().isPresent()).orElse(false)) {
      return Optional.empty();
    }
    return Optional
        .of(((double) getHitPoints().get()) / ((double) getType().get().maxHitPoints().get()));
  }

  default Optional<Boolean> isWounded() {
    return isFullyHealthy().map(aBoolean -> !aBoolean);
  }

  /**
   * Returns true if this unit is capable of attacking <b>otherUnitType</b>. For example Zerglings
   * can't attack flying targets and Corsairs can't attack ground targets.
   */
  default Optional<Boolean> canAttackThisKindOfUnit(IUnitType otherUnitType) {
    if (!getType().isPresent()) {
      return Optional.empty();
    }
    // Enemy is AIR unit
    if (otherUnitType.isFlyer().isPresent() && otherUnitType.isFlyer().get()) {
      return getType().flatMap(IUnitType::canAttackAirUnits);
    } else // Enemy is GROUND unit
    {
      return getType().flatMap(IUnitType::canAttackGroundUnits);
    }
  }

  /**
   * Returns weapon that would be used to attack given target. If no such weapon, then
   * WeaponTypes.None will be returned.
   */
  default Optional<IWeaponType> getWeaponAgainst(IUnitType target) {
    if (!target.isFlyer().isPresent()) {
      return Optional.empty();
    } else {
      if (target.isFlyer().get()) {
        return getType().flatMap(IUnitType::airWeapon);
      }
      return getType().flatMap(IUnitType::groundWeapon);
    }
  }

  /**
   * Returns true if unit is starting an attack or already in the attack frame animation.
   */
  default Optional<Boolean> isJustShooting() {
    if (isAttackFrame().isPresent() && isAttackFrame().get()) {
      return Optional.of(true);
    }
    return Optional.of(isStartingAttack().isPresent() && isStartingAttack().get());
  }

  Optional<Stream<IUnit>> getEnemyUnitsInWeaponRange();

  Optional<Stream<IUnit>> getFriendlyUnitsInRadiusOfSight();

  Optional<Stream<IUnit>> getResourceUnitsInRadiusOfSight();

  Optional<Stream<IUnit>> getEnemyUnitsInRadiusOfSight();

}