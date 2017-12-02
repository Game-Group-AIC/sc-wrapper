package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.data.readonly.TechType;
import gg.fel.cvut.cz.data.readonly.UnitType;
import gg.fel.cvut.cz.data.readonly.UpgradeType;
import gg.fel.cvut.cz.data.readonly.WeaponType;
import gg.fel.cvut.cz.enums.RaceTypeEnum;
import gg.fel.cvut.cz.enums.TechTypeEnum;
import gg.fel.cvut.cz.enums.UnitTypeEnum;
import gg.fel.cvut.cz.enums.UpgradeTypeEnum;
import gg.fel.cvut.cz.enums.WeaponTypeEnum;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Contract on adapter to access game instances
 */
public interface IGameDataAccessAdapter {

  Stream<? extends AContainer> getAllGameInstances();

  Optional<Race> getType(RaceTypeEnum race);

  Optional<TechType> getType(TechTypeEnum techType);

  Optional<UnitType> getType(UnitTypeEnum unitType);

  Optional<UpgradeType> getType(UpgradeTypeEnum techType);

  Optional<WeaponType> getType(WeaponTypeEnum unitType);

}
