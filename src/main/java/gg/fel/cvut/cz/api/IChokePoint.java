package gg.fel.cvut.cz.api;

import java.io.Serializable;
import java.util.Optional;

public interface IChokePoint extends IAbstractPoint, InGameInterface, Serializable {

    Optional<Tuple<IRegion, IRegion>> getRegions();

    Optional<Tuple<IPosition, IPosition>> getSides();

    default Optional<IPosition> getCenter() {
        return getPosition();
    }

    default Optional<Integer> getX() {
        return getPosition().flatMap(IAbstractPoint::getX);
    }

    default Optional<Integer> getY() {
        return getPosition().flatMap(IAbstractPoint::getY);
    }

    Optional<Double> getWidth();

    default Optional<IChokePoint> getNearestChokePoint() {
        return Optional.of(this);
    }

}
