package gg.fel.cvut.cz.api;

import java.util.Optional;
import java.util.Set;

/**
 * Build Tiles - each build tile is a 4x4 square of walk tiles, or a 32x32 square of pixels.
 * These are called build tiles because buildability data is available at this resolution, and correspond to the tiles seen in game.
 * For example, a Command Center occupies an area of 4x3 build tiles.
 */
public interface ITilePosition extends IAbstractPoint {
    int SIZE_IN_PIXELS = 32;

    Optional<Integer> getGroundHeight();

    Optional<Set<IUnit>> getUnitsOnTile();

    Optional<IPosition> getPosition();

    default Optional<ITilePosition> getTilePosition() {
        return Optional.of(this);
    }
}