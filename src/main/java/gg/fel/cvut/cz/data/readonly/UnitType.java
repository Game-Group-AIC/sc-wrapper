package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.api.IWeaponType;
import gg.fel.cvut.cz.api.Tuple;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.UnitSizeTypeEnum;
import gg.fel.cvut.cz.enums.UnitTypeEnum;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class UnitType extends AContainer implements IUnitType, Serializable {

  public UnitType(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<IRace> getRace() {
    return null;
  }

  @Override
  public UnitTypeEnum getUnitType() {
    return null;
  }

  @Override
  public Optional<Tuple<IUnitType, Integer>> whatBuilds() {
    return null;
  }

  @Override
  public Optional<Map<IUnitType, Integer>> requiredUnits() {
    return null;
  }

  @Override
  public Optional<ITechType> requiredTech() {
    return null;
  }

  @Override
  public Optional<ITechType> cloakingTech() {
    return null;
  }

  @Override
  public Optional<Set<ITechType>> abilities() {
    return null;
  }

  @Override
  public Optional<Set<IUpgradeType>> upgrades() {
    return null;
  }

  @Override
  public Optional<IUpgradeType> armorUpgrade() {
    return null;
  }

  @Override
  public Optional<Integer> maxHitPoints() {
    return null;
  }

  @Override
  public Optional<Integer> maxShields() {
    return null;
  }

  @Override
  public Optional<Integer> maxEnergy() {
    return null;
  }

  @Override
  public Optional<Integer> armor() {
    return null;
  }

  @Override
  public Optional<Integer> mineralPrice() {
    return null;
  }

  @Override
  public Optional<Integer> gasPrice() {
    return null;
  }

  @Override
  public Optional<Integer> buildTime() {
    return null;
  }

  @Override
  public Optional<Integer> supplyRequired() {
    return null;
  }

  @Override
  public Optional<Integer> supplyProvided() {
    return null;
  }

  @Override
  public Optional<Integer> spaceRequired() {
    return null;
  }

  @Override
  public Optional<Integer> spaceProvided() {
    return null;
  }

  @Override
  public Optional<Integer> buildScore() {
    return null;
  }

  @Override
  public Optional<Integer> destroyScore() {
    return null;
  }

  @Override
  public Optional<UnitSizeTypeEnum> size() {
    return null;
  }

  @Override
  public Optional<Integer> tileWidth() {
    return null;
  }

  @Override
  public Optional<Integer> tileHeight() {
    return null;
  }

  @Override
  public Optional<Integer> width() {
    return null;
  }

  @Override
  public Optional<Integer> height() {
    return null;
  }

  @Override
  public Optional<Integer> seekRange() {
    return null;
  }

  @Override
  public Optional<Integer> sightRange() {
    return null;
  }

  @Override
  public Optional<IWeaponType> groundWeapon() {
    return null;
  }

  @Override
  public Optional<Integer> maxGroundHits() {
    return null;
  }

  @Override
  public Optional<IWeaponType> airWeapon() {
    return null;
  }

  @Override
  public Optional<Integer> maxAirHits() {
    return null;
  }

  @Override
  public Optional<Double> topSpeed() {
    return null;
  }

  @Override
  public Optional<Integer> acceleration() {
    return null;
  }

  @Override
  public Optional<Integer> haltDistance() {
    return null;
  }

  @Override
  public Optional<Integer> turnRadius() {
    return null;
  }

  @Override
  public Optional<Boolean> canProduce() {
    return null;
  }

  @Override
  public Optional<Boolean> canAttack() {
    return null;
  }

  @Override
  public Optional<Boolean> canMove() {
    return null;
  }

  @Override
  public Optional<Boolean> isFlyer() {
    return null;
  }

  @Override
  public Optional<Boolean> regeneratesHP() {
    return null;
  }

  @Override
  public Optional<Boolean> isSpellcaster() {
    return null;
  }

  @Override
  public Optional<Boolean> hasPermanentCloak() {
    return null;
  }

  @Override
  public Optional<Boolean> isInvincible() {
    return null;
  }

  @Override
  public Optional<Boolean> isOrganic() {
    return null;
  }

  @Override
  public Optional<Boolean> isMechanical() {
    return null;
  }

  @Override
  public Optional<Boolean> isRobotic() {
    return null;
  }

  @Override
  public Optional<Boolean> isDetector() {
    return null;
  }

  @Override
  public Optional<Boolean> isResourceContainer() {
    return null;
  }

  @Override
  public Optional<Boolean> isResourceDepot() {
    return null;
  }

  @Override
  public Optional<Boolean> isRefinery() {
    return null;
  }

  @Override
  public Optional<Boolean> isWorker() {
    return null;
  }

  @Override
  public Optional<Boolean> requiresPsi() {
    return null;
  }

  @Override
  public Optional<Boolean> requiresCreep() {
    return null;
  }

  @Override
  public Optional<Boolean> isTwoUnitsInOneEgg() {
    return null;
  }

  @Override
  public Optional<Boolean> isBurrowable() {
    return null;
  }

  @Override
  public Optional<Boolean> isCloakable() {
    return null;
  }

  @Override
  public Optional<Boolean> isBuilding() {
    return null;
  }

  @Override
  public Optional<Boolean> isAddon() {
    return null;
  }

  @Override
  public Optional<Boolean> isFlyingBuilding() {
    return null;
  }

  @Override
  public Optional<Boolean> isNeutral() {
    return null;
  }

  @Override
  public Optional<Boolean> isHero() {
    return null;
  }

  @Override
  public Optional<Boolean> isPowerup() {
    return null;
  }

  @Override
  public Optional<Boolean> isBeacon() {
    return null;
  }

  @Override
  public Optional<Boolean> isFlagBeacon() {
    return null;
  }

  @Override
  public Optional<Boolean> isSpecialBuilding() {
    return null;
  }

  @Override
  public Optional<Boolean> isSpell() {
    return null;
  }

  @Override
  public Optional<Boolean> producesCreep() {
    return null;
  }

  @Override
  public Optional<Boolean> producesLarva() {
    return null;
  }

  @Override
  public Optional<Boolean> isMineralField() {
    return null;
  }

  @Override
  public Optional<Boolean> isCritter() {
    return null;
  }

  @Override
  public Optional<Boolean> canBuildAddon() {
    return null;
  }

  @Override
  public Optional<Set<ITechType>> researchesWhat() {
    return null;
  }

  @Override
  public Optional<Set<IUpgradeType>> upgradesWhat() {
    return null;
  }

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return null;
  }
}
