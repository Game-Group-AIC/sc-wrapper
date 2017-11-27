package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IChokePoint;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.Tuple;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public class ChokePoint extends AContainer implements IChokePoint, Serializable {
    protected final StaticPropertyRegister<Tuple<IRegion, IRegion>> regions = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Tuple<IPosition, IPosition>> sides = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Double> width = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<IPosition> position = new StaticPropertyRegister<>();
    private final Set<StaticPropertyRegister<?>> toHash = ImmutableSet.of(position);

    @Override
    public Optional<Tuple<IRegion, IRegion>> getRegions() {
        return getPropertyOnTimeLineStrategy(regions);
    }

    @Override
    public Optional<Tuple<IPosition, IPosition>> getSides() {
        return getPropertyOnTimeLineStrategy(sides);
    }

    @Override
    public Optional<Double> getWidth() {
        return getPropertyOnTimeLineStrategy(width);
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
