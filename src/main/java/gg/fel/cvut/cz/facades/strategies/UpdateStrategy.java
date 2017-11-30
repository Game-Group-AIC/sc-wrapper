package gg.fel.cvut.cz.facades.strategies;

import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.BulletType;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.data.readonly.Game;
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
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Update strategy contains strategies for all containers checking if container should be updated
 */
@Builder
@AllArgsConstructor
public class UpdateStrategy {

  @Builder.Default
  private final IUpdateContainerStrategy<Bullet> bulletUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<BaseLocation> baseLocationUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<ChokePoint> chokePointUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<Position> positionUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<Region> regionUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<TilePosition> tilePositionUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<Game> gameUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<Player> playerUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<Race> raceUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<TechType> techTypeUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<Unit> unitUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<UnitType> unitTypeUpdateContainerStrategyContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<UpgradeType> upgradeTypeUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<WeaponType> weaponTypeUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;
  @Builder.Default
  private final IUpdateContainerStrategy<BulletType> bulletTypeUpdateContainerStrategy = (container, deltaUpdate, depth) -> true;

  public boolean shouldBeUpdated(BulletType bulletType, int deltaUpdate, int depth) {
    return bulletTypeUpdateContainerStrategy
        .shouldBeUpdated(bulletType, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(WeaponType weaponType, int deltaUpdate, int depth) {
    return weaponTypeUpdateContainerStrategy
        .shouldBeUpdated(weaponType, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(UpgradeType upgradeType, int deltaUpdate, int depth) {
    return upgradeTypeUpdateContainerStrategy
        .shouldBeUpdated(upgradeType, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(UnitType unitType, int deltaUpdate, int depth) {
    return unitTypeUpdateContainerStrategyContainerStrategy
        .shouldBeUpdated(unitType, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(Unit unit, int deltaUpdate, int depth) {
    return unitUpdateContainerStrategy.shouldBeUpdated(unit, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(TechType techType, int deltaUpdate, int depth) {
    return techTypeUpdateContainerStrategy.shouldBeUpdated(techType, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(Race race, int deltaUpdate, int depth) {
    return raceUpdateContainerStrategy.shouldBeUpdated(race, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(Player player, int deltaUpdate, int depth) {
    return playerUpdateContainerStrategy.shouldBeUpdated(player, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(Game game, int deltaUpdate, int depth) {
    return gameUpdateContainerStrategy.shouldBeUpdated(game, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(Bullet bullet, int deltaUpdate, int depth) {
    return bulletUpdateContainerStrategy.shouldBeUpdated(bullet, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(BaseLocation baseLocation, int deltaUpdate, int depth) {
    return baseLocationUpdateContainerStrategy.shouldBeUpdated(baseLocation, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(ChokePoint chokePoint, int deltaUpdate, int depth) {
    return chokePointUpdateContainerStrategy.shouldBeUpdated(chokePoint, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(Position position, int deltaUpdate, int depth) {
    return positionUpdateContainerStrategy.shouldBeUpdated(position, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(Region region, int deltaUpdate, int depth) {
    return regionUpdateContainerStrategy.shouldBeUpdated(region, deltaUpdate, depth);
  }

  public boolean shouldBeUpdated(TilePosition tilePosition, int deltaUpdate, int depth) {
    return tilePositionUpdateContainerStrategy.shouldBeUpdated(tilePosition, deltaUpdate, depth);
  }

}
