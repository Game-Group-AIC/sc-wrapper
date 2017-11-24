package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableList;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.facades.UpdateStrategy;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Position extends AContainer implements IPosition, Serializable {
    protected final StaticPropertyRegister<ITilePosition> tilePosition = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Integer> x = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<Integer> y = new StaticPropertyRegister<>();
    protected final StaticPropertyRegister<IRegion> region = new StaticPropertyRegister<>();
    private final List<StaticPropertyRegister<?>> toHash = ImmutableList.of(x, y);

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
    public boolean shouldBeUpdated(UpdateStrategy updateStrategy, int deltaUpdate, int depth) {
        return updateStrategy.shouldBeUpdated(this, deltaUpdate, depth);
    }

    @Override
    protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
        return new HashSet<>(toHash);
    }
}
