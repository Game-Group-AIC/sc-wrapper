package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.readonly.*;
import gg.fel.cvut.cz.data.updatable.UpdatableBullet;
import gg.fel.cvut.cz.wrappers.WBullet;

public class UpdaterFacade extends BWDataFacade<BWCounter> implements IUpdaterFacade, IInternalUpdaterFacade {
    private static final Object MONITOR = new Object();
    private final AUpdater<WBullet, Bullet, UpdatableBullet> bulletUpdater = new AUpdater<>(UpdatableBullet::new, this);
    //TODO implement updaters + methods

    public UpdaterFacade() {
        super(new BWCounter());
    }

    @Override
    public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy) {
        //lock counter
        synchronized (MONITOR) {
            update(bulletToUpdate, updateStrategy, 0, getCurrentFrame());
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
     *
     * @param bulletToUpdate
     * @param updateStrategy
     */
    @Override
    public void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy, int depth, int currentFrame) {
        if (bulletToUpdate.shouldBeUpdated(updateStrategy, this, depth)) {

            //TODO - update whole tree
            bulletUpdater.update(bulletToUpdate, currentFrame);
        }
    }

    @Override
    public void update(Position position, UpdateStrategy updateStrategy, int depth, int currentFrame) {

    }

    @Override
    public void update(TilePosition tilePosition, UpdateStrategy updateStrategy, int depth, int currentFrame) {

    }

    @Override
    public void update(BaseLocation baseLocation, UpdateStrategy updateStrategy, int depth, int currentFrame) {

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
