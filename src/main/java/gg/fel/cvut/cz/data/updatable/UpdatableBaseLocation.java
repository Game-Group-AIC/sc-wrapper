package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.facades.IUpdaterFacade;
import gg.fel.cvut.cz.facades.managers.UpdaterFacade;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBaseLocation;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatableBaseLocation extends BaseLocation implements
    IUpdatableContainer<WBaseLocation, BaseLocation> {

  private final transient WBaseLocation wrapped;

  @Override
  public WBaseLocation getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public Stream<? extends AContainer> update(UpdaterFacade internalUpdaterFacade) {
    //TODO set fields + lock
    return null;
  }

  @Override
  public BaseLocation getContainer() {
    return this;
  }

  @Override
  public boolean shouldBeUpdated(UpdateStrategy updateStrategy, IUpdaterFacade updaterFacade,
      int depth) {
    return updateStrategy.shouldBeUpdated(this, updaterFacade.getDeltaUpdate(this), depth);
  }

  @Override
  public void update(UpdateStrategy updateStrategy, IUpdaterFacade updaterFacade, int depth,
      int currentFrame) {
    updaterFacade.update(this, updateStrategy, depth, currentFrame);
  }
}
