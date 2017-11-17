package gg.fel.cvut.cz.api;

import java.util.Optional;

/**
 * Positions are measured in pixels and are the highest resolution.
 */
public interface IPosition extends IAbstractPoint {
    Optional<ITilePosition> getTilePosition();

    @Override
    default Optional<IPosition> getPosition() {
        return Optional.of(this);
    }
}