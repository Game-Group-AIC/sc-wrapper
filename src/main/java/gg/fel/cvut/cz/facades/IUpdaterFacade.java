package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.counters.IBWCounter;
import gg.fel.cvut.cz.data.readonly.*;

/**
 * Contract of UpdaterFacade (updatable methods)
 */
public interface IUpdaterFacade extends IBWCounter {

    /**
     * Update bullet
     *
     * @param bulletToUpdate
     * @param updateStrategy
     */
    void update(Bullet bulletToUpdate, UpdateStrategy updateStrategy);

    /**
     * Update baseLocation
     *
     * @param baseLocation
     * @param updateStrategy
     */
    void update(BaseLocation baseLocation, UpdateStrategy updateStrategy);

    /**
     * Update chokePoint
     *
     * @param chokePoint
     * @param updateStrategy
     */
    void update(ChokePoint chokePoint, UpdateStrategy updateStrategy);

    /**
     * Update position
     *
     * @param position
     * @param updateStrategy
     */
    void update(Position position, UpdateStrategy updateStrategy);

    /**
     * Update region
     *
     * @param region
     * @param updateStrategy
     */
    void update(Region region, UpdateStrategy updateStrategy);

    /**
     * Update tilePosition
     *
     * @param tilePosition
     * @param updateStrategy
     */
    void update(TilePosition tilePosition, UpdateStrategy updateStrategy);

}
