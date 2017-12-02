package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IWeaponType;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.OrderEnum;
import gg.fel.cvut.cz.enums.TechTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class TechType extends AContainer implements ITechType, Serializable {

  public TechType(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public TechTypeEnum getTechType() {
    return null;
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
  public Optional<Integer> gasPrice() {
    return null;
  }

  @Override
  public Optional<Integer> researchTime() {
    return null;
  }

  @Override
  public Optional<Integer> energyCost() {
    return null;
  }

  @Override
  public Optional<IWeaponType> getWeapon() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsUnit() {
    return null;
  }

  @Override
  public Optional<Boolean> targetsPosition() {
    return null;
  }

  @Override
  public Optional<OrderEnum> getOrder() {
    return null;
  }

  @Override
  public Optional<IUnitType> requiredUnit() {
    return null;
  }

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return null;
  }
}
