package gg.fel.cvut.cz.data.access;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.StaticPropertyRegister;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TilePosition extends AContainer implements ITilePosition, Serializable {
    protected final DynamicPropertyRegister<HashSet<IUnit>> units = new DynamicPropertyRegister<>();
    protected final StaticPropertyRegister<IPosition> position = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Integer> groundHeight = new StaticPropertyRegister<>();
    private final Set<StaticPropertyRegister<?>> toHash = ImmutableSet.of(position);

    @Override
    public Optional<Integer> getGroundHeight() {
        return getPropertyOnTimeLineStrategy(groundHeight);
    }

    @Override
    public Optional<Set<IUnit>> getUnitsOnTile() {
        return getPropertyOnTimeLineStrategyOnSet(units);
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
