package gg.fel.cvut.cz.data.updatable;

import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.facades.IUpdaterFacade;
import gg.fel.cvut.cz.facades.managers.UpdaterFacade;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBullet;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatableBullet extends Bullet implements IUpdatableContainer<WBullet, Bullet> {

  private final transient WBullet wrapped;

  @Override
  public WBullet getWrappedSCInstance() {
    return wrapped;
  }

  @Override
  public Stream<? extends AContainer> update(UpdaterFacade internalUpdaterFacade) {
    //TODO set fields + lock
    return null;
  }

  @Override
  public Bullet getContainer() {
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
