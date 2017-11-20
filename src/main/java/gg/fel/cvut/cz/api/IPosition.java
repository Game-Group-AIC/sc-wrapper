package gg.fel.cvut.cz.api;

import java.io.Serializable;
import java.util.Optional;

/**
 * Positions are measured in pixels and are the highest resolution.
 */
public interface IPosition extends IAbstractPoint, InGameInterface, Serializable {
    Optional<ITilePosition> getTilePosition();

    @Override
    default Optional<IPosition> getPosition() {
        return Optional.of(this);
    }

    Optional<IRegion> getRegion();

    @Override
    Optional<Integer> getX();

    @Override
    Optional<Integer> getY();
}