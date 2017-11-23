package gg.fel.cvut.cz.data.updatable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBaseLocation;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.data.AUpdateStrategy;
import gg.fel.cvut.cz.data.IExecuteUpdateStrategy;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.factories.MainWrappingFactory;
import lombok.AllArgsConstructor;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

@JsonIgnoreProperties({"wrapped", "lock"})
@AllArgsConstructor
public class UpdatableBaseLocation extends BaseLocation implements IUpdatableContainer<WBaseLocation, BaseLocation, UpdatableBaseLocation> {
    private final transient WBaseLocation wrapped;

    @Override
    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    @Override
    public WBaseLocation getWrappedSCInstance() {
        return wrapped;
    }

    @Override
    public BaseLocation getDataAccessContainer() {
        return this;
    }

    @Override
    public Stream<AUpdateStrategy<?, ?, ?>> callUpdateStrategy(MainWrappingFactory mainWrappingFactory, int frame, IExecuteUpdateStrategy<WBaseLocation, BaseLocation, UpdatableBaseLocation> updateStrategy) {
        return updateStrategy.executeUpdate(this, mainWrappingFactory, frame);
    }

    public void setMinerals(int minerals, int frame) {
        this.minerals.addProperty(minerals, frame);
    }

    public void setGas(int gas, int frame) {
        this.gas.addProperty(gas, frame);
    }

    public void setMinerals(ImmutableSet<IUnit> minerals, int frame) {
        this.mineralsAsUnits.addProperty(minerals, frame);
    }

    public void setStaticMinerals(ImmutableSet<IUnit> minerals, int frame) {
        this.staticMineralsAsUnits.addProperty(minerals, frame);
    }

    public void setGeysers(ImmutableSet<IUnit> geysers, int frame) {
        this.geysers.addProperty(geysers, frame);
    }

    public void setGroundDistance(ImmutableMap<IBaseLocation, Double> distances, int frame) {
        groundDistanceToBases.addProperty(distances, frame);
    }

    public void setAirDistance(ImmutableMap<IBaseLocation, Double> distances, int frame) {
        airDistanceToBases.addProperty(distances, frame);
    }

    public void setIsIsland(boolean isIsland, int frame) {
        this.isIsland.addProperty(isIsland, frame);
    }

    public void setIsStartLocation(boolean isStartLocation, int frame) {
        this.isStartLocation.addProperty(isStartLocation, frame);
    }

    public void setPosition(Position position, int frame) {
        this.position.addProperty(position, frame);
    }

}
