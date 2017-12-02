package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.api.IWeaponType;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class Race extends AContainer implements IRace, Serializable {

  public Race(BWCounter bwCounter) {
    super(bwCounter);
  }


  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return null;
  }

  @Override
  public Optional<IUnitType> getWorker() {
    return Optional.empty();
  }

  @Override
  public Optional<IUnitType> getCenter() {
    return Optional.empty();
  }

  @Override
  public Optional<IUnitType> getRefinery() {
    return Optional.empty();
  }

  @Override
  public Optional<IUnitType> getTransport() {
    return Optional.empty();
  }

  @Override
  public Optional<IUnitType> getSupplyProvider() {
    return Optional.empty();
  }

  @Override
  public Optional<Set<IUnitType>> getAllUnitTypesOfThisRace() {
    return Optional.empty();
  }

  @Override
  public Optional<Set<ITechType>> getAllTechTypesOfThisRace() {
    return Optional.empty();
  }

  @Override
  public Optional<Set<IUpgradeType>> getAllUpgradeTypesOfThisRace() {
    return Optional.empty();
  }

  @Override
  public Optional<Set<IWeaponType>> getAllWeaponTypesOfThisRace() {
    return Optional.empty();
  }
}
