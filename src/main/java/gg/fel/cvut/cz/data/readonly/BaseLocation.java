package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.facades.UpdateStrategy;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public class BaseLocation extends AContainer implements IBaseLocation, Serializable {
    protected final DynamicPropertyRegister<Integer> minerals = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<Integer> gas = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<ImmutableSet<IUnit>> mineralsAsUnits = new DynamicPropertyRegister<>();
    protected final StaticPropertyRegister<ImmutableSet<IUnit>> staticMineralsAsUnits = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<ImmutableSet<IUnit>> geysers = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Boolean> isIsland = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Boolean> isStartLocation = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<IPosition> position = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<ImmutableMap<IBaseLocation, Double>> groundDistanceToBases = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<ImmutableMap<IBaseLocation, Double>> airDistanceToBases = new StaticPropertyRegister<>();
    private final Set<StaticPropertyRegister<?>> toHash = ImmutableSet.of(position);

    @Override
    public boolean shouldBeUpdated(UpdateStrategy updateStrategy, int deltaUpdate, int depth) {
        return updateStrategy.shouldBeUpdated(this, deltaUpdate, depth);
    }

    @Override
    public Optional<Integer> minerals() {
        return getPropertyOnTimeLineStrategy(minerals);
    }

    @Override
    public Optional<Integer> gas() {
        return getPropertyOnTimeLineStrategy(gas);
    }

    @Override
    public Optional<Set<IUnit>> getMinerals() {
        return getPropertyOnTimeLineStrategyOnSet(mineralsAsUnits);
    }

    @Override
    public Optional<Set<IUnit>> getStaticMinerals() {
        return getPropertyOnTimeLineStrategyOnSet(staticMineralsAsUnits);
    }

    @Override
    public Optional<Set<IUnit>> getGeysers() {
        return getPropertyOnTimeLineStrategyOnSet(geysers);
    }

    @Override
    public Optional<Double> getGroundDistance(IBaseLocation other) {
        return getPropertyOnTimeLineStrategy(groundDistanceToBases, other);
    }

    @Override
    public Optional<Double> getAirDistance(IBaseLocation other) {
        return getPropertyOnTimeLineStrategy(airDistanceToBases, other);
    }

    @Override
    public Optional<Boolean> isIsland() {
        return getPropertyOnTimeLineStrategy(isIsland);
    }

    @Override
    public Optional<Boolean> isStartLocation() {
        return getPropertyOnTimeLineStrategy(isStartLocation);
    }

    @Override
    public Optional<IPosition> getPosition() {
        return getPropertyOnTimeLineStrategy(position);
    }


    @Override
    protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
        return toHash;
    }
}
