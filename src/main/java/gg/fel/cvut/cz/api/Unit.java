package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.api.enums.Order;
import gg.fel.cvut.cz.api.enums.UnitCommandType;

import java.util.List;

/**
 * The Unit class is used to get information about individual units as well as issue orders to units. Each unit in the game has a unique Unit object, and Unit objects are not deleted until the end of the match (so you don't need to worry about unit pointers becoming invalid). Every Unit in the game is either accessible or inaccessible. To determine if an AI can access a particular unit, BWAPI checks to see if Flag::CompleteMapInformation is enabled. So there are two cases to consider - either the flag is enabled, or it is disabled: If Flag::CompleteMapInformation is disabled, then a unit is accessible if and only if it is visible. Note Some properties of visible enemy units will not be made available to the AI (such as the contents of visible enemy dropships). If a unit is not visible, UnitInterface::exists will return false, regardless of whether or not the unit exists. This is because absolutely no state information on invisible enemy units is made available to the AI. To determine if an enemy unit has been destroyed, the AI must watch for AIModule::onUnitDestroy messages from BWAPI, which is only called for visible units which get destroyed. If Flag::CompleteMapInformation is enabled, then all units that exist in the game are accessible, and UnitInterface::exists is accurate for all units. Similarly AIModule::onUnitDestroy messages are generated for all units that get destroyed, not just visible ones. If a Unit is not accessible, then only the getInitial__ functions will be available to the AI. However for units that were owned by the player, getPlayer and getType will continue to work for units that have been destroyed.
 */
//TODO - remove command methods
public interface Unit {

    /**
     * Retrieves a unique identifier for this unit. Returns An integer containing the unit's identifier. See also getReplayID
     */
    int getID();

    /**
     * Checks if the Unit exists in the view of the BWAPI player. This is used primarily to check if BWAPI has access to a specific unit, or if the unit is alive. This function is more general and would be synonymous to an isAlive function if such a function were necessary. Return values true If the unit exists on the map and is visible according to BWAPI. false If the unit is not accessible or the unit is dead. In the event that this function returns false, there are two cases to consider: You own the unit. This means the unit is dead. Another player owns the unit. This could either mean that you don't have access to the unit or that the unit has died. You can specifically identify dead units by polling onUnitDestroy. See also isVisible, isCompleted
     */
    boolean exists();

    /**
     * Retrieves the unit identifier for this unit as seen in replay data. Note This is only available if Flag::CompleteMapInformation is enabled. Returns An integer containing the replay unit identifier. See also getID
     */
    int getReplayID();

    /**
     * Retrieves the player that owns this unit. Return values Game::neutral() If the unit is a neutral unit or inaccessible. Returns The owning Player interface object.
     */
    Player getPlayer();

    /**
     * Retrieves the unit's type. Return values UnitTypes::Unknown if this unit is inaccessible or cannot be determined. Returns A UnitType objects representing the unit's type. See also getInitialType
     */
    UnitType getType();

    /**
     * Retrieves the unit's position from the upper left corner of the map in pixels. The position returned is roughly the center if the unit. Note The unit bounds are defined as this value plus/minus the values of UnitType::dimensionLeft, UnitType::dimensionUp, UnitType::dimensionRight, and UnitType::dimensionDown, which is conveniently expressed in UnitInterface::getLeft, UnitInterface::getTop, UnitInterface::getRight, and UnitInterface::getBottom respectively. Return values Positions::Unknown if this unit is inaccessible. Returns Position object representing the unit's current position. See also getTilePosition, getInitialPosition, getLeft, getTop
     */
    Position getPosition();

    /**
     * Retrieves the unit's build position from the upper left corner of the map in tiles. Note : This tile position is the tile that is at the top left corner of the structure. Return values TilePositions::Unknown if this unit is inaccessible. Returns TilePosition object representing the unit's current tile position. See also getPosition, getInitialTilePosition
     */
    TilePosition getTilePosition();

    /**
     * Retrieves the unit's facing direction in radians. Note A value of 0.0 means the unit is facing east. Returns A double with the angle measure in radians.
     */
    double getAngle();

    /**
     * Retrieves the x component of the unit's velocity, measured in pixels per frame. Returns A double that represents the velocity's x component. See also getVelocityY
     */
    double getVelocityX();

    /**
     * Retrieves the y component of the unit's velocity, measured in pixels per frame. Returns A double that represents the velocity's y component. See also getVelocityX
     */
    double getVelocityY();

    /**
     * Retrieves the Region that the center of the unit is in. Return values nullptr If the unit is inaccessible. Returns The Region object that contains this unit. Example Unitset myUnits = Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u ) { if ( u->isFlying() && u->isUnderAttack() ) // implies exists and isCompleted { Region r = u->getRegion(); if ( r ) u->move(r->getClosestInaccessibleRegion()); // Retreat to inaccessible region } } Note If this function returns a successful state, then the following function calls will also return a successful state: exists
     */
    Region getRegion();

    /**
     * Retrieves the X coordinate of the unit's left boundary, measured in pixels from the left side of the map. Returns An integer representing the position of the left side of the unit. See also getTop, getRight, getBottom
     */
    int getLeft();

    /**
     * Retrieves the Y coordinate of the unit's top boundary, measured in pixels from the top of the map. Returns An integer representing the position of the top side of the unit. See also getLeft, getRight, getBottom
     */
    int getTop();

    /**
     * Retrieves the X coordinate of the unit's right boundary, measured in pixels from the left side of the map. Returns An integer representing the position of the right side of the unit. See also getLeft, getTop, getBottom
     */
    int getRight();

    /**
     * Retrieves the Y coordinate of the unit's bottom boundary, measured in pixels from the top of the map. Returns An integer representing the position of the bottom side of the unit. See also getLeft, getTop, getRight
     */
    int getBottom();

    /**
     * Retrieves the unit's current Hit Points (HP) as seen in the game. Returns An integer representing the amount of hit points a unit currently has. Note In Starcraft, a unit usually dies when its HP reaches 0. It is possible however, to have abnormal HP values in the Use Map Settings game type and as the result of a hack over Battle.net. Such values include units that have 0 HP (can't be killed conventionally) or even negative HP (death in one hit). See also UnitType::maxHitPoints, getShields, getInitialHitPoints
     */
    int getHitPoints();

    /**
     * Retrieves the unit's current Shield Points (Shields) as seen in the game. Returns An integer representing the amount of shield points a unit currently has. See also UnitType::maxShields, getHitPoints
     */
    int getShields();

    /**
     * Retrieves the unit's current Energy Points (Energy) as seen in the game. Returns An integer representing the amount of energy points a unit currently has. Note Energy is required in order for units to use abilities. See also UnitType::maxEnergy
     */
    int getEnergy();

    /**
     * Retrieves the resource amount from a resource container, such as a Mineral Field and Vespene Geyser. If the unit is inaccessible, then the last known resource amount is returned. Returns An integer representing the last known amount of resources remaining in this resource. See also getInitialResources
     */
    int getResources();

    /**
     * Retrieves a grouping index from a resource container. Other resource containers of the same value are considered part of one expansion location (group of resources that are close together). Note This grouping method is explicitly determined by Starcraft itself and is used only by the internal AI. Returns An integer with an identifier between 0 and 250 that determine which resources are grouped together to form an expansion.
     */
    int getResourceGroup();

    /**
     * Retrieves the distance between this unit and a target. Note Distance is calculated from the edge of this unit, using Starcraft's own distance algorithm. Parameters target A Position or a Unit to calculate the distance to. If it is a unit, then it will calculate the distance to the edge of the target unit. Returns An integer representation of the number of pixels between this unit and the target.
     */
    int getDistance(Position target);

    int getDistance(Unit target);

    /**
     * Using data provided by Starcraft, checks if there is a path available from this unit to the given target. Note This function only takes into account the terrain data, and does not include buildings when determining if a path is available. However, the complexity of this function is constant ( O(1) ), and no extensive calculations are necessary. If the current unit is an air unit, then this function will always return true. Parameters target A Position or a Unit that is used to determine if this unit has a path to the target. Return values true If there is a path between this unit and the target. false If the target is on a different piece of land than this one (such as an island).
     */
    boolean hasPath(Position target);

    boolean hasPath(Unit target);

    /**
     * Retrieves the frame number that sent the last successful command. Note This value is comparable to Game::getFrameCount. Returns The frame number that sent the last successfully processed command to BWAPI. See also Game::getFrameCount, getLastCommand
     */
    int getLastCommandFrame();

    /**
     * Retrieves the last successful command that was sent to BWAPI. Returns A UnitCommand object containing information about the command that was processed. See also getLastCommandFrame
     */
    UnitCommand getLastCommand();

    /**
     * Retrieves the Player that last attacked this unit. Returns Player interface object representing the player that last attacked this unit. Return values nullptr If this unit was not attacked. Note If this function returns a successful state, then the following function calls will also return a successful state: exists()
     */
    Player getLastAttackingPlayer();

    /**
     * Retrieves the initial type of the unit. This is the type that the unit starts as in the beginning of the game. This is used to access the types of static neutral units such as mineral fields when they are not visible. Returns UnitType of this unit as it was when it was created. Return values UnitTypes::Unknown if this unit was not a static neutral unit in the beginning of the game.
     */
    UnitType getInitialType();

    /**
     * Retrieves the initial position of this unit. This is the position that the unit starts at in the beginning of the game. This is used to access the positions of static neutral units such as mineral fields when they are not visible. Returns Position indicating the unit's initial position when it was created. Return values Positions::Unknown if this unit was not a static neutral unit in the beginning of the game.
     */
    Position getInitialPosition();

    /**
     * Retrieves the initial build tile position of this unit. This is the tile position that the unit starts at in the beginning of the game. This is used to access the tile positions of static neutral units such as mineral fields when they are not visible. The build tile position corresponds to the upper left corner of the unit. Returns TilePosition indicating the unit's initial tile position when it was created. Return values TilePositions::Unknown if this unit was not a static neutral unit in the beginning of the game.
     */
    TilePosition getInitialTilePosition();

    /**
     * Retrieves the amount of hit points that this unit started off with at the beginning of the game. The unit must be neutral. Returns Number of hit points that this unit started with. Return values 0 if this unit was not a neutral unit at the beginning of the game. Note : It is possible for the unit's initial hit points to differ from the maximum hit points. See also Game::getStaticNeutralUnits
     */
    int getInitialHitPoints();

    /**
     * Retrieves the amount of resources contained in the unit at the beginning of the game. The unit must be a neutral resource container. Returns Amount of resources that this unit started with. Return values 0 if this unit was not a neutral unit at the beginning of the game, or if this unit does not contain resources. It is possible that the unit simply contains 0 resources. See also Game::getStaticNeutralUnits
     */
    int getInitialResources();

    /**
     * Retrieves the number of units that this unit has killed in total. Note The maximum amount of recorded kills per unit is 255. Returns integer indicating this unit's kill count.
     */
    int getKillCount();

    /**
     * Retrieves the number of acid spores that this unit is inflicted with. Returns Number of acid spores on this unit.
     */
    int getAcidSporeCount();

    /**
     * Retrieves the number of interceptors that this unit manages. This function is only for the Carrier. Returns Number of interceptors in this unit.
     */
    int getInterceptorCount();

    /**
     * Retrieves the number of scarabs that this unit has for use. This function is only for the Reaver. Returns Number of scarabs this unit has ready.
     */
    int getScarabCount();

    /**
     * Retrieves the amount of Spider Mines this unit has available. This function is only for the Vulture. Returns Number of spider mines available for placement.
     */
    int getSpiderMineCount();

    /**
     * Retrieves the unit's ground weapon cooldown. This value decreases every frame, until it reaches 0. When the value is 0, this indicates that the unit is capable of using its ground weapon, otherwise it must wait until it reaches 0. Note This value will vary, because Starcraft adds an additional random value between (-1) and (+2) to the unit's weapon cooldown. Returns Number of frames needed for the unit's ground weapon to become available again.
     */
    int getGroundWeaponCooldown();

    /**
     * Retrieves the unit's air weapon cooldown. This value decreases every frame, until it reaches 0. When the value is 0, this indicates that the unit is capable of using its air weapon, otherwise it must wait until it reaches 0. Note This value will vary, because Starcraft adds an additional random value between (-1) and (+2) to the unit's weapon cooldown. Returns Number of frames needed for the unit's air weapon to become available again.
     */
    int getAirWeaponCooldown();

    /**
     * Retrieves the unit's ability cooldown. This value decreases every frame, until it reaches 0. When the value is 0, this indicates that the unit is capable of using one of its special abilities, otherwise it must wait until it reaches 0. Note This value will vary, because Starcraft adds an additional random value between (-1) and (+2) to the unit's ability cooldown. Returns Number of frames needed for the unit's abilities to become available again.
     */
    int getSpellCooldown();

    /**
     * Retrieves the amount of hit points remaining on the Defensive Matrix created by a Science Vessel. The Defensive Matrix ability starts with 250 hit points when it is used. Returns Number of hit points remaining on this unit's Defensive Matrix. See also getDefenseMatrixTimer, isDefenseMatrixed
     */
    int getDefenseMatrixPoints();

    /**
     * Retrieves the time, in frames, that the Defensive Matrix will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also getDefenseMatrixPoints, isDefenseMatrixed
     */
    int getDefenseMatrixTimer();

    /**
     * Retrieves the time, in frames, that Ensnare will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also isEnsnared
     */
    int getEnsnareTimer();

    /**
     * Retrieves the time, in frames, that Irradiate will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also isIrradiated
     */
    int getIrradiateTimer();

    /**
     * Retrieves the time, in frames, that Lockdown will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also isLockdowned
     */
    int getLockdownTimer();

    /**
     * Retrieves the time, in frames, that Maelstrom will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also isMaelstrommed
     */
    int getMaelstromTimer();

    /**
     * Retrieves an internal timer used for the primary order. Its use is specific to the order type that is currently assigned to the unit. Returns A value used as a timer for the primary order. See also getOrder
     */
    int getOrderTimer();

    /**
     * Retrieves the time, in frames, that Plague will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also isPlagued
     */
    int getPlagueTimer();

    /**
     * Retrieves the time, in frames, until this temporary unit is destroyed or removed. This is used to determine the remaining time for the following units that were created by abilities: Hallucination broodling Dark Swarm Disruption Web Scanner Sweep Once this value reaches 0, the unit is destroyed.
     */
    int getRemoveTimer();

    /**
     * Retrieves the time, in frames, that Stasis Field will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also isPlagued
     */
    int getStasisTimer();

    /**
     * Retrieves the time, in frames, that Stim Packs will remain active on the current unit. Returns Number of frames remaining until the effect is removed. See also isPlagued
     */
    int getStimTimer();

    /**
     * Retrieves the building type that a worker (SCV, Probe, Drone) is about to construct. If the unit is morphing or is an incomplete structure, then this returns the UnitType that it will become when it has completed morphing/constructing. Returns UnitType indicating the type that a worker (SCV, Probe, Drone) is about to construct, or an incomplete unit will be when completed.
     */
    UnitType getBuildType();

    /**
     * Retrieves the list of units queued up to be trained. Returns a UnitType::set containing all the types that are in this factory's training queue. See also train, cancelTrain, isTraining
     */
    List<UnitType> getTrainingQueue();

    /**
     * Retrieves the technology that this unit is currently researching. Returns TechType indicating the technology being researched by this unit. Return values TechTypes::None if this unit is not researching anything. See also research, cancelResearch, isResearching, getRemainingResearchTime
     */
    TechType getTech();

    /**
     * Retrieves the upgrade that this unit is currently upgrading. Returns UpgradeType indicating the upgrade in progress by this unit. Return values UpgradeTypes::None if this unit is not upgrading anything. See also upgrade, cancelUpgrade, isUpgrading, getRemainingUpgradeTime
     */
    UpgradeType getUpgrade();

    /**
     * Retrieves the remaining build time for a unit or structure that is being trained or constructed. Returns Number of frames remaining until the unit's completion.
     */
    int getRemainingBuildTime();

    /**
     * Retrieves the remaining time, in frames, of the unit that is currently being trained. Note If the unit is a Hatchery, Lair, or Hive, this retrieves the amount of time until the next larva spawns. Returns Number of frames remaining until the current training unit becomes completed, or the number of frames remaining until the next larva spawns. Return values 0 If the unit is not training or has three larvae. See also train, getTrainingQueue
     */
    int getRemainingTrainTime();

    /**
     * Retrieves the amount of time until the unit is done researching its currently assigned TechType. Returns The remaining research time, in frames, for the current technology being researched by this unit. Return values 0 If the unit is not researching anything. See also research, cancelResearch, isResearching, getTech
     */
    int getRemainingResearchTime();

    /**
     * Retrieves the amount of time until the unit is done upgrading its current upgrade. Returns The remaining upgrade time, in frames, for the current upgrade. Return values 0 If the unit is not upgrading anything. See also upgrade, cancelUpgrade, isUpgrading, getUpgrade
     */
    int getRemainingUpgradeTime();

    /**
     * Retrieves the corresponding paired unit for SCVs and Terran structures. For example, if this unit is a Factory under construction, this function will return the SCV that is constructing it. If this unit is a SCV, then it will return the structure it is currently constructing. Returns Paired build unit that is either constructing this unit, or being constructed by this unit. Return values nullptr If there is no unit constructing this one, or this unit is not constructing another unit.
     */
    Unit getBuildUnit();

    /**
     * Generally returns the appropriate target unit after issuing an order that accepts a target unit (i.e. attack, repair, gather, etc.). To get a target that has been acquired automatically without issuing an order, use getOrderTarget. Returns Unit that is currently being targeted by this unit. See also getOrderTarget
     */
    Unit getTarget();

    /**
     * Retrieves the target position the unit is moving to, provided a valid path to the target position exists. Returns Target position of a movement action.
     */
    Position getTargetPosition();

    /**
     * Retrieves the primary Order that the unit is assigned. Primary orders are distinct actions such as Orders::AttackUnit and Orders::PlayerGuard. Returns The primary Order that the unit is executing.
     */
    Order getOrder();

    /**
     * Retrieves the secondary Order that the unit is assigned. Secondary orders are run in the background as a sub-order. An example would be Orders::TrainFighter, because a Carrier can move and train fighters at the same time. Returns The secondary Order that the unit is executing.
     */
    Order getSecondaryOrder();

    /**
     * Retrieves the unit's primary order target. This is usually set when the low level unit AI acquires a new target automatically. For example if an enemy Probe comes in range of your Marine, the Marine will start attacking it, and getOrderTarget will be set in this case, but not getTarget. Returns The Unit that this unit is currently targetting. See also getTarget, getOrder
     */
    Unit getOrderTarget();

    /**
     * Retrieves the target position for the unit's order. For example, when Orders::Move is assigned, getTargetPosition returns the end of the unit's path, but this returns the location that the unit is trying to move to. Returns Position that this unit is currently targetting. See also getTargetPosition, getOrder
     */
    Position getOrderTargetPosition();

    /**
     * Retrieves the position the structure is rallying units to once they are completed. Returns Position that a completed unit coming from this structure will travel to. Return values Positions::None If this building does not produce units. Note If getRallyUnit is valid, then this value is ignored. See also setRallyPoint, getRallyUnit
     */
    Position getRallyPosition();

    /**
     * Retrieves the unit the structure is rallying units to once they are completed. Units will then follow the targetted unit. Returns Unit that a completed unit coming from this structure will travel to. Return values nullptr If the structure is not rallied to a unit or it does not produce units. Note A rallied unit takes precedence over a rallied position. That is if the return value is valid(non-null), then getRallyPosition is ignored. See also setRallyPoint, getRallyPosition
     */
    Unit getRallyUnit();

    /**
     * Retrieves the add-on that is attached to this unit. Returns Unit interface that represents the add-on that is attached to this unit. Return values nullptr if this unit does not have an add-on.
     */
    Unit getAddon();

    /**
     * Retrieves the Nydus Canal that is attached to this one. Every Nydus Canal can place a "Nydus Exit" which, when connected, can be travelled through by Zerg units. Returns Unit interface representing the Nydus Canal connected to this one. Return values nullptr if the unit is not a Nydus Canal, is not owned, or has not placed a Nydus Exit.
     */
    Unit getNydusExit();

    /**
     * Retrieves the power-up that the worker unit is holding. Power-ups are special units such as the Flag in the Capture The Flag game type, which can be picked up by worker units. Note If your bot is strictly melee/1v1, then this method is not necessary. Returns The Unit interface object that represents the power-up. Return values nullptr If the unit is not carrying anything. Example BWAPI::Unitset myUnits = BWAPI::Broodwar->self()getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u ) { // If we are carrying a flag if ( u->getPowerUp() && u->getPowerUp()->getType() == BWAPI::UnitTypes::Powerup_Flag ) u->move( u->getClosestUnit(BWAPI::Filter::IsFlagBeacon && BWAPI::Filter::IsOwned) ); // return it to our flag beacon to score } Note If this function returns a successful state, then the following function calls will also return a successful state: getType().isWorker(), isCompleted()
     */
    Unit getPowerUp();

    /**
     * Retrieves the Transport(Dropship, Shuttle, Overlord ) or Bunker unit that has this unit loaded inside of it. Returns Unit interface object representing the Transport(Dropship, Shuttle, Overlord ) containing this unit. Return values nullptr if this unit is not in a Transport(Dropship, Shuttle, Overlord ).
     */
    Unit getTransport();

    /**
     * Retrieves the set of units that are contained within this Bunker or Transport(Dropship, Shuttle, Overlord ). Returns A Unitset object containing all of the units that are loaded inside of the current unit.
     */
    List<Unit> getLoadedUnits();

    /**
     * Retrieves the remaining unit-space available for Bunkers and Transports(Dropships, Shuttles, Overlords ). Returns The number of spots available to transport a unit. See also getLoadedUnits
     */
    int getSpaceRemaining();

    /**
     * Retrieves the parent Carrier that owns this Interceptor. Returns The parent Carrier unit that has ownership of this one. Return values nullptr if the current unit is not an Interceptor.
     */
    Unit getCarrier();

    /**
     * Retrieves the set of Interceptors controlled by this unit. This is intended for Carriers. Returns Unitset containing Interceptor units owned by this one.
     */
    List<Unit> getInterceptors();

    /**
     * Retrieves the parent Hatchery, Lair, or Hive that owns this particular unit. This is intended for Larvae. Returns Hatchery unit that has ownership of this larva. Return values nullptr if the current unit is not a Larva or has no parent.
     */
    Unit getHatchery();

    /**
     * Retrieves the set of Larvae that were spawned by this unit. Only Hatcheries, Lairs, and Hives are capable of spawning Larvae. This is like clicking the "Select Larva" button and getting the selection of Larvae. Returns Unitset containing Larva units owned by this unit. The set will be empty if there are none.
     */
    List<Unit> getLarva();

    /**
     * Retrieves the set of all units in a given radius of the current unit. Takes into account this unit's dimensions. Can optionally specify a filter that is composed using BWAPI Filter semantics to include only specific units (such as only ground units, etc.) Parameters radius The radius, in pixels, to search for units. pred (optional) The composed function predicate to include only specific (desired) units in the set. Defaults to nullptr, which means no filter. Returns A Unitset containing the set of units that match the given criteria. Example usage: // Get main building closest to start location. BWAPI::Unit pMain = BWAPI::Broodwar->getClosestUnit( BWAPI::Broodwar->self()->getStartLocation(), BWAPI::Filter::IsResourceDepot ); if ( pMain ) // check if pMain is valid { // Get sets of resources and workers BWAPI::Unitset myResources = pMain->getUnitsInRadius(1024, BWAPI::Filter::IsMineralField); if ( !myResources.empty() ) // check if we have resources nearby { BWAPI::Unitset myWorkers = pMain->getUnitsInRadius(512, BWAPI::Filter::IsWorker && BWAPI::Filter::IsIdle && BWAPI::Filter::IsOwned ); while ( !myWorkers.empty() ) // make sure we command all nearby idle workers, if any { for ( auto u = myResources.begin(); u != myResources.end() && !myWorkers.empty(); ++u ) { myWorkers.back()->gather(*u); myWorkers.pop_back(); } } } // myResources not empty } // pMain != nullptr See also getClosestUnit, getUnitsInWeaponRange, Game::getUnitsInRadius, Game::getUnitsInRectangle
     */
    List<Unit> getUnitsInRadius(int radius);

    /**
     * Obtains the set of units within weapon range of this unit. Parameters weapon The weapon type to use as a filter for distance and units that can be hit by it. pred (optional) A predicate used as an additional filter. If omitted, no additional filter is used. See also getUnitsInRadius, getClosestUnit, Game::getUnitsInRadius, Game::getUnitsInRectangle
     */
    List<Unit> getUnitsInWeaponRange(WeaponType weapon);

    /**
     * Checks if the current unit is housing a Nuke. This is only available for Nuclear Silos. Returns true if this unit has a Nuke ready, and false if there is no Nuke.
     */
    boolean hasNuke();

    /**
     * Checks if the current unit is accelerating. Returns true if this unit is accelerating, and false otherwise
     */
    boolean isAccelerating();

    /**
     * Checks if this unit is currently attacking something. Returns true if this unit is attacking another unit, and false if it is not.
     */
    boolean isAttacking();

    /**
     * Checks if this unit is currently playing an attack animation. Issuing commands while this returns true may interrupt the unit's next attack sequence. Returns true if this unit is currently running an attack frame, and false if interrupting the unit is feasible. Note This function is only available to some unit types, specifically those that play special animations when they attack.
     */
    boolean isAttackFrame();

    /**
     * Checks if the current unit is being constructed. This is mostly applicable to Terran structures which require an SCV to be constructing a structure. Return values true if this is either a Protoss structure, Zerg structure, or Terran structure being constructed by an attached SCV. false if this is either completed, not a structure, or has no SCV constructing it See also build, cancelConstruction, haltConstruction, isConstructing
     */
    boolean isBeingConstructed();

    /**
     * Checks this Mineral Field or Refinery is currently being gathered from. Returns true if this unit is a resource container and being harvested by a worker, and false otherwise
     */
    boolean isBeingGathered();

    /**
     * Checks if this unit is currently being healed by a Medic or repaired by a SCV. Returns true if this unit is being healed, and false otherwise.
     */
    boolean isBeingHealed();

    /**
     * Checks if this unit is currently blinded by a Medic 's Optical Flare ability. Blinded units have reduced sight range and cannot detect other units. Returns true if this unit is blind, and false otherwise
     */
    boolean isBlind();

    /**
     * Checks if the current unit is slowing down to come to a stop. Returns true if this unit is breaking, false if it has stopped or is still moving at full speed.
     */
    boolean isBraking();

    /**
     * Checks if the current unit is burrowed, either using the Burrow ability, or is an armed Spider Mine. Returns true if this unit is burrowed, and false otherwise See also burrow, unburrow
     */
    boolean isBurrowed();

    /**
     * Checks if this worker unit is carrying some vespene gas. Returns true if this is a worker unit carrying vespene gas, and false if it is either not a worker, or not carrying gas. Example BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u ) { if ( u->isIdle() && (u->isCarryingGas() || u->isCarryingMinerals()) ) u->returnCargo(); } Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted(), getType().isWorker() See also returnCargo, isGatheringGas, isCarryingMinerals
     */
    boolean isCarryingGas();

    /**
     * Checks if this worker unit is carrying some minerals. Returns true if this is a worker unit carrying minerals, and false if it is either not a worker, or not carrying minerals. Example BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u ) { if ( u->isIdle() && (u->isCarryingGas() || u->isCarryingMinerals()) ) u->returnCargo(); } Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted(), getType().isWorker() See also returnCargo, isGatheringMinerals, isCarryingMinerals
     */
    boolean isCarryingMinerals();

    /**
     * Checks if this unit is currently cloaked. Returns true if this unit is cloaked, and false if it is visible. See also cloak, decloak
     */
    boolean isCloaked();

    /**
     * Checks if this unit has finished being constructed, trained, morphed, or warped in, and can now receive orders. Returns true if this unit is completed, and false if it is under construction or inaccessible.
     */
    boolean isCompleted();

    /**
     * Checks if a unit is either constructing something or moving to construct something. Returns true when a unit has been issued an order to build a structure and is moving to the build location, or is currently constructing something. See also isBeingConstructed, build, cancelConstruction, haltConstruction
     */
    boolean isConstructing();

    /**
     * Checks if this unit has the Defensive Matrix effect. Returns true if the Defensive Matrix ability was used on this unit, and false otherwise.
     */
    boolean isDefenseMatrixed();

    /**
     * Checks if this unit is visible or revealed by a detector unit. If this is false and isVisible is true, then the unit is only partially visible and requires a detector in order to be targetted. Returns true if this unit is detected, and false if it needs a detector unit nearby in order to see it. Note If this function returns a successful state, then the following function calls will also return a successful state: isVisible
     */
    boolean isDetected();

    /**
     * Checks if the Queen ability Ensnare has been used on this unit. Returns true if the unit is ensnared, and false if it is not
     */
    boolean isEnsnared();

    /**
     * This macro function checks if this unit is in the air. That is, the unit is either a flyer or a flying building. Returns true if this unit is in the air, and false if it is on the ground See also UnitType::isFlyer, UnitInterface::isLifted
     */
    boolean isFlying();

    /**
     * Checks if this unit is following another unit. When a unit is following another unit, it simply moves where the other unit does, and does not attack enemies when it is following. Returns true if this unit is following another unit, and false if it is not Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted See also follow, getTarget
     */
    boolean isFollowing();

    /**
     * Checks if this unit is currently gathering gas. That is, the unit is either moving to a refinery, waiting to enter a refinery, harvesting from the refinery, or returning gas to a resource depot. Returns true if this unit is harvesting gas, and false if it is not Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted, getType().isWorker() See also isCarryingGas
     */
    boolean isGatheringGas();

    /**
     * Checks if this unit is currently harvesting minerals. That is, the unit is either moving to a Mineral Field, waiting to mine, mining minerals, or returning minerals to a resource depot. Returns true if this unit is gathering minerals, and false if it is not Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted, getType().isWorker() See also isCarryingMinerals
     */
    boolean isGatheringMinerals();

    /**
     * Checks if this unit is a hallucination. Hallucinations are created by the High Templar using the Hallucination ability. Enemy hallucinations are unknown if Flag::CompleteMapInformation is disabled. Hallucinations have a time limit until they are destroyed (see UnitInterface::getRemoveTimer). Returns true if the unit is a hallucination and false otherwise. See also getRemoveTimer
     */
    boolean isHallucination();

    /**
     * Checks if the unit is currently holding position. A unit that is holding position will attack other units, but will not chase after them. Returns true if this unit is holding position, and false if it is not. See also holdPosition
     */
    boolean isHoldingPosition();

    /**
     * Checks if this unit is running an idle order. This function is particularly useful when checking for units that aren't doing any tasks that you assigned. A unit is considered idle if it is not doing any of the following: Training Constructing Morphing Researching Upgrading In addition to running one of the following orders: Orders::PlayerGuard: Player unit idle. Orders::Guard: Generic unit idle. Orders::Stop Orders::PickupIdle Orders::Nothing: Structure/generic idle. Orders::Medic: Medic idle. Orders::Carrier: Carrier idle. Orders::Reaver: Reaver idle. Orders::Critter: Critter idle. Orders::Neutral: Neutral unit idle. Orders::TowerGuard: Turret structure idle. Orders::Burrowed: Burrowed unit idle. Orders::NukeTrain Orders::Larva: Larva idle. BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u ) { // Order idle worker to gather from closest mineral field if ( u->getType().isWorker() && u->isIdle() ) u->gather( u->getClosestUnit( BWAPI::Filter::IsMineralField ) ); } Returns true if this unit is idle, and false if this unit is performing any action, such as moving or attacking Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted See also UnitInterface::stop
     */
    boolean isIdle();

    /**
     * Checks if the unit can be interrupted. Returns true if this unit can be interrupted, or false if this unit is uninterruptable
     */
    boolean isInterruptible();

    /**
     * Checks the invincibility state for this unit. Returns true if this unit is currently invulnerable, and false if it is vulnerable
     */
    boolean isInvincible();

    /**
     * Checks if the target unit can immediately be attacked by this unit in the current frame. Parameters target The target unit to use in this check. Returns true if target is within weapon range of this unit's appropriate weapon, and false otherwise. Return values false if target is invalid, inaccessible, too close, too far, or this unit does not have a weapon that can attack target.
     */
    boolean isInWeaponRange(Unit target);

    /**
     * Checks if this unit is irradiated by a Science Vessel 's Irradiate ability. Returns true if this unit is irradiated, and false otherwise Example usage: BWAPI::Unitset myUnits = BWAPI::Broodwar->self()->getUnits(); for ( auto u = myUnits.begin(); u != myUnits.end(); ++u ) { if ( u->isIrradiated() && u->getIrradiateTimer > 50 && BWAPI::Broodwar->self()->hasResearched(BWAPI::TechTypes::Restoration) ) { BWAPI::Unit medic = u->getClosestUnit( BWAPI::Filter::GetType == BWAPI::UnitTypes::Terran_Medic && BWAPI::Filter::Energy >= BWAPI::TechTypes::Restoration.energyCost() ); if ( medic ) medic->useTech(BWAPI::TechTypes::Restoration, *u); } } See also getIrradiateTimer
     */
    boolean isIrradiated();

    /**
     * Checks if this unit is a Terran building and lifted off the ground. This function generally implies this->getType().isBuilding() and this->isCompleted() both return true. Returns true if this unit is a Terran structure lifted off the ground. Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted, getType().isFlyingBuilding() See also isFlying
     */
    boolean isLifted();

    /**
     * Checks if this unit is currently loaded into another unit such as a Transport(Dropship, Shuttle, Overlord ). Returns true if this unit is loaded in another one, and false otherwise Note If this function returns a successful state, then the following function calls will also return a successful state: isCompleted See also load, unload, unloadAll
     */
    boolean isLoaded();

    /**
     * Checks if this unit is currently locked by a Ghost. Returns true if this unit is locked down, and false otherwise See also getLockdownTimer
     */
    boolean isLockedDown();

    /**
     * Checks if this unit has been maelstrommed by a Dark Archon. Returns true if this unit is maelstrommed, and false otherwise See also getMaelstromTimer
     */
    boolean isMaelstrommed();

    /**
     * Finds out if the current unit is morphing or not. Zerg units and structures often have the ability to morph into different types of units. This function allows you to identify when this process is occurring. Return values true if the unit is currently morphing. false if the unit is not morphing See also morph, cancelMorph, getBuildType, getRemainingBuildTime
     */
    boolean isMorphing();

    /**
     * Checks if this unit is currently moving. Returns true if this unit is moving, and false if it is not See also stop
     */
    boolean isMoving();

    /**
     * Checks if this unit has been parasited by some other player. Returns true if this unit is inflicted with Parasite, and false if it is clean
     */
    boolean isParasited();

    /**
     * Checks if this unit is patrolling between two positions. Returns true if this unit is patrolling and false if it is not See also patrol
     */
    boolean isPatrolling();

    /**
     * Checks if this unit has been been plagued by a Defiler. Returns true if this unit is inflicted with Plague and is taking damage, and false if it is clean See also getPlagueTimer
     */
    boolean isPlagued();

    /**
     * Checks if this unit is repairing or moving to repair another unit. This is only applicable to SCVs. Returns true if this unit is currently repairing or moving to repair another unit, and false if it is not
     */
    boolean isRepairing();

    /**
     * Checks if this unit is a structure that is currently researching a technology. See TechTypes for a complete list of technologies in Broodwar. Returns true if this structure is researching a technology, false otherwise See also research, cancelResearch, getTech, getRemainingResearchTime, Note If this function returns a successful state, then the following function calls will also return a successful state: exists(), isCompleted(), getType().isBuilding()
     */
    boolean isResearching();

    /**
     * Checks if this unit has been selected in the user interface. This function is only available if the flag Flag::UserInput is enabled. Returns true if this unit is currently selected, and false if this unit is not selected See also Game::getSelectedUnits
     */
    boolean isSelected();

    /**
     * Checks if this unit is currently sieged. This is only applicable to Siege Tanks. Returns true if the unit is in siege mode, and false if it is either not in siege mode or not a Siege Tank See also siege, unsiege
     */
    boolean isSieged();

    /**
     * Checks if the unit is starting to attack. Returns true if this unit is starting an attack. See also attack, getGroundWeaponCooldown, getAirWeaponCooldown
     */
    boolean isStartingAttack();

    /**
     * Checks if this unit is inflicted with Stasis Field by an Arbiter. Returns true if this unit is locked in a Stasis Field and is unable to move, and false if it is free. Note This function does not necessarily imply that the unit is invincible, since there is a feature in the Use Map Settings game type that allows stasised units to be vulnerable. See also getStasisTimer
     */
    boolean isStasised();

    /**
     * Checks if this unit is currently under the influence of a Stim Packs. Returns true if this unit has used a stim pack, false otherwise See also getStimTimer
     */
    boolean isStimmed();

    /**
     * Checks if this unit is currently trying to resolve a collision by randomly moving around. Returns true if this unit is currently stuck and trying to resolve a collision, and false if this unit is free
     */
    boolean isStuck();

    /**
     * Checks if this unit is training a new unit. For example, a Barracks training a Marine. Note It is possible for a unit to remain in the training queue with no progress. In that case, this function will return false because of supply or unit count limitations. Returns true if this unit is currently training another unit, and false otherwise. See also train, getTrainingQueue, cancelTrain, getRemainingTrainTime
     */
    boolean isTraining();

    /**
     * Checks if the current unit is being attacked. Has a small delay before this returns false again when the unit is no longer being attacked. Returns true if this unit has been attacked within the past few frames, and false if it has not
     */
    boolean isUnderAttack();

    /**
     * Checks if this unit is under the cover of a Dark Swarm. Returns true if this unit is protected by a Dark Swarm, and false if it is not
     */
    boolean isUnderDarkSwarm();

    /**
     * Checks if this unit is currently being affected by a Disruption Web. Returns true if this unit is under the effects of Disruption Web.
     */
    boolean isUnderDisruptionWeb();

    /**
     * Checks if this unit is currently taking damage from a Psionic Storm. Returns true if this unit is losing hit points from a Psionic Storm, and false otherwise.
     */
    boolean isUnderStorm();

    /**
     * Checks if this unit has power. Most structures are powered by default, but Protoss structures require a Pylon to be powered and functional. Returns true if this unit has power or is inaccessible, and false if this unit is unpowered. Since 4.0.1 Beta (previously isUnpowered)
     */
    boolean isPowered();

    /**
     * Checks if this unit is a structure that is currently upgrading an upgrade. See UpgradeTypes for a full list of upgrades in Broodwar. Returns true if this structure is upgrading, false otherwise See also upgrade, cancelUpgrade, getUpgrade, getRemainingUpgradeTime Note If this function returns a successful state, then the following function calls will also return a successful state: exists(), isCompleted(), getType().isBuilding()
     */
    boolean isUpgrading();

    /**
     * Checks if this unit is visible. Parameters player (optional) The player to check visibility for. If this parameter is omitted, then the BWAPI player obtained from Game::self will be used. Returns true if this unit is visible to the specified player, and false if it is not. Note If the Flag::CompleteMapInformation flag is enabled, existing units hidden by the fog of war will be accessible, but isVisible will still return false. See also exists
     */
    boolean isVisible();

    boolean isVisible(Player player);

    /**
     * Performs some cheap checks to attempt to quickly detect whether the unit is unable to be targetted as the target unit of an unspecified command. Return values true if BWAPI was unable to determine whether the unit can be a target. false if an error occurred and the unit can not be a target. See also Game::getLastError, UnitInterface::canTargetUnit
     */
    boolean isTargetable();

    /**
     * This function issues a command to the unit(s), however it is used for interfacing only, and is recommended to use one of the more specific command functions when writing an AI. Parameters command A UnitCommand containing command parameters such as the type, position, target, etc. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also UnitCommandTypes, Game::getLastError, UnitInterface::canIssueCommand
     */
    boolean issueCommand(UnitCommand command);

    /**
     * Orders the unit(s) to attack move to the specified position or attack the specified unit. Parameters target A Position or a Unit to designate as the target. If a Position is used, the unit will perform an Attack Move command. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. A Medic will use Heal Move instead of attack. See also Game::getLastError, UnitInterface::canAttack
     */
    boolean attack(Position target);

    boolean attack(Unit target);

    boolean attack(Position target, boolean shiftQueueCommand);

    boolean attack(Unit target, boolean shiftQueueCommand);

    /**
     * Orders the worker unit(s) to construct a structure at a target position. Parameters type The UnitType to build. target A TilePosition to specify the build location, specifically the upper-left corner of the location. If the target is not specified, then the function call will be redirected to the train command. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. You must have sufficient resources and meet the necessary requirements in order to build a structure. See also Game::getLastError, UnitInterface::train, UnitInterface::cancelConstruction, UnitInterface::canBuild
     */
    boolean build(UnitType type);

    boolean build(UnitType type, TilePosition target);

    /**
     * Orders the Terran structure(s) to construct an add-on. Parameters type The add-on UnitType to construct. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. You must have sufficient resources and meet the necessary requirements in order to build a structure. See also Game::getLastError, UnitInterface::build, UnitInterface::cancelAddon, UnitInterface::canBuildAddon
     */
    boolean buildAddon(UnitType type);

    /**
     * Orders the unit(s) to add a UnitType to its training queue, or morphs into the UnitType if it is Zerg. Parameters type The UnitType to train. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. You must have sufficient resources, supply, and meet the necessary requirements in order to train a unit. This command is also used for training Interceptors and Scarabs. If you call this using a Hatchery, Lair, or Hive, then it will automatically pass the command to one of its Larvae. See also Game::getLastError, UnitInterface::build, UnitInterface::morph, UnitInterface::cancelTrain, UnitInterface::isTraining, UnitInterface::canTrain
     */
    boolean train();

    boolean train(UnitType type);

    /**
     * Orders the unit(s) to morph into a different UnitType. Parameters type The UnitType to morph into. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also Game::getLastError, UnitInterface::build, UnitInterface::morph, UnitInterface::canMorph
     */
    boolean morph(UnitType type);

    /**
     * Orders the unit to research the given tech type. Parameters tech The TechType to research. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also cancelResearch, isResearching, getRemainingResearchTime, getTech, canResearch
     */
    boolean research(TechType tech);

    /**
     * Orders the unit to upgrade the given upgrade type. Parameters upgrade The UpgradeType to upgrade. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also cancelUpgrade, isUpgrading, getRemainingUpgradeTime, getUpgrade, canUpgrade
     */
    boolean upgrade(UpgradeType upgrade);

    /**
     * Orders the unit to set its rally position to the specified position or unit. Parameters target The target position or target unit that this structure will rally to. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also getRallyPosition, getRallyUnit, canSetRallyPoint, canSetRallyPosition, canSetRallyUnit
     */
    boolean setRallyPoint(Position target);

    boolean setRallyPoint(Unit target);

    /**
     * Orders the unit to move from its current position to the specified position. Parameters target The target position to move to. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isMoving, canMove
     */
    boolean move(Position target);

    boolean move(Position target, boolean shiftQueueCommand);

    /**
     * Orders the unit to patrol between its current position and the specified position. While patrolling, units will attack and chase enemy units that they encounter, and then return to its patrol route. Medics will automatically heal units and then return to their patrol route. Parameters target The position to patrol to. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isPatrolling, canPatrol
     */
    boolean patrol(Position target);

    boolean patrol(Position target, boolean shiftQueueCommand);

    /**
     * Orders the unit to hold its position. Parameters shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also canHoldPosition, isHoldingPosition
     */
    boolean holdPosition();

    boolean holdPosition(boolean shiftQueueCommand);

    /**
     * Orders the unit to stop. Parameters shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also canStop, isIdle
     */
    boolean stop();

    boolean stop(boolean shiftQueueCommand);

    /**
     * Orders the unit to follow the specified unit. Units that are following other units will not perform any other actions such as attacking. They will ignore attackers. Parameters target The target unit to start following. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isFollowing, canFollow, getOrderTarget
     */
    boolean follow(Unit target);

    boolean follow(Unit target, boolean shiftQueueCommand);

    /**
     * Orders the unit to gather the specified unit (must be mineral or refinery type). Parameters target The target unit to gather from. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isGatheringGas, isGatheringMinerals, canGather
     */
    boolean gather(Unit target);

    boolean gather(Unit target, boolean shiftQueueCommand);

    /**
     * Orders the unit to return its cargo to a nearby resource depot such as a Command Center. Only workers that are carrying minerals or gas can be ordered to return cargo. Parameters shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isCarryingGas, isCarryingMinerals, canReturnCargo
     */
    boolean returnCargo();

    boolean returnCargo(boolean shiftQueueCommand);

    /**
     * Orders the unit to repair the specified unit. Only Terran SCVs can be ordered to repair, and the target must be a mechanical Terran unit or building. Parameters target The unit to repair. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isRepairing, canRepair
     */
    boolean repair(Unit target);

    boolean repair(Unit target, boolean shiftQueueCommand);

    /**
     * Orders the unit to burrow. Either the unit must be a Lurker, or the unit must be a Zerg ground unit that is capable of Burrowing, and Burrow technology must be researched. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also unburrow, isBurrowed, canBurrow
     */
    boolean burrow();

    /**
     * Orders a burrowed unit to unburrow. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also burrow, isBurrowed, canUnburrow
     */
    boolean unburrow();

    /**
     * Orders the unit to cloak. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also decloak, isCloaked, canCloak
     */
    boolean cloak();

    /**
     * Orders a cloaked unit to decloak. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also cloak, isCloaked, canDecloak
     */
    boolean decloak();

    /**
     * Orders the unit to siege. Only works for Siege Tanks. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also unsiege, isSieged, canSiege
     */
    boolean siege();

    /**
     * Orders the unit to unsiege. Only works for sieged Siege Tanks. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also siege, isSieged, canUnsiege
     */
    boolean unsiege();

    /**
     * Orders the unit to lift. Only works for liftable Terran structures. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also land, isLifted, canLift
     */
    boolean lift();

    /**
     * Orders the unit to land. Only works for Terran structures that are currently lifted. Parameters target The tile position to land this structure at. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also lift, isLifted, canLand
     */
    boolean land(TilePosition target);

    /**
     * Orders the unit to load the target unit. Only works if this unit is a Transport(Dropship, Shuttle, Overlord ) or Bunker type. Parameters target The target unit to load into this Transport(Dropship, Shuttle, Overlord ) or Bunker. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also unload, unloadAll, getLoadedUnits, isLoaded
     */
    boolean load(Unit target);

    boolean load(Unit target, boolean shiftQueueCommand);

    /**
     * Orders the unit to unload the target unit. Only works for Transports(Dropships, Shuttles, Overlords ) and Bunkers. Parameters target Unloads the target unit from this Transport(Dropship, Shuttle, Overlord ) or Bunker. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also load, unloadAll, getLoadedUnits, isLoaded, canUnload, canUnloadAtPosition
     */
    boolean unload(Unit target);

    /**
     * Orders the unit to unload all loaded units at the unit's current position. Only works for Transports(Dropships, Shuttles, Overlords ) and Bunkers. Parameters shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also load, unload, getLoadedUnits, isLoaded, canUnloadAll, canUnloadAtPosition
     */
    boolean unloadAll();

    boolean unloadAll(boolean shiftQueueCommand);

    /**
     * Orders the unit to unload all loaded units at the unit's current position. Only works for Transports(Dropships, Shuttles, Overlords ) and Bunkers. Parameters shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also load, unload, getLoadedUnits, isLoaded, canUnloadAll, canUnloadAtPosition
     */
    boolean unloadAll(Position target);

    boolean unloadAll(Position target, boolean shiftQueueCommand);

    /**
     * Works like the right click in the GUI. Parameters target The target position or target unit to right click. shiftQueueCommand (optional) If this value is true, then the order will be queued instead of immediately executed. If this value is omitted, then the order will be executed immediately by default. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also canRightClick, canRightClickPosition, canRightClickUnit
     */
    boolean rightClick(Position target);

    boolean rightClick(Unit target);

    boolean rightClick(Position target, boolean shiftQueueCommand);

    boolean rightClick(Unit target, boolean shiftQueueCommand);

    /**
     * Orders a SCV to stop constructing a structure. This leaves the structure in an incomplete state until it is either cancelled, razed, or completed by another SCV. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isConstructing, canHaltConstruction
     */
    boolean haltConstruction();

    /**
     * Orders this unit to cancel and refund itself from begin constructed. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also isBeingConstructed, build, canCancelConstruction
     */
    boolean cancelConstruction();

    /**
     * Orders this unit to cancel and refund an add-on that is being constructed. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also canCancelAddon, buildAddon
     */
    boolean cancelAddon();

    /**
     * Orders the unit to remove the specified unit from its training queue. Parameters slot (optional) Identifies the slot that will be cancelled. If the specified value is at least 0, then the unit in the corresponding slot from the list provided by getTrainingQueue will be cancelled. If the value is either omitted or -2, then the last slot is cancelled. Note The value of slot is passed directly to Broodwar. Other negative values have no effect. See also train, cancelTrain, isTraining, getTrainingQueue, canCancelTrain, canCancelTrainSlot
     */
    boolean cancelTrain();

    boolean cancelTrain(int slot);

    /**
     * Orders this unit to cancel and refund a unit that is morphing. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also morph, isMorphing, canCancelMorph
     */
    boolean cancelMorph();

    /**
     * Orders this unit to cancel and refund a research that is in progress. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also research, isResearching, getTech, canCancelResearch
     */
    boolean cancelResearch();

    /**
     * Orders this unit to cancel and refund an upgrade that is in progress. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. See also upgrade, isUpgrading, getUpgrade, canCancelUpgrade
     */
    boolean cancelUpgrade();

    /**
     * Orders the unit to use a technology. Parameters tech The technology type to use. target (optional) If specified, indicates the target location or unit to use the tech on. If unspecified, causes the tech to be used without a target (i.e. Stim Packs). Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. See also canUseTechWithOrWithoutTarget, canUseTech, canUseTechWithoutTarget, canUseTechUnit, canUseTechPosition, TechTypes
     */
    boolean useTech(TechType tech);

    boolean useTech(TechType tech, Position target);

    boolean useTech(TechType tech, Unit target);

    /**
     * Moves a Flag Beacon to a different location. This is only used for Capture The Flag or Use Map Settings game types. Parameters target The target tile position to place the Flag Beacon. Returns true if the command was passed to Broodwar, and false if BWAPI determined that the command would fail. Note There is a small chance for a command to fail after it has been passed to Broodwar. This command is only available for the first 10 minutes of the game, as in Broodwar. See also canPlaceCOP
     */
    boolean placeCOP(TilePosition target);

    /**
     * Checks whether the unit is able to execute the given command. If you are calling this function repeatedly (e.g. to generate a collection of valid commands), you can avoid repeating the same kinds of checks by specifying false for some of the optional boolean arguments. Make sure that the state hasn't changed since the check was done though (eg a new frame/event, or a command issued). Also see the more specific functions. Parameters command A UnitCommand to check. checkCanUseTechPositionOnPositions Only used if the command type is UnitCommandTypes::enums::Use_Tech_Position. A boolean for whether to perform cheap checks for whether the unit is unable to target any positions using the command's TechType (i.e. regardless of what the other command parameters are). You can set this to false if you know this check has already just been performed. checkCanUseTechUnitOnUnits Only used if the command type is UnitCommandTypes::enums::Use_Tech_Unit. A boolean for whether to perform cheap checks for whether the unit is unable to target any units using the command's TechType (i.e. regardless of what the other command parameters are). You can set this to false if you know this check has already just been performed. checkCanBuildUnitType Only used if the command type is UnitCommandTypes::Build. A boolean for whether to perform cheap checks for whether the unit is unable to build the specified UnitType (i.e. regardless of what the other command parameters are). You can set this to false if you know this check has already just been performed. checkCanTargetUnit Only used for command types that can target a unit. A boolean for whether to perform UnitInterface::canTargetUnit as a check. You can set this to false if you know this check has already just been performed. checkCanIssueCommandType A boolean for whether to perform UnitInterface::canIssueCommandType as a check. You can set this to false if you know this check has already just been performed. checkCommandibility A boolean for whether to perform UnitInterface::canCommand as a check. You can set this to false if you know this check has already just been performed. Return values true if BWAPI determined that the command is valid. false if an error occurred and the command is invalid. See also UnitCommandTypes, Game::getLastError, UnitInterface::canCommand, UnitInterface::canIssueCommandType, UnitInterface::canTargetUnit
     */
    boolean canIssueCommand(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanBuildUnitType, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canIssueCommand(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanBuildUnitType, boolean checkCanTargetUnit);

    boolean canIssueCommand(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanBuildUnitType);

    boolean canIssueCommand(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits);

    boolean canIssueCommand(UnitCommand command, boolean checkCanUseTechPositionOnPositions);

    boolean canIssueCommand(UnitCommand command);

    boolean canIssueCommand(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanBuildUnitType, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute the given command as part of a Unitset (even if none of the units in the Unitset are able to execute the command individually). The reason this function exists is because some commands are valid for an individual unit but not for those individuals as a group (e.g. buildings, critters) and some commands are only valid for a unit if it is commanded as part of a unit group, e.g.: attackMove/attackUnit for a Unitset, some of which can't attack, e.g. High Templar. This is supported simply for consistency with BW's behaviour - you could issue move command(s) individually instead. attackMove/move/patrol/rightClickPosition for air unit(s) + e.g. Larva, as part of the air stacking technique. This is supported simply for consistency with BW's behaviour - you could issue move/patrol/rightClickPosition command(s) for them individually instead. Note BWAPI allows the following special cases to command a unit individually (rather than only allowing it to be commanded as part of a Unitset). These commands are not available to a user in BW when commanding units individually, but BWAPI allows them for convenience: attackMove for Medic, which is equivalent to Heal Move. holdPosition for burrowed Lurker, for ambushes. stop for Larva, to move it to a different side of the Hatchery / Lair / Hive (e.g. so that Drones morphed later morph nearer to minerals/gas). See also UnitCommandTypes, Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::canCommandGrouped, UnitInterface::canIssueCommandTypeGrouped, UnitInterface::canTargetUnit
     */
    boolean canIssueCommandGrouped(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped);

    boolean canIssueCommandGrouped(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canIssueCommandGrouped(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanTargetUnit);

    boolean canIssueCommandGrouped(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits);

    boolean canIssueCommandGrouped(UnitCommand command, boolean checkCanUseTechPositionOnPositions);

    boolean canIssueCommandGrouped(UnitCommand command);

    boolean canIssueCommandGrouped(UnitCommand command, boolean checkCanUseTechPositionOnPositions, boolean checkCanUseTechUnitOnUnits, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Performs some cheap checks to attempt to quickly detect whether the unit is unable to execute any commands (eg the unit is stasised). Return values true if BWAPI was unable to determine whether the unit can be commanded. false if an error occurred and the unit can not be commanded. See also Game::getLastError, UnitInterface::canIssueCommand
     */
    boolean canCommand();

    /**
     * Performs some cheap checks to attempt to quickly detect whether the unit is unable to execute any commands as part of a Unitset (eg buildings, critters). Return values true if BWAPI was unable to determine whether the unit can be commanded grouped. false if an error occurred and the unit can not be commanded grouped. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canIssueCommand
     */
    boolean canCommandGrouped();

    boolean canCommandGrouped(boolean checkCommandibility);

    /**
     * Performs some cheap checks to attempt to quickly detect whether the unit is unable to execute the given command type (i.e. regardless of what other possible command parameters could be). Parameters ct A UnitCommandType. checkCommandibility A boolean for whether to perform UnitInterface::canCommand as a check. You can set this to false if you know this check has already just been performed. Return values true if BWAPI was unable to determine whether the command type is invalid. false if an error occurred and the command type is invalid. See also UnitCommandTypes, Game::getLastError, UnitInterface::canIssueCommand
     */
    boolean canIssueCommandType(UnitCommandType ct);

    boolean canIssueCommandType(UnitCommandType ct, boolean checkCommandibility);

    /**
     * Performs some cheap checks to attempt to quickly detect whether the unit is unable to execute the given command type (i.e. regardless of what other possible command parameters could be) as part of a Unitset. Parameters ct A UnitCommandType. checkCommandibilityGrouped A boolean for whether to perform UnitInterface::canCommandGrouped as a check. You can set this to false if you know this check has already just been performed. checkCommandibility A boolean for whether to perform UnitInterface::canCommand as a check. You can set this to false if you know this check has already just been performed. Return values true if BWAPI was unable to determine whether the command type is invalid. false if an error occurred and the command type is invalid. See also UnitCommandTypes, Game::getLastError, UnitInterface::canIssueCommandGrouped
     */
    boolean canIssueCommandTypeGrouped(UnitCommandType ct, boolean checkCommandibilityGrouped);

    boolean canIssueCommandTypeGrouped(UnitCommandType ct);

    boolean canIssueCommandTypeGrouped(UnitCommandType ct, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Performs some cheap checks to attempt to quickly detect whether the unit is unable to use the given unit as the target unit of an unspecified command. Parameters targetUnit A target unit for an unspecified command. checkCommandibility A boolean for whether to perform UnitInterface::canCommand as a check. You can set this to false if you know this check has already just been performed. Return values true if BWAPI was unable to determine whether the unit can target the given unit. false if an error occurred and the unit can not target the given unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::isTargetable
     */
    boolean canTargetUnit(Unit targetUnit);

    boolean canTargetUnit(Unit targetUnit, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack-move or attack a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack, UnitInterface::canAttackMove, UnitInterface::canAttackUnit
     */
    boolean canAttack();

    boolean canAttack(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack-move or attack a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack, UnitInterface::canAttackMove, UnitInterface::canAttackUnit
     */
    boolean canAttack(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canAttack(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canAttack(Position target, boolean checkCanTargetUnit);

    boolean canAttack(Unit target, boolean checkCanTargetUnit);

    boolean canAttack(Position target);

    boolean canAttack(Unit target);

    boolean canAttack(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    boolean canAttack(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack-move or attack a unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canAttack
     */
    boolean canAttackGrouped(boolean checkCommandibilityGrouped);

    boolean canAttackGrouped();

    boolean canAttackGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack-move or attack a unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canAttack
     */
    boolean canAttackGrouped(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped);

    boolean canAttackGrouped(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped);

    boolean canAttackGrouped(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canAttackGrouped(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canAttackGrouped(Position target, boolean checkCanTargetUnit);

    boolean canAttackGrouped(Unit target, boolean checkCanTargetUnit);

    boolean canAttackGrouped(Position target);

    boolean canAttackGrouped(Unit target);

    boolean canAttackGrouped(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    boolean canAttackGrouped(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute an attack command to attack-move. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack
     */
    boolean canAttackMove();

    boolean canAttackMove(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute an attack command to attack-move, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canAttackMove
     */
    boolean canAttackMoveGrouped(boolean checkCommandibilityGrouped);

    boolean canAttackMoveGrouped();

    boolean canAttackMoveGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack
     */
    boolean canAttackUnit();

    boolean canAttackUnit(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::attack
     */
    boolean canAttackUnit(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canAttackUnit(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canAttackUnit(Unit targetUnit);

    boolean canAttackUnit(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack a unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canAttackUnit
     */
    boolean canAttackUnitGrouped(boolean checkCommandibilityGrouped);

    boolean canAttackUnitGrouped();

    boolean canAttackUnitGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an attack command to attack a unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canAttackUnit
     */
    boolean canAttackUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped);

    boolean canAttackUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canAttackUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canAttackUnitGrouped(Unit targetUnit);

    boolean canAttackUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a build command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::build
     */
    boolean canBuild();

    boolean canBuild(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a build command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::build
     */
    boolean canBuild(UnitType uType, boolean checkCanIssueCommandType);

    boolean canBuild(UnitType uType);

    boolean canBuild(UnitType uType, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a build command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::build
     */
    boolean canBuild(UnitType uType, TilePosition tilePos, boolean checkTargetUnitType, boolean checkCanIssueCommandType);

    boolean canBuild(UnitType uType, TilePosition tilePos, boolean checkTargetUnitType);

    boolean canBuild(UnitType uType, TilePosition tilePos);

    boolean canBuild(UnitType uType, TilePosition tilePos, boolean checkTargetUnitType, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a buildAddon command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::buildAddon
     */
    boolean canBuildAddon();

    boolean canBuildAddon(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a buildAddon command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::buildAddon
     */
    boolean canBuildAddon(UnitType uType, boolean checkCanIssueCommandType);

    boolean canBuildAddon(UnitType uType);

    boolean canBuildAddon(UnitType uType, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a train command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::train
     */
    boolean canTrain();

    boolean canTrain(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a train command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::train
     */
    boolean canTrain(UnitType uType, boolean checkCanIssueCommandType);

    boolean canTrain(UnitType uType);

    boolean canTrain(UnitType uType, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a morph command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::morph
     */
    boolean canMorph();

    boolean canMorph(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a morph command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::morph
     */
    boolean canMorph(UnitType uType, boolean checkCanIssueCommandType);

    boolean canMorph(UnitType uType);

    boolean canMorph(UnitType uType, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a research command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::research
     */
    boolean canResearch();

    boolean canResearch(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a research command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::research
     */
    boolean canResearch(TechType type);

    boolean canResearch(TechType type, boolean checkCanIssueCommandType);

    /**
     * Cheap checks for whether the unit is able to execute an upgrade command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::upgrade
     */
    boolean canUpgrade();

    boolean canUpgrade(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an upgrade command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::upgrade
     */
    boolean canUpgrade(UpgradeType type);

    boolean canUpgrade(UpgradeType type, boolean checkCanIssueCommandType);

    /**
     * Cheap checks for whether the unit is able to execute a setRallyPoint command to a position or unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::setRallyPoint, UnitInterface::canSetRallyPosition, UnitInterface::canSetRallyUnit.
     */
    boolean canSetRallyPoint();

    boolean canSetRallyPoint(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a setRallyPoint command to a position or unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::setRallyPoint, UnitInterface::canSetRallyPosition, UnitInterface::canSetRallyUnit.
     */
    boolean canSetRallyPoint(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canSetRallyPoint(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canSetRallyPoint(Position target, boolean checkCanTargetUnit);

    boolean canSetRallyPoint(Unit target, boolean checkCanTargetUnit);

    boolean canSetRallyPoint(Position target);

    boolean canSetRallyPoint(Unit target);

    boolean canSetRallyPoint(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    boolean canSetRallyPoint(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a setRallyPoint command to a position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::setRallyPoint
     */
    boolean canSetRallyPosition();

    boolean canSetRallyPosition(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a setRallyPoint command to a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::setRallyPoint
     */
    boolean canSetRallyUnit();

    boolean canSetRallyUnit(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a setRallyPoint command to a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::setRallyPoint
     */
    boolean canSetRallyUnit(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canSetRallyUnit(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canSetRallyUnit(Unit targetUnit);

    boolean canSetRallyUnit(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a move command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::move
     */
    boolean canMove();

    boolean canMove(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a move command, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canMove
     */
    boolean canMoveGrouped(boolean checkCommandibilityGrouped);

    boolean canMoveGrouped();

    boolean canMoveGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a patrol command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::patrol
     */
    boolean canPatrol();

    boolean canPatrol(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a patrol command, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canPatrol
     */
    boolean canPatrolGrouped(boolean checkCommandibilityGrouped);

    boolean canPatrolGrouped();

    boolean canPatrolGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a follow command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::follow
     */
    boolean canFollow();

    boolean canFollow(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a follow command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::follow
     */
    boolean canFollow(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canFollow(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canFollow(Unit targetUnit);

    boolean canFollow(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a gather command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::gather
     */
    boolean canGather();

    boolean canGather(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a gather command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::gather
     */
    boolean canGather(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canGather(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canGather(Unit targetUnit);

    boolean canGather(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a returnCargo command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::returnCargo
     */
    boolean canReturnCargo();

    boolean canReturnCargo(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a holdPosition command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::holdPosition
     */
    boolean canHoldPosition();

    boolean canHoldPosition(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a stop command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::stop
     */
    boolean canStop();

    boolean canStop(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a repair command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::repair
     */
    boolean canRepair();

    boolean canRepair(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a repair command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::repair
     */
    boolean canRepair(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canRepair(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canRepair(Unit targetUnit);

    boolean canRepair(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a burrow command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::burrow
     */
    boolean canBurrow();

    boolean canBurrow(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute an unburrow command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unburrow
     */
    boolean canUnburrow();

    boolean canUnburrow(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a cloak command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cloak
     */
    boolean canCloak();

    boolean canCloak(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a decloak command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::decloak
     */
    boolean canDecloak();

    boolean canDecloak(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a siege command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::siege
     */
    boolean canSiege();

    boolean canSiege(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute an unsiege command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unsiege
     */
    boolean canUnsiege();

    boolean canUnsiege(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a lift command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::lift
     */
    boolean canLift();

    boolean canLift(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a land command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::land
     */
    boolean canLand();

    boolean canLand(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a land command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::land
     */
    boolean canLand(TilePosition target, boolean checkCanIssueCommandType);

    boolean canLand(TilePosition target);

    boolean canLand(TilePosition target, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a load command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::load
     */
    boolean canLoad();

    boolean canLoad(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a load command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::load
     */
    boolean canLoad(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canLoad(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canLoad(Unit targetUnit);

    boolean canLoad(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an unload command or unloadAll at current position command or unloadAll at a different position command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unload, UnitInterface::unloadAll
     */
    boolean canUnloadWithOrWithoutTarget();

    boolean canUnloadWithOrWithoutTarget(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an unload command or unloadAll at current position command or unloadAll at a different position command, for a given position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unload, UnitInterface::unloadAll
     */
    boolean canUnloadAtPosition(Position targDropPos, boolean checkCanIssueCommandType);

    boolean canUnloadAtPosition(Position targDropPos);

    boolean canUnloadAtPosition(Position targDropPos, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an unload command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unload
     */
    boolean canUnload();

    boolean canUnload(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an unload command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unload
     */
    boolean canUnload(Unit targetUnit, boolean checkCanTargetUnit, boolean checkPosition, boolean checkCanIssueCommandType);

    boolean canUnload(Unit targetUnit, boolean checkCanTargetUnit, boolean checkPosition);

    boolean canUnload(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canUnload(Unit targetUnit);

    boolean canUnload(Unit targetUnit, boolean checkCanTargetUnit, boolean checkPosition, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute an unloadAll command for the current position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unloadAll
     */
    boolean canUnloadAll();

    boolean canUnloadAll(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an unloadAll command for a different position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unloadAll
     */
    boolean canUnloadAllPosition();

    boolean canUnloadAllPosition(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute an unloadAll command for a different position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::unloadAll
     */
    boolean canUnloadAllPosition(Position targDropPos, boolean checkCanIssueCommandType);

    boolean canUnloadAllPosition(Position targDropPos);

    boolean canUnloadAllPosition(Position targDropPos, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a position or unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::rightClick, UnitInterface::canRightClickPosition, UnitInterface::canRightClickUnit.
     */
    boolean canRightClick();

    boolean canRightClick(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a position or unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::rightClick, UnitInterface::canRightClickPosition, UnitInterface::canRightClickUnit.
     */
    boolean canRightClick(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canRightClick(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canRightClick(Position target, boolean checkCanTargetUnit);

    boolean canRightClick(Unit target, boolean checkCanTargetUnit);

    boolean canRightClick(Position target);

    boolean canRightClick(Unit target);

    boolean canRightClick(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    boolean canRightClick(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a position or unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canRightClickUnit
     */
    boolean canRightClickGrouped(boolean checkCommandibilityGrouped);

    boolean canRightClickGrouped();

    boolean canRightClickGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a position or unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canRightClickUnit
     */
    boolean canRightClickGrouped(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped);

    boolean canRightClickGrouped(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped);

    boolean canRightClickGrouped(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canRightClickGrouped(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canRightClickGrouped(Position target, boolean checkCanTargetUnit);

    boolean canRightClickGrouped(Unit target, boolean checkCanTargetUnit);

    boolean canRightClickGrouped(Position target);

    boolean canRightClickGrouped(Unit target);

    boolean canRightClickGrouped(Position target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    boolean canRightClickGrouped(Unit target, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a rightClick command for a position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::rightClick
     */
    boolean canRightClickPosition();

    boolean canRightClickPosition(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a rightClick command for a position, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canRightClick
     */
    boolean canRightClickPositionGrouped(boolean checkCommandibilityGrouped);

    boolean canRightClickPositionGrouped();

    boolean canRightClickPositionGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::rightClick
     */
    boolean canRightClickUnit();

    boolean canRightClickUnit(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::rightClick
     */
    boolean canRightClickUnit(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canRightClickUnit(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canRightClickUnit(Unit targetUnit);

    boolean canRightClickUnit(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canRightClickUnit
     */
    boolean canRightClickUnitGrouped(boolean checkCommandibilityGrouped);

    boolean canRightClickUnitGrouped();

    boolean canRightClickUnitGrouped(boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a rightClick command to a unit, as part of a Unitset. See also Game::getLastError, UnitInterface::canIssueCommandGrouped, UnitInterface::canRightClickUnit
     */
    boolean canRightClickUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped);

    boolean canRightClickUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType);

    boolean canRightClickUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit);

    boolean canRightClickUnitGrouped(Unit targetUnit);

    boolean canRightClickUnitGrouped(Unit targetUnit, boolean checkCanTargetUnit, boolean checkCanIssueCommandType, boolean checkCommandibilityGrouped, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a haltConstruction command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::haltConstruction
     */
    boolean canHaltConstruction();

    boolean canHaltConstruction(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a cancelConstruction command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelConstruction
     */
    boolean canCancelConstruction();

    boolean canCancelConstruction(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a cancelAddon command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelAddon
     */
    boolean canCancelAddon();

    boolean canCancelAddon(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a cancelTrain command for any slot. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelTrain
     */
    boolean canCancelTrain();

    boolean canCancelTrain(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a cancelTrain command for an unspecified slot. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelTrain
     */
    boolean canCancelTrainSlot();

    boolean canCancelTrainSlot(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a cancelTrain command for an unspecified slot. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelTrain
     */
    boolean canCancelTrainSlot(int slot, boolean checkCanIssueCommandType);

    boolean canCancelTrainSlot(int slot);

    boolean canCancelTrainSlot(int slot, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a cancelMorph command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelMorph
     */
    boolean canCancelMorph();

    boolean canCancelMorph(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a cancelResearch command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelResearch
     */
    boolean canCancelResearch();

    boolean canCancelResearch(boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a cancelUpgrade command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::cancelUpgrade
     */
    boolean canCancelUpgrade();

    boolean canCancelUpgrade(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a useTech command without a target or or a useTech command with a target position or a useTech command with a target unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
     */
    boolean canUseTechWithOrWithoutTarget();

    boolean canUseTechWithOrWithoutTarget(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a useTech command without a target or or a useTech command with a target position or a useTech command with a target unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
     */
    boolean canUseTechWithOrWithoutTarget(TechType tech, boolean checkCanIssueCommandType);

    boolean canUseTechWithOrWithoutTarget(TechType tech);

    boolean canUseTechWithOrWithoutTarget(TechType tech, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a useTech command for a specified position or unit (only specify nullptr if the TechType does not target another position/unit). See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech, UnitInterface::canUseTechWithoutTarget, UnitInterface::canUseTechUnit, UnitInterface::canUseTechPosition
     */
    boolean canUseTech(TechType tech, Position target, boolean checkCanTargetUnit, boolean checkTargetsType, boolean checkCanIssueCommandType);

    boolean canUseTech(TechType tech, Unit target, boolean checkCanTargetUnit, boolean checkTargetsType, boolean checkCanIssueCommandType);

    boolean canUseTech(TechType tech, Position target, boolean checkCanTargetUnit, boolean checkTargetsType);

    boolean canUseTech(TechType tech, Unit target, boolean checkCanTargetUnit, boolean checkTargetsType);

    boolean canUseTech(TechType tech, Position target, boolean checkCanTargetUnit);

    boolean canUseTech(TechType tech, Unit target, boolean checkCanTargetUnit);

    boolean canUseTech(TechType tech, Position target);

    boolean canUseTech(TechType tech, Unit target);

    boolean canUseTech(TechType tech);

    boolean canUseTech(TechType tech, Position target, boolean checkCanTargetUnit, boolean checkTargetsType, boolean checkCanIssueCommandType, boolean checkCommandibility);

    boolean canUseTech(TechType tech, Unit target, boolean checkCanTargetUnit, boolean checkTargetsType, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a useTech command without a target. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
     */
    boolean canUseTechWithoutTarget(TechType tech, boolean checkCanIssueCommandType);

    boolean canUseTechWithoutTarget(TechType tech);

    boolean canUseTechWithoutTarget(TechType tech, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a useTech command with an unspecified target unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
     */
    boolean canUseTechUnit(TechType tech, boolean checkCanIssueCommandType);

    boolean canUseTechUnit(TechType tech);

    boolean canUseTechUnit(TechType tech, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a useTech command with an unspecified target unit. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
     */
    boolean canUseTechUnit(TechType tech, Unit targetUnit, boolean checkCanTargetUnit, boolean checkTargetsUnits, boolean checkCanIssueCommandType);

    boolean canUseTechUnit(TechType tech, Unit targetUnit, boolean checkCanTargetUnit, boolean checkTargetsUnits);

    boolean canUseTechUnit(TechType tech, Unit targetUnit, boolean checkCanTargetUnit);

    boolean canUseTechUnit(TechType tech, Unit targetUnit);

    boolean canUseTechUnit(TechType tech, Unit targetUnit, boolean checkCanTargetUnit, boolean checkTargetsUnits, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a useTech command with an unspecified target position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
     */
    boolean canUseTechPosition(TechType tech, boolean checkCanIssueCommandType);

    boolean canUseTechPosition(TechType tech);

    boolean canUseTechPosition(TechType tech, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Checks whether the unit is able to execute a useTech command with an unspecified target position. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::useTech
     */
    boolean canUseTechPosition(TechType tech, Position target, boolean checkTargetsPositions, boolean checkCanIssueCommandType);

    boolean canUseTechPosition(TechType tech, Position target, boolean checkTargetsPositions);

    boolean canUseTechPosition(TechType tech, Position target);

    boolean canUseTechPosition(TechType tech, Position target, boolean checkTargetsPositions, boolean checkCanIssueCommandType, boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a placeCOP command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::placeCOP
     */
    boolean canPlaceCOP();

    boolean canPlaceCOP(boolean checkCommandibility);

    /**
     * Cheap checks for whether the unit is able to execute a placeCOP command. See also Game::getLastError, UnitInterface::canIssueCommand, UnitInterface::placeCOP
     */
    boolean canPlaceCOP(TilePosition target, boolean checkCanIssueCommandType);

    boolean canPlaceCOP(TilePosition target);

    boolean canPlaceCOP(TilePosition target, boolean checkCanIssueCommandType, boolean checkCommandibility);

}