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
import java.util.Set;

//TODO implement
public class Race extends AContainer implements IRace, Serializable {

  public Race(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public IUnitType getWorker() {
    return null;
  }

  @Override
  public IUnitType getCenter() {
    return null;
  }

  @Override
  public IUnitType getRefinery() {
    return null;
  }

  @Override
  public IUnitType getTransport() {
    return null;
  }

  @Override
  public IUnitType getSupplyProvider() {
    return null;
  }

  @Override
  public Set<IUnitType> getAllUnitTypesOfThisRace() {
    return null;
  }

  @Override
  public Set<ITechType> getAllTechTypesOfThisRace() {
    return null;
  }

  @Override
  public Set<IUpgradeType> getAllUpgradeTypesOfThisRace() {
    return null;
  }

  @Override
  public Set<IWeaponType> getAllWeaponTypesOfThisRace() {
    return null;
  }

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return null;
  }
}
