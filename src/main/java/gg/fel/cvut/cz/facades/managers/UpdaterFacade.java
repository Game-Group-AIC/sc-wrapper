package gg.fel.cvut.cz.facades.managers;

import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.readonly.BaseLocation;
import gg.fel.cvut.cz.data.readonly.Bullet;
import gg.fel.cvut.cz.data.readonly.ChokePoint;
import gg.fel.cvut.cz.data.readonly.Position;
import gg.fel.cvut.cz.data.readonly.Region;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import gg.fel.cvut.cz.data.updatable.UpdatableBullet;
import gg.fel.cvut.cz.facades.IUpdaterFacade;
import gg.fel.cvut.cz.facades.strategies.UpdateStrategy;
import gg.fel.cvut.cz.wrappers.WBullet;
import java.util.stream.Stream;

public class UpdaterFacade extends BWDataFacade<BWCounter> implements IUpdaterFacade {

  protected static final Object MONITOR = new Object();
  private final AUpdater<WBullet, Bullet, UpdatableBullet> bulletUpdater = new AUpdater<>(
      UpdatableBullet::new, this);
  //TODO implement updaters + methods

  public UpdaterFacade() {
    super(new BWCounter());
  }

  @Override
  public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy) {
    //lock counter
    synchronized (MONITOR) {
      if (bulletToUpdate.shouldBeUpdated(updateStrategy, this, 0)) {
        update(bulletToUpdate, updateStrategy, 0, getCurrentFrame());
      }
    }
  }

  @Override
  public int getDeltaUpdate(Bullet bullet) {
    return bulletUpdater.getDeltaUpdate(bullet, getCurrentFrame());
  }

  @Override
  public void update(BaseLocation baseLocation, UpdateStrategy updateStrategy) {

  }

  @Override
  public int getDeltaUpdate(BaseLocation baseLocation) {
    return 0;
  }

  @Override
  public void update(ChokePoint chokePoint, UpdateStrategy updateStrategy) {

  }

  @Override
  public int getDeltaUpdate(ChokePoint chokePoint) {
    return 0;
  }

  @Override
  public void update(Position position, UpdateStrategy updateStrategy) {

  }

  @Override
  public int getDeltaUpdate(Position position) {
    return 0;
  }

  @Override
  public void update(Region region, UpdateStrategy updateStrategy) {

  }

  @Override
  public int getDeltaUpdate(Region region) {
    return 0;
  }

  @Override
  public void update(TilePosition tilePosition, UpdateStrategy updateStrategy) {

  }

  @Override
  public int getDeltaUpdate(TilePosition tilePosition) {
    return 0;
  }

  /**
   * Update bullet
   */
  @Override
  public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {
    int newDepth = depth + 1;
    bulletUpdater.update(bulletToUpdate, currentFrame)
        .filter(o -> o.shouldBeUpdated(updateStrategy, this, newDepth))
        .forEach(o -> o.update(updateStrategy, this, newDepth, currentFrame));
  }

  @Override
  public void update(Position position, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public void update(TilePosition tilePosition, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public void update(BaseLocation baseLocation, UpdateStrategy updateStrategy, int depth,
      int currentFrame) {

  }

  @Override
  public Stream<? extends AContainer> getAllContainers() {
    //TODO
    return Stream.empty();
//        return Stream.concat(bulletUpdater.getAllContainers(), );
  }


  @Override
  public void increaseClocks() {
    //lock update
    synchronized (MONITOR) {
      bwCounter.increaseClocks();
    }
  }

  @Override
  public int getCurrentFrame() {
    return bwCounter.getCurrentFrame();
  }
}
