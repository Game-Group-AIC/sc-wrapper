package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.enums.RaceTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

@JsonDeserialize(as = Race.class)
public interface IRace extends InGameInterface, Serializable {

  RaceTypeEnum getRaceType();

  /**
   * Retrieves the default worker type for this UpdatableRace. Note In Starcraft, workers are the
   * units that are used to construct structures. Returns UnitTypeEnum of the worker that this race
   * uses.
   */
  Optional<IUnitType> getWorker();

  /**
   * Retrieves the default resource center UnitTypeEnum that is used to create expansions for this
   * UpdatableRace. Note In Starcraft, the center is the very first structure of the UpdatableRace's
   * technology tree. Also known as its base of operations or resource depot. Returns UnitTypeEnum
   * of the center that this race uses.
   */
  Optional<IUnitType> getCenter();

  /**
   * Retrieves the default structure UnitTypeEnum for this UpdatableRace that is used to harvest gas
   * from Vespene Geysers. Note In Starcraft, you must first construct a structure over a Vespene
   * Geyser in order to begin harvesting Vespene Gas. Returns UnitTypeEnum of the structure used to
   * harvest gas.
   */
  Optional<IUnitType> getRefinery();

  /**
   * Retrieves the default transport UnitTypeEnum for this race that is used to transport ground
   * units across the map. Note In Starcraft, transports will allow you to carry ground units over
   * unpassable terrain. Returns UnitTypeEnum for transportation.
   */
  Optional<IUnitType> getTransport();

  /**
   * Retrieves the default supply provider UnitTypeEnum for this race that is used to construct
   * units. Note In Starcraft, training, morphing, or warping in units requires that the player has
   * sufficient supply available for their UpdatableRace. Returns UnitTypeEnum that provides the
   * player with supply.
   */
  Optional<IUnitType> getSupplyProvider();

  Optional<Stream<IUnitType>> getAllUnitTypesOfThisRace();

  Optional<Stream<ITechType>> getAllTechTypesOfThisRace();

  Optional<Stream<IUpgradeType>> getAllUpgradeTypesOfThisRace();

  Optional<Stream<IWeaponType>> getAllWeaponTypesOfThisRace();

}
