package gg.fel.cvut.cz.data.updatable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.data.AUpdateStrategy;
import gg.fel.cvut.cz.data.IExecuteUpdateStrategy;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.wrappers.WTilePosition;
import gg.fel.cvut.cz.wrappers.factories.MainWrappingFactory;
import lombok.AllArgsConstructor;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

@AllArgsConstructor
public class UpdatableTilePosition extends TilePosition implements IUpdatableContainer<WTilePosition, TilePosition, UpdatableTilePosition> {
    private transient final WTilePosition wrapped;

    @Override
    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    @Override
    public WTilePosition getWrappedSCInstance() {
        return wrapped;
    }

    @Override
    public gg.fel.cvut.cz.data.readonly.TilePosition getDataAccessContainer() {
        return this;
    }

    @Override
    public Stream<AUpdateStrategy<?, ?, ?>> callUpdateStrategy(MainWrappingFactory mainWrappingFactory, int frame, IExecuteUpdateStrategy<WTilePosition, TilePosition, UpdatableTilePosition> updateStrategy) {
        return updateStrategy.executeUpdate(this, mainWrappingFactory, frame);
    }

    public void setGroundHeight(int groundHeight, int frame) {
        this.groundHeight.addProperty(groundHeight, frame);
    }

    public void setUnitsOnTile(ImmutableSet<IUnit> units, int frame) {
        this.units.addProperty(units, frame);
    }

    public void setPosition(Position position, int frame) {
        this.position.addProperty(position, frame);
    }
}
