package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.StaticPropertyRegister;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public class Position extends AContainer implements IPosition, Serializable {
    protected final StaticPropertyRegister<ITilePosition> tilePosition = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Integer> x = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Integer> y = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<IRegion> region = new StaticPropertyRegister<>();
    private transient final Set<StaticPropertyRegister<?>> toHash = ImmutableSet.of(x, y);

    @Override
    public Optional<Integer> getX() {
        return getPropertyOnTimeLineStrategy(x);
    }

    @Override
    public Optional<Integer> getY() {
        return getPropertyOnTimeLineStrategy(y);
    }

    @Override
    public Optional<ITilePosition> getTilePosition() {
        return getPropertyOnTimeLineStrategy(tilePosition);
    }

    @Override
    public Optional<IRegion> getRegion() {
        return getPropertyOnTimeLineStrategy(region);
    }

    @Override
    protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
        return toHash;
    }
}
