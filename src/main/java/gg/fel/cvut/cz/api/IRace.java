package gg.fel.cvut.cz.api;

import java.io.Serializable;
import java.util.Set;

public interface IRace extends InGameInterface, Serializable {

  /**
   * Retrieves the default worker type for this UpdatableRace. Note In Starcraft, workers are the
   * units that are used to construct structures. Returns EUnitType of the worker that this race
   * uses.
   */
  IUnitType getWorker();

  /**
   * Retrieves the default resource center EUnitType that is used to create expansions for this
   * UpdatableRace. Note In Starcraft, the center is the very first structure of the UpdatableRace's
   * technology tree. Also known as its base of operations or resource depot. Returns EUnitType of
   * the center that this race uses.
   */
  IUnitType getCenter();

  /**
   * Retrieves the default structure EUnitType for this UpdatableRace that is used to harvest gas
   * from Vespene Geysers. Note In Starcraft, you must first construct a structure over a Vespene
   * Geyser in order to begin harvesting Vespene Gas. Returns EUnitType of the structure used to
   * harvest gas.
   */
  IUnitType getRefinery();

  /**
   * Retrieves the default transport EUnitType for this race that is used to transport ground units
   * across the map. Note In Starcraft, transports will allow you to carry ground units over
   * unpassable terrain. Returns EUnitType for transportation.
   */
  IUnitType getTransport();

  /**
   * Retrieves the default supply provider EUnitType for this race that is used to construct units.
   * Note In Starcraft, training, morphing, or warping in units requires that the player has
   * sufficient supply available for their UpdatableRace. Returns EUnitType that provides the player
   * with supply.
   */
  IUnitType getSupplyProvider();

  Set<IUnitType> getAllUnitTypesOfThisRace();

  Set<ITechType> getAllTechTypesOfThisRace();

  Set<IUpgradeType> getAllUpgradeTypesOfThisRace();

  Set<IWeaponType> getAllWeaponTypesOfThisRace();

}
