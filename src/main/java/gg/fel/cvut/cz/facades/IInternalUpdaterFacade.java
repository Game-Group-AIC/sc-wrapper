package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.data.readonly.*;

/**
 * Contract of InternalUpdaterFacade (internal methods)
 */
public interface IInternalUpdaterFacade {

    /**
     * Returns delta of current time and last updated. If object has not been updated yet max integer is returned.
     * For object updated after current frame, zero is returned.
     *
     * @param bullet
     * @return
     */
    int getDeltaUpdate(Bullet bullet);

    /**
     * Returns delta of current time and last updated. If object has not been updated yet max integer is returned.
     * For object updated after current frame, zero is returned.
     *
     * @param baseLocation
     * @return
     */
    int getDeltaUpdate(BaseLocation baseLocation);

    /**
     * Returns delta of current time and last updated. If object has not been updated yet max integer is returned.
     * For object updated after current frame, zero is returned.
     *
     * @param chokePoint
     * @return
     */
    int getDeltaUpdate(ChokePoint chokePoint);

    /**
     * Returns delta of current time and last updated. If object has not been updated yet max integer is returned.
     * For object updated after current frame, zero is returned.
     *
     * @param position
     * @return
     */
    int getDeltaUpdate(Position position);

    /**
     * Returns delta of current time and last updated. If object has not been updated yet max integer is returned.
     * For object updated after current frame, zero is returned.
     *
     * @param region
     * @return
     */
    int getDeltaUpdate(Region region);

    /**
     * Returns delta of current time and last updated. If object has not been updated yet max integer is returned.
     * For object updated after current frame, zero is returned.
     *
     * @param tilePosition
     * @return
     */
    int getDeltaUpdate(TilePosition tilePosition);

    /**
     * Update bullet
     *
     * @param bulletToUpdate
     * @param updateStrategy
     */
    void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy, int depth, int currentFrame);

    /**
     * Update position
     *
     * @param position
     * @param updateStrategy
     */
    void update(Position position, UpdateStrategy updateStrategy, int depth, int currentFrame);

    /**
     * Update baseLocation
     *
     * @param tilePosition
     * @param updateStrategy
     */
    void update(TilePosition tilePosition, UpdateStrategy updateStrategy, int depth, int currentFrame);

    /**
     * Update baseLocation
     *
     * @param baseLocation
     * @param updateStrategy
     */
    void update(BaseLocation baseLocation, UpdateStrategy updateStrategy, int depth, int currentFrame);

}
