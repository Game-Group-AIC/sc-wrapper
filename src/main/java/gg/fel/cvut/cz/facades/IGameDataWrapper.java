package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.data.readonly.Player;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Race;
import gg.fel.cvut.cz.data.readonly.Region;
import gg.fel.cvut.cz.data.readonly.TechType;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.data.readonly.Unit;
import gg.fel.cvut.cz.data.readonly.UnitType;
import gg.fel.cvut.cz.data.readonly.UpgradeType;
import gg.fel.cvut.cz.data.readonly.WeaponType;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.WBullet;
import gg.fel.cvut.cz.wrappers.WChokePoint;
import gg.fel.cvut.cz.wrappers.WPlayer;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.WRace;
import gg.fel.cvut.cz.wrappers.WRegion;
import gg.fel.cvut.cz.wrappers.WTechType;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import gg.fel.cvut.cz.wrappers.WUnit;
import gg.fel.cvut.cz.wrappers.WUnitType;
import gg.fel.cvut.cz.wrappers.WUpgradeType;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import java.util.Optional;

/**
 * Contract to return wrapped game objects
 */
public interface IGameDataWrapper {

  Optional<Bullet> getDataContainer(WBullet bullet);

  Optional<WBullet> getBWInstance(Bullet container);

  Optional<BaseLocation> getDataContainer(WBaseLocation baseLocation);

  Optional<WBaseLocation> getBWInstance(BaseLocation container);

  Optional<ChokePoint> getDataContainer(WChokePoint chokePoint);

  Optional<WChokePoint> getBWInstance(ChokePoint container);

  Optional<Player> getDataContainer(WPlayer player);

  Optional<WPlayer> getBWInstance(Player container);

  Optional<Position> getDataContainer(WPosition position);

  Optional<WPosition> getBWInstance(Position container);

  Optional<Race> getDataContainer(WRace race);

  Optional<WRace> getBWInstance(Race container);

  Optional<Region> getDataContainer(WRegion region);

  Optional<WRegion> getBWInstance(Region container);

  Optional<TechType> getDataContainer(WTechType techType);

  Optional<WTechType> getBWInstance(TechType container);

  Optional<TilePosition> getDataContainer(WTilePosition tilePosition);

  Optional<WTilePosition> getBWInstance(TilePosition container);

  Optional<Unit> getDataContainer(WUnit unit);

  Optional<WUnit> getBWInstance(Unit container);

  Optional<UnitType> getDataContainer(WUnitType unitType);

  Optional<WUnitType> getBWInstance(UnitType container);

  Optional<UpgradeType> getDataContainer(WUpgradeType upgradeType);

  Optional<WUpgradeType> getBWInstance(UpgradeType container);

  Optional<WeaponType> getDataContainer(WWeaponType weaponType);

  Optional<WWeaponType> getBWInstance(WeaponType container);

}
