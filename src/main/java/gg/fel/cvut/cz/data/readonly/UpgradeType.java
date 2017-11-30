package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.UpgradeTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class UpgradeType extends AContainer implements IUpgradeType, Serializable {

  public UpgradeType(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<UpgradeTypeEnum> getUpgradeType() {
    return null;
  }

  @Override
  public IRace getRace() {
    return null;
  }

  @Override
  public Optional<Integer> mineralPrice() {
    return null;
  }

  @Override
  public Optional<Integer> mineralPrice(int level) {
    return null;
  }

  @Override
  public Optional<Integer> mineralPriceFactor() {
    return null;
  }

  @Override
  public Optional<Integer> gasPrice() {
    return null;
  }

  @Override
  public Optional<Integer> gasPrice(int level) {
    return null;
  }

  @Override
  public Optional<Integer> gasPriceFactor() {
    return null;
  }

  @Override
  public Optional<Integer> upgradeTime() {
    return null;
  }

  @Override
  public Optional<Integer> upgradeTime(int level) {
    return null;
  }

  @Override
  public Optional<Integer> maxRepeats() {
    return null;
  }

  @Override
  public Optional<IUnitType> whatUpgrades() {
    return null;
  }

  @Override
  public Optional<IUnitType> whatsRequired() {
    return null;
  }

  @Override
  public Optional<IUnitType> whatsRequired(int level) {
    return null;
  }

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return null;
  }
}
