package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.readonly.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Update strategy contains strategies for all containers checking if container should be updated
 */
@Builder
@AllArgsConstructor
public class UpdateStrategy {
    //TODO rest of the classes
    private IUpdateContainerStrategy<Bullet> bulletUpdateContainerStrategy;
    private IUpdateContainerStrategy<BaseLocation> baseLocationUpdateContainerStrategy;
    private IUpdateContainerStrategy<ChokePoint> chokePointUpdateContainerStrategy;
    private IUpdateContainerStrategy<Position> positionUpdateContainerStrategy;
    private IUpdateContainerStrategy<Region> regionUpdateContainerStrategy;
    private IUpdateContainerStrategy<TilePosition> tilePositionUpdateContainerStrategy;

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
