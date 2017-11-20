package gg.fel.cvut.cz.api;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public interface IBaseLocation extends IAbstractPoint, InGameInterface, Serializable {

    Optional<Integer> minerals();

    Optional<Integer> gas();

    Optional<Set<IUnit>> getMinerals();

    Optional<Set<IUnit>> getStaticMinerals();

    Optional<Set<IUnit>> getGeysers();

    Optional<Double> getGroundDistance(IBaseLocation other);

    Optional<Double> getAirDistance(IBaseLocation other);

    Optional<Boolean> isIsland();

    default Optional<Boolean> isMineralOnly() {
        return getGeysers().map(Set::isEmpty);
    }

    Optional<Boolean> isStartLocation();

    default Optional<IBaseLocation> getNearestBaseLocation() {
        return Optional.of(this);
    }
}
