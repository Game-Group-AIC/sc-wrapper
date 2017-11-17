package gg.fel.cvut.cz.api;

import bwapi.Pair;

import java.util.Optional;

public interface IChokePoint extends IAbstractPoint {

    Optional<Pair<IRegion, IRegion>> getRegions();

    Optional<Pair<IPosition, IPosition>> getSides();

    Optional<IPosition> getCenter();

    Optional<Double> getWidth();

    default Optional<IChokePoint> getNearestChokePoint() {
        return Optional.of(this);
    }

}
