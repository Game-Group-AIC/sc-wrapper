package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IBullet;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.api.IWeaponType;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.PlayerTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class Player extends AContainer implements IPlayer, Serializable {

  public Player(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<Integer> getID() {
    return null;
  }

  @Override
  public Optional<String> getName() {
    return null;
  }

  @Override
  public Optional<Set<IUnit>> getAllUnits() {
    return null;
  }

  @Override
  public Optional<Set<IBullet>> getAllBullets() {
    return null;
  }

  @Override
  public Optional<Set<IPlayer>> getAllies() {
    return null;
  }

  @Override
  public Optional<Set<IPlayer>> getEnemies() {
    return null;
  }

  @Override
  public Optional<IRace> getRace() {
    return null;
  }

  @Override
  public Optional<PlayerTypeEnum> getType() {
    return null;
  }

  @Override
  public Optional<Boolean> isNeutral() {
    return null;
  }

  @Override
  public Optional<Boolean> isVictorious() {
    return null;
  }

  @Override
  public Optional<Boolean> isDefeated() {
    return null;
  }

  @Override
  public Optional<Boolean> leftGame() {
    return null;
  }

  @Override
  public Optional<Integer> minerals() {
    return null;
  }

  @Override
  public Optional<Integer> gas() {
    return null;
  }

  @Override
  public Optional<Integer> gatheredMinerals() {
    return null;
  }

  @Override
  public Optional<Integer> gatheredGas() {
    return null;
  }

  @Override
  public Optional<Integer> repairedMinerals() {
    return null;
  }

  @Override
  public Optional<Integer> repairedGas() {
    return null;
  }

  @Override
  public Optional<Integer> refundedMinerals() {
    return null;
  }

  @Override
  public Optional<Integer> refundedGas() {
    return null;
  }

  @Override
  public Optional<Integer> spentMinerals() {
    return null;
  }

  @Override
  public Optional<Integer> spentGas() {
    return null;
  }

  @Override
  public Optional<Integer> deadUnitCount() {
    return null;
  }

  @Override
  public Optional<Integer> deadUnitCount(IUnitType unitType) {
    return null;
  }

  @Override
  public Optional<Integer> killedUnitCount() {
    return null;
  }

  @Override
  public Optional<Integer> killedUnitCount(IUnitType unitType) {
    return null;
  }

  @Override
  public Optional<Integer> getUpgradeLevel(IUpgradeType upgrade) {
    return null;
  }

  @Override
  public Optional<Boolean> hasResearched(ITechType tech) {
    return null;
  }

  @Override
  public Optional<Boolean> isResearching(ITechType tech) {
    return null;
  }

  @Override
  public Optional<Boolean> isUpgrading(IUpgradeType upgrade) {
    return null;
  }

  @Override
  public Optional<Integer> maxEnergy(IUnitType unit) {
    return null;
  }

  @Override
  public Optional<Double> topSpeed(IUnitType unit) {
    return null;
  }

  @Override
  public Optional<Integer> weaponMaxRange(IWeaponType weapon) {
    return null;
  }

  @Override
  public Optional<Integer> sightRange(IUnitType unit) {
    return null;
  }

  @Override
  public Optional<Integer> weaponDamageCooldown(IUnitType unit) {
    return null;
  }

  @Override
  public Optional<Integer> armor(IUnitType unit) {
    return null;
  }

  @Override
  public Optional<Integer> damage(IWeaponType wpn) {
    return null;
  }

  @Override
  public Optional<Integer> getUnitScore() {
    return null;
  }

  @Override
  public Optional<Integer> getKillScore() {
    return null;
  }

  @Override
  public Optional<Integer> getBuildingScore() {
    return null;
  }

  @Override
  public Optional<Integer> getRazingScore() {
    return null;
  }

  @Override
  public Optional<Integer> getCustomScore() {
    return null;
  }

  @Override
  public Optional<Boolean> isObserver() {
    return null;
  }

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return null;
  }
}
