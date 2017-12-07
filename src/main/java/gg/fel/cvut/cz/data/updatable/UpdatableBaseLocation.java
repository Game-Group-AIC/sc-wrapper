package gg.fel.cvut.cz.data.updatable;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.facades.IUpdateManager;
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
    super(bwReplayCounter);
    this.wrapped = wrapped;
    x.addProperty(wrapped.getX(), 0);
    y.addProperty(wrapped.getY(), 0);
    toHash = ImmutableSet.of(x, y);
  }

  @Override
  public WBaseLocation getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public Stream<? extends AContainer> update(UpdateManager internalUpdaterFacade) {
    try {
      lock.writeLock().lock();

      int currentFrame = bwCounter.getCurrentFrame();

      //updates
      minerals.addProperty(wrapped.getScInstance().minerals(), currentFrame);
      gas.addProperty(wrapped.getScInstance().gas(), currentFrame);
      mineralsAsUnits.addProperty(ImmutableSet.copyOf(wrapped.getScInstance().getMinerals().stream()
          .map(WUnit::getOrCreateWrapper)
          .map(internalUpdaterFacade::getDataContainer)
          .filter(Optional::isPresent)
          .map(Optional::get)
          .collect(Collectors.toSet())), currentFrame);
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

      //collect containers
      return Stream.of(mineralsAsUnits.getValueInFrame(currentFrame),
          staticMineralsAsUnits.getValueInFrame(currentFrame),
          geysers.getValueInFrame(currentFrame),
          position.getValueInFrame(currentFrame).map(ImmutableSet::of))
          .filter(Optional::isPresent)
          .map(Optional::get)
          .flatMap(Collection::stream);
    } finally {
      lock.writeLock().unlock();
    }
  }

  @Override
  public BaseLocation getContainer() {
    return this;
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, IUpdateManager updaterFacade,
      int depth) {
    return updateStrategy.shouldBeUpdated(this, updaterFacade.getDeltaUpdate(this), depth);
  }

  @Override
  public void update(UpdateStrategy updateStrategy, IUpdateManager updaterFacade, int depth,
      int currentFrame) {
    updaterFacade.update(this, updateStrategy, depth, currentFrame);
  }
}
