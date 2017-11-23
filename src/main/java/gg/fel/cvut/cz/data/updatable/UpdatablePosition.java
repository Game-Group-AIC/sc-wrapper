package gg.fel.cvut.cz.data.updatable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import gg.fel.cvut.cz.api.IRegion;
import gg.fel.cvut.cz.api.ITilePosition;
import gg.fel.cvut.cz.data.AUpdateStrategy;
import gg.fel.cvut.cz.data.IExecuteUpdateStrategy;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.factories.MainWrappingFactory;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

@AllArgsConstructor
public class UpdatablePosition extends Position implements IUpdatableContainer<WPosition, Position, UpdatablePosition> {
    private final transient WPosition wrapped;

    @Override
    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    @Override
    public WPosition getWrappedSCInstance() {
        return wrapped;
    }

    @Override
    public Position getDataAccessContainer() {
        return this;
    }

    @Override
    public Stream<AUpdateStrategy<?, ?, ?>> callUpdateStrategy(MainWrappingFactory mainWrappingFactory, int frame, IExecuteUpdateStrategy<WPosition, Position, UpdatablePosition> updateStrategy) {
        return updateStrategy.executeUpdate(this, mainWrappingFactory, frame);
    }

    public void setX(int x, int frame) {
        this.x.addProperty(x, frame);
    }

    public void setY(int y, int frame) {
        this.y.addProperty(y, frame);
    }

    public void setTilePosition(TilePosition tilePosition, int frame) {
        this.tilePosition.addProperty(tilePosition, frame);
    }

    public void setRegion(IRegion region, int frame) {
        this.region.addProperty(region, frame);
    }
}
