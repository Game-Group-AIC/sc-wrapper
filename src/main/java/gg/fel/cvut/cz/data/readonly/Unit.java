package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.api.UnitCommand;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.OrderEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

//TODO implement
public class Unit extends AContainer implements IUnit, Serializable {

  protected final StaticPropertyRegister<Integer, Property<Integer>> unitID = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  private final Set<StaticPropertyRegister<?, ?>> toHash = ImmutableSet.of(unitID);

  public Unit(BWReplayCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<Integer> getID() {
    return null;
  }

  @Override
  public Optional<Boolean> exists() {
    return null;
  }

  @Override
  public Optional<IPlayer> getPlayer() {
    return null;
  }

  @Override
  public Optional<IUnitType> getType() {
    return null;
  }

  @Override
  public Optional<IPosition> getPosition() {
    return null;
  }

  @Override
  public Optional<ITilePosition> getTilePosition() {
    return null;
  }

  @Override
  public Optional<Double> getAngle() {
    return null;
  }

  @Override
  public Optional<Double> getVelocityX() {
    return null;
  }

  @Override
  public Optional<Double> getVelocityY() {
    return null;
  }

  @Override
  public Optional<Integer> getHitPoints() {
    return null;
  }

  @Override
  public Optional<Integer> getShields() {
    return null;
  }

  @Override
  public Optional<Integer> getEnergy() {
    return null;
  }

  @Override
  public Optional<Integer> getResources() {
    return null;
  }

  @Override
  public Optional<Integer> getLastCommandFrame() {
    return null;
  }

  @Override
  public Optional<UnitCommand> getLastCommand() {
    return null;
  }

  @Override
  public Optional<IPlayer> getLastAttackingPlayer() {
    return null;
  }

  @Override
  public Optional<IUnitType> getInitialType() {
    return null;
  }

  @Override
  public Optional<IPosition> getInitialPosition() {
    return null;
  }

  @Override
  public Optional<ITilePosition> getInitialTilePosition() {
    return null;
  }

  @Override
  public Optional<Integer> getInitialHitPoints() {
    return null;
  }

  @Override
  public Optional<Integer> getInitialResources() {
    return null;
  }

  @Override
  public Optional<Integer> getKillCount() {
    return null;
  }

  @Override
  public Optional<Integer> getAcidSporeCount() {
    return null;
  }

  @Override
  public Optional<Integer> getInterceptorCount() {
    return null;
  }

  @Override
  public Optional<Integer> getScarabCount() {
    return null;
  }

  @Override
  public Optional<Integer> getSpiderMineCount() {
    return null;
  }

  @Override
  public Optional<Integer> getGroundWeaponCooldown() {
    return null;
  }

  @Override
  public Optional<Integer> getAirWeaponCooldown() {
    return null;
  }

  @Override
  public Optional<Integer> getSpellCooldown() {
    return null;
  }

  @Override
  public Optional<Integer> getDefenseMatrixPoints() {
    return null;
  }

  @Override
  public Optional<Integer> getDefenseMatrixTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getEnsnareTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getIrradiateTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getLockdownTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getMaelstromTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getOrderTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getPlagueTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getRemoveTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getStasisTimer() {
    return null;
  }

  @Override
  public Optional<Integer> getStimTimer() {
    return null;
  }

  @Override
  public Optional<IUnitType> getBuildType() {
    return null;
  }

  @Override
  public Optional<Stream<IUnitType>> getTrainingQueue() {
    return null;
  }

  @Override
  public Optional<ITechType> getTech() {
    return null;
  }

  @Override
  public Optional<IUpgradeType> getUpgrade() {
    return null;
  }

  @Override
  public Optional<Integer> getRemainingBuildTime() {
    return null;
  }

  @Override
  public Optional<Integer> getRemainingTrainTime() {
    return null;
  }

  @Override
  public Optional<Integer> getRemainingResearchTime() {
    return null;
  }

  @Override
  public Optional<Integer> getRemainingUpgradeTime() {
    return null;
  }

  @Override
  public Optional<IUnit> getBuildUnit() {
    return null;
  }

  @Override
  public Optional<IUnit> getTarget() {
    return null;
  }

  @Override
  public Optional<IPosition> getTargetPosition() {
    return null;
  }

  @Override
  public Optional<OrderEnum> getOrder() {
    return null;
  }

  @Override
  public Optional<OrderEnum> getSecondaryOrder() {
    return null;
  }

  @Override
  public Optional<IUnit> getOrderTarget() {
    return null;
  }

  @Override
  public Optional<IPosition> getOrderTargetPosition() {
    return null;
  }

  @Override
  public Optional<IPosition> getRallyPosition() {
    return null;
  }

  @Override
  public Optional<IUnit> getRallyUnit() {
    return null;
  }

  @Override
  public Optional<IUnit> getAddon() {
    return null;
  }

  @Override
  public Optional<IUnit> getNydusExit() {
    return null;
  }

  @Override
  public Optional<IUnit> getPowerUp() {
    return null;
  }

  @Override
  public Optional<IUnit> getTransport() {
    return null;
  }

  @Override
  public Optional<Stream<IUnit>> getLoadedUnits() {
    return null;
  }

  @Override
  public Optional<Integer> getSpaceRemaining() {
    return null;
  }

  @Override
  public Optional<IUnit> getCarrier() {
    return null;
  }

  @Override
  public Optional<Stream<IUnit>> getInterceptors() {
    return null;
  }

  @Override
  public Optional<IUnit> getHatchery() {
    return null;
  }

  @Override
  public Optional<Stream<IUnit>> getLarva() {
    return null;
  }

  @Override
  public Optional<Boolean> hasNuke() {
    return null;
  }

  @Override
  public Optional<Boolean> isAccelerating() {
    return null;
  }

  @Override
  public Optional<Boolean> isAttacking() {
    return null;
  }

  @Override
  public Optional<Boolean> isAttackFrame() {
    return null;
  }

  @Override
  public Optional<Boolean> isBeingConstructed() {
    return null;
  }

  @Override
  public Optional<Boolean> isBeingGathered() {
    return null;
  }

  @Override
  public Optional<Boolean> isBeingHealed() {
    return null;
  }

  @Override
  public Optional<Boolean> isBlind() {
    return null;
  }

  @Override
  public Optional<Boolean> isBraking() {
    return null;
  }

  @Override
  public Optional<Boolean> isBurrowed() {
    return null;
  }

  @Override
  public Optional<Boolean> isCarryingGas() {
    return null;
  }

  @Override
  public Optional<Boolean> isCarryingMinerals() {
    return null;
  }

  @Override
  public Optional<Boolean> isCloaked() {
    return null;
  }

  @Override
  public Optional<Boolean> isCompleted() {
    return null;
  }

  @Override
  public Optional<Boolean> isConstructing() {
    return null;
  }

  @Override
  public Optional<Boolean> isDefenseMatrixed() {
    return null;
  }

  @Override
  public Optional<Boolean> isDetected() {
    return null;
  }

  @Override
  public Optional<Boolean> isEnsnared() {
    return null;
  }

  @Override
  public Optional<Boolean> isFlying() {
    return null;
  }

  @Override
  public Optional<Boolean> isFollowing() {
    return null;
  }

  @Override
  public Optional<Boolean> isGatheringGas() {
    return null;
  }

  @Override
  public Optional<Boolean> isGatheringMinerals() {
    return null;
  }

  @Override
  public Optional<Boolean> isHallucination() {
    return null;
  }

  @Override
  public Optional<Boolean> isHoldingPosition() {
    return null;
  }

  @Override
  public Optional<Boolean> isIdle() {
    return null;
  }

  @Override
  public Optional<Boolean> isInterruptible() {
    return null;
  }

  @Override
  public Optional<Boolean> isInvincible() {
    return null;
  }

  @Override
  public Optional<Boolean> isIrradiated() {
    return null;
  }

  @Override
  public Optional<Boolean> isLifted() {
    return null;
  }

  @Override
  public Optional<Boolean> isLoaded() {
    return null;
  }

  @Override
  public Optional<Boolean> isLockedDown() {
    return null;
  }

  @Override
  public Optional<Boolean> isMaelstrommed() {
    return null;
  }

  @Override
  public Optional<Boolean> isMorphing() {
    return null;
  }

  @Override
  public Optional<Boolean> isMoving() {
    return null;
  }

  @Override
  public Optional<Boolean> isParasited() {
    return null;
  }

  @Override
  public Optional<Boolean> isPatrolling() {
    return null;
  }

  @Override
  public Optional<Boolean> isPlagued() {
    return null;
  }

  @Override
  public Optional<Boolean> isRepairing() {
    return null;
  }

  @Override
  public Optional<Boolean> isResearching() {
    return null;
  }

  @Override
  public Optional<Boolean> isSelected() {
    return null;
  }

  @Override
  public Optional<Boolean> isSieged() {
    return null;
  }

  @Override
  public Optional<Boolean> isStartingAttack() {
    return null;
  }

  @Override
  public Optional<Boolean> isStasised() {
    return null;
  }

  @Override
  public Optional<Boolean> isStimmed() {
    return null;
  }

  @Override
  public Optional<Boolean> isStuck() {
    return null;
  }

  @Override
  public Optional<Boolean> isTraining() {
    return null;
  }

  @Override
  public Optional<Boolean> isUnderAttack() {
    return null;
  }

  @Override
  public Optional<Boolean> isUnderDarkSwarm() {
    return null;
  }

  @Override
  public Optional<Boolean> isUnderDisruptionWeb() {
    return null;
  }

  @Override
  public Optional<Boolean> isUnderStorm() {
    return null;
  }

  @Override
  public Optional<Boolean> isPowered() {
    return null;
  }

  @Override
  public Optional<Boolean> isUpgrading() {
    return null;
  }

  @Override
  public Optional<Boolean> isTargetable() {
    return null;
  }

  @Override
  public Optional<Boolean> canCommand() {
    return null;
  }

  @Override
  public Optional<Boolean> canCommandGrouped() {
    return null;
  }

  @Override
  public Optional<Boolean> canAttack() {
    return null;
  }

  @Override
  public Optional<Boolean> canAttackMove() {
    return null;
  }

  @Override
  public Optional<Boolean> canAttackMoveGrouped() {
    return null;
  }

  @Override
  public Optional<Boolean> canAttackUnit() {
    return null;
  }

  @Override
  public Optional<Boolean> canBuild() {
    return null;
  }

  @Override
  public Optional<Boolean> canBuildAddon() {
    return null;
  }

  @Override
  public Optional<Boolean> canTrain() {
    return null;
  }

  @Override
  public Optional<Boolean> canMorph() {
    return null;
  }

  @Override
  public Optional<Boolean> canResearch() {
    return null;
  }

  @Override
  public Optional<Boolean> canUpgrade() {
    return null;
  }

  @Override
  public Optional<Boolean> canSetRallyPoint() {
    return null;
  }

  @Override
  public Optional<Boolean> canSetRallyPosition() {
    return null;
  }

  @Override
  public Optional<Boolean> canSetRallyUnit() {
    return null;
  }

  @Override
  public Optional<Boolean> canMove() {
    return null;
  }

  @Override
  public Optional<Boolean> canPatrol() {
    return null;
  }

  @Override
  public Optional<Boolean> canFollow() {
    return null;
  }

  @Override
  public Optional<Boolean> canGather() {
    return null;
  }

  @Override
  public Optional<Boolean> canReturnCargo() {
    return null;
  }

  @Override
  public Optional<Boolean> canHoldPosition() {
    return null;
  }

  @Override
  public Optional<Boolean> canStop() {
    return null;
  }

  @Override
  public Optional<Boolean> canRepair() {
    return null;
  }

  @Override
  public Optional<Boolean> canBurrow() {
    return null;
  }

  @Override
  public Optional<Boolean> canUnburrow() {
    return null;
  }

  @Override
  public Optional<Boolean> canCloak() {
    return null;
  }

  @Override
  public Optional<Boolean> canDecloak() {
    return null;
  }

  @Override
  public Optional<Boolean> canSiege() {
    return null;
  }

  @Override
  public Optional<Boolean> canUnsiege() {
    return null;
  }

  @Override
  public Optional<Boolean> canLift() {
    return null;
  }

  @Override
  public Optional<Boolean> canLand() {
    return null;
  }

  @Override
  public Optional<Boolean> canLoad() {
    return null;
  }

  @Override
  public Optional<Boolean> canUnloadWithOrWithoutTarget() {
    return null;
  }

  @Override
  public Optional<Boolean> canUnload() {
    return null;
  }

  @Override
  public Optional<Boolean> canUnloadAll() {
    return null;
  }

  @Override
  public Optional<Boolean> canUnloadAllPosition() {
    return null;
  }

  @Override
  public Optional<Boolean> canHaltConstruction() {
    return null;
  }

  @Override
  public Optional<Boolean> canCancelConstruction() {
    return null;
  }

  @Override
  public Optional<Boolean> canCancelAddon() {
    return null;
  }

  @Override
  public Optional<Boolean> canCancelTrain() {
    return null;
  }

  @Override
  public Optional<Boolean> canCancelTrainSlot() {
    return null;
  }

  @Override
  public Optional<Boolean> canCancelMorph() {
    return null;
  }

  @Override
  public Optional<Boolean> canCancelResearch() {
    return null;
  }

  @Override
  public Optional<Boolean> canCancelUpgrade() {
    return null;
  }

  @Override
  public Optional<Boolean> canUseTechWithOrWithoutTarget() {
    return null;
  }

  @Override
  public Optional<Boolean> canPlaceCOP() {
    return null;
  }

  @Override
  public Optional<Stream<IUnit>> getEnemyUnitsInWeaponRange() {
    return null;
  }

  @Override
  public Optional<Stream<IUnit>> getFriendlyUnitsInRadiusOfSight() {
    return null;
  }

  @Override
  public Optional<Stream<IUnit>> getResourceUnitsInRadiusOfSight() {
    return null;
  }

  @Override
  public Optional<Stream<IUnit>> getEnemyUnitsInRadiusOfSight() {
    return null;
  }

  @Override
  protected Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode() {
    return toHash;
  }
}
