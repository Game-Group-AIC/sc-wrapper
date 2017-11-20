package gg.fel.cvut.cz.api;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Optional;

/**
 * Common ancestor for location based objects to simplify distance computation.
 */
public interface IAbstractPoint extends InGameInterface, Serializable {

    default Optional<Integer> getX() {
        return getPosition().flatMap(IAbstractPoint::getX);
    }

    default Optional<Integer> getY() {
        return getPosition().flatMap(IAbstractPoint::getY);
    }

    default Optional<Double> getApproxDistance(IAbstractPoint other) {
        if (!getX().isPresent() || !getY().isPresent() || !other.getX().isPresent() || !other.getY().isPresent()) {
            return Optional.empty();
        }
        double dx = other.getX().get() - getX().get();
        double dy = other.getY().get() - getY().get();
        return Optional.of(Math.sqrt(dx * dx + dy * dy));
    }

    default Optional<IRegion> getRegion() {
        return getPosition().flatMap(IAbstractPoint::getRegion);
    }

    Optional<IPosition> getPosition();

    default Optional<ITilePosition> getTilePosition() {
        return getPosition().flatMap(IPosition::getTilePosition);
    }

    default Optional<IChokePoint> getNearestChokePoint() {
        return getRegion().flatMap(iRegion -> iRegion.getChokePoints().flatMap(iChokePoints -> iChokePoints.stream()
                .min(Comparator.comparingDouble(o -> o.getApproxDistance(this).orElse(Double.MAX_VALUE)))));
    }

    default Optional<IBaseLocation> getNearestBaseLocation() {
        return getRegion().flatMap(iRegion -> iRegion.getBaseLocations().flatMap(iBaseLocations -> iBaseLocations.stream()
                .min(Comparator.comparingDouble(o -> o.getApproxDistance(this).orElse(Double.MAX_VALUE)))));
    }

}