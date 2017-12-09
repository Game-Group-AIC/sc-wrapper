package gg.fel.cvut.cz.data.updatable;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.facades.managers.UpdateManager;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import gg.fel.cvut.cz.wrappers.WPosition;
import gg.fel.cvut.cz.wrappers.WUnit;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdatableBaseLocation extends BaseLocation implements
    IUpdatableContainer<WBaseLocation, BaseLocation> {

  private final transient WBaseLocation wrapped;

  public UpdatableBaseLocation(BWReplayCounter bwReplayCounter, WBaseLocation wrapped) {
    super(bwReplayCounter, wrapped.getX(), wrapped.getY());
    this.wrapped = wrapped;
  }

  @Override
  public WBaseLocation getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public void update(UpdateManager internalUpdaterFacade,
      int currentFrame) {
    try {
      lock.writeLock().lock();

      //updates
      minerals.addProperty(wrapped.getScInstance().minerals(), currentFrame);
      gas.addProperty(wrapped.getScInstance().gas(), currentFrame);
      if (staticMineralsAsUnits.propertyHasNotBeenAdded()) {
        staticMineralsAsUnits
            .addProperty(ImmutableSet.copyOf(wrapped.getScInstance().getStaticMinerals().stream()
                .map(WUnit::getOrCreateWrapper)
                .map(internalUpdaterFacade::getDataContainer)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet())), 0);
      }
      if (geysers.propertyHasNotBeenAdded()) {
        geysers.addProperty(ImmutableSet.copyOf(wrapped.getScInstance().getGeysers().stream()
            .map(WUnit::getOrCreateWrapper)
            .map(internalUpdaterFacade::getDataContainer)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toSet())), 0);
      }
      if (isIsland.propertyHasNotBeenAdded()) {
        isIsland.addProperty(wrapped.getScInstance().isIsland(), 0);
      }
      if (isStartLocation.propertyHasNotBeenAdded()) {
        isStartLocation.addProperty(wrapped.getScInstance().isStartLocation(), 0);
      }
      if (position.propertyHasNotBeenAdded()) {
        Optional<Position> position = internalUpdaterFacade
            .getDataContainer(WPosition.getOrCreateWrapper(wrapped.getScInstance().getPosition()));
        position.ifPresent(p -> this.position.addProperty(p, 0));
      }

      //updated in frame
      updatedInFrame = currentFrame;
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public void update(UpdateManager updateManager, UpdateStrategy updateStrategy) {
    updateManager.update(this, updateStrategy);
  }

  @Override
  public BaseLocation getContainer() {
    return this;
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, int depth, int currentFrame) {
    return updateStrategy.shouldBeUpdated(this, deltaOfUpdate(currentFrame), depth);
  }

  @Override
  public Stream<? extends AContainer> getReferencedContainers(int currentFrame) {
    return Stream.of(staticMineralsAsUnits.getValueInFrame(currentFrame),
        geysers.getValueInFrame(currentFrame),
        position.getValueInFrame(currentFrame).map(ImmutableSet::of))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .flatMap(Collection::stream);
  }

}
