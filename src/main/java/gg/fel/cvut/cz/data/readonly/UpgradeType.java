package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForType;
import gg.fel.cvut.cz.enums.UpgradeTypeEnum;
import java.io.Serializable;
import java.util.Optional;

//TODO implement
public class UpgradeType extends AContainerForType<bwapi.UpgradeType, UpgradeTypeEnum> implements
    IUpgradeType, Serializable {

  public UpgradeType(BWReplayCounter bwCounter, UpgradeTypeEnum upgradeType) {
    super(bwCounter, upgradeType);
  }

  @Override
  public UpgradeTypeEnum getUpgradeType() {
    return type;
  }

  @Override
  public Optional<IRace> getRace() {
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
}
