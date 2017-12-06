package gg.fel.cvut.cz.data.readonly;

import gg.fel.cvut.cz.api.IRace;
import gg.fel.cvut.cz.api.ITechType;
import gg.fel.cvut.cz.api.IUnitType;
import gg.fel.cvut.cz.api.IUpgradeType;
import gg.fel.cvut.cz.api.IWeaponType;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerForType;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.RaceTypeEnum;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

//TODO implement
public class Race extends AContainerForType<bwapi.Race, RaceTypeEnum> implements IRace,
    Serializable {

  public Race(BWReplayCounter bwCounter, RaceTypeEnum raceType) {
    super(bwCounter, raceType);
  }


  @Override
  protected Set<StaticPropertyRegister<?, ?>> staticPropertiesForEqualsAndHashCode() {
    return new HashSet<>();
  }

  @Override
  public RaceTypeEnum getRaceType() {
    return type;
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
  public Optional<Stream<IUnitType>> getAllUnitTypesOfThisRace() {
    return Optional.empty();
  }

  @Override
  public Optional<Stream<ITechType>> getAllTechTypesOfThisRace() {
    return Optional.empty();
  }

  @Override
  public Optional<Stream<IUpgradeType>> getAllUpgradeTypesOfThisRace() {
    return Optional.empty();
  }

  @Override
  public Optional<Stream<IWeaponType>> getAllWeaponTypesOfThisRace() {
    return Optional.empty();
  }
}
