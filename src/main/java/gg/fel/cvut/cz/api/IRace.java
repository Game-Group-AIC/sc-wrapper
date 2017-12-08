package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.enums.IGameTypes;
import gg.fel.cvut.cz.enums.RaceTypeEnum;
import gg.fel.cvut.cz.enums.TechTypeEnum;
import gg.fel.cvut.cz.enums.UnitTypeEnum;
import gg.fel.cvut.cz.enums.UpgradeTypeEnum;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import java.io.Serializable;
import java.util.Set;

@JsonDeserialize(as = Race.class)
public interface IRace extends InGameInterface, Serializable {

  RaceTypeEnum getRaceType();

  /**
   * Retrieves the default worker type for this UpdatableRace.
   *
   * Note: In Starcraft, workers are the units that are used to construct structures.
   */
  default UnitTypeEnum getWorker() {
    return IGameTypes.RACE_WORKERS.get(getRaceType());
  }

  /**
   * Retrieves the default resource center UnitTypeEnum that is used to create expansions for this
   * UpdatableRace.
   *
   * Note: In Starcraft, the center is the very first structure of the UpdatableRace's
   * technology tree. Also known as its base of operations or resource depot.
   */
  default  UnitTypeEnum getCenter() {
    return IGameTypes.RACE_RESOURCE_CENTER.get(getRaceType());
  }

  /**
   * Retrieves the default structure UnitTypeEnum for this race that is used to harvest gas
   * from Vespene Geysers.
   *
   * Note? In Starcraft, you must first construct a structure over a Vespene
   * Geyser in order to begin harvesting Vespene Gas.
   */
  default UnitTypeEnum getRefinery() {
    return IGameTypes.RACE_REFINERY.get(getRaceType());
  }

  /**
   * Retrieves the default transport UnitTypeEnum for this race that is used to transport ground
   * units across the map.
   *
   * Note: In Starcraft, transports will allow you to carry ground units over
   * unpassable terrain.
   */
  default UnitTypeEnum getTransport() {
    return IGameTypes.RACE_TRANSPORTS.get(getRaceType());
  }

  /**
   * Retrieves the default supply provider UnitTypeEnum for this race that is used to construct
   * units. Note In Starcraft, training, morphing, or warping in units requires that the player has
   * sufficient supply available for their UpdatableRace. Returns UnitTypeEnum that provides the
   * player with supply.
   */
  default UnitTypeEnum getSupplyProvider() {
    return IGameTypes.RACE_SUPPLY_PROVIDER.get(getRaceType());
  }

  default Set<UnitTypeEnum> getAllUnitTypesOfThisRace() {
    return IGameTypes.RACE_UNIT_TYPES.get(getRaceType());
  }

  default Set<TechTypeEnum> getAllTechTypesOfThisRace() {
    return IGameTypes.RACE_TECH_TYPES.get(getRaceType());
  }

  default Set<UpgradeTypeEnum> getAllUpgradeTypesOfThisRace() {
    return IGameTypes.RACE_UPGRADE_TYPES.get(getRaceType());
  }

  default Set<WeaponTypeEnum> getAllWeaponTypesOfThisRace() {
    return IGameTypes.RACE_WEAPON_TYPES.get(getRaceType());
  }

}
