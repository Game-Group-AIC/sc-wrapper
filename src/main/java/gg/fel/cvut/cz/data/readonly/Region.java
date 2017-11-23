package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.*;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.StaticPropertyRegister;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public class Region extends AContainer implements IRegion, Serializable {
    protected final DynamicPropertyRegister<ImmutableSet<IUnit>> units = new DynamicPropertyRegister<>();
    protected final StaticPropertyRegister<ImmutableSet<IChokePoint>> chokePoints = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<ImmutableSet<IBaseLocation>> baseLocations = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<ImmutableSet<IRegion>> reachableRegions = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<IPosition> position = new StaticPropertyRegister<>();
    private final Set<StaticPropertyRegister<?>> toHash = ImmutableSet.of(position);

    @Override
    public Optional<IPosition> getPosition() {
        return getPropertyOnTimeLineStrategy(position);
    }

    @Override
    public Optional<Set<IUnit>> getUnits() {
        return getPropertyOnTimeLineStrategyOnSet(units);
    }

    @Override
    public Optional<Set<IChokePoint>> getChokePoints() {
        return getPropertyOnTimeLineStrategyOnSet(chokePoints);
    }

    @Override
    public Optional<Set<IBaseLocation>> getBaseLocations() {
        return getPropertyOnTimeLineStrategyOnSet(baseLocations);
    }

    @Override
    public Optional<Set<IRegion>> getReachableRegions() {
        return getPropertyOnTimeLineStrategyOnSet(reachableRegions);
    }

    @Override
    protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
        return toHash;
    }
}
