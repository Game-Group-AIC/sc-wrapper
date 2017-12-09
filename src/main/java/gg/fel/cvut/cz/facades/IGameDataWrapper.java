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
import gg.fel.cvut.cz.data.readonly.WalkPosition;
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
import gg.fel.cvut.cz.wrappers.WWalkPosition;
import gg.fel.cvut.cz.wrappers.WWeaponType;
import java.util.Optional;

/**
 * Contract to return containers
 */
public interface IGameDataWrapper {

  Optional<Bullet> getDataContainer(WBullet bullet);

  Optional<BaseLocation> getDataContainer(WBaseLocation baseLocation);

  Optional<ChokePoint> getDataContainer(WChokePoint chokePoint);

  Optional<Player> getDataContainer(WPlayer player);

  Optional<Position> getDataContainer(WPosition position);

  Optional<Race> getDataContainer(WRace race);

  Optional<Region> getDataContainer(WRegion region);

  Optional<TechType> getDataContainer(WTechType techType);

  Optional<TilePosition> getDataContainer(WTilePosition tilePosition);

  Optional<Unit> getDataContainer(WUnit unit);

  Optional<UnitType> getDataContainer(WUnitType unitType);

  Optional<UpgradeType> getDataContainer(WUpgradeType upgradeType);

  Optional<WeaponType> getDataContainer(WWeaponType weaponType);

  Optional<WalkPosition> getDataContainer(WWalkPosition walkPosition);

}
