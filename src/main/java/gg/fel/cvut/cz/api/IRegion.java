package gg.fel.cvut.cz.api;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

/**
 * IRegion objects are created by Starcraft: Broodwar to contain several tiles with the same properties, and create a node in pathfinding and other algorithms. Regions may not contain detailed information, but have a sufficient amount of data to identify general chokepoints, accessibility to neighboring terrain, be used in general pathing algorithms, and used as nodes to rally units to. Most parameters that are available are explicitly assigned by Broodwar itself. See also IGame::getAllRegions, IGame::getRegionAt, UnitInterface::getRegion
 */
public interface IRegion extends IAbstractPoint, InGameInterface, Serializable {

    /**
     * Retrieves the center of the region. This position is used as the node of the region. Returns A IPosition indicating the center location of the IRegion, in pixels.
     */
    Optional<IPosition> getPosition();

    /**
     * Retrieves a IUnit set containing all the units that are in this region. Also has the ability to filter the units before the creation of the IUnit set. Parameters pred (optional) If this parameter is used, it is a UnitFilter or function predicate that will retrieve only the units whose attributes match the given criteria. If omitted, then a default value of nullptr is used, in which case there is no filter. Returns A Unitset containing all units in this region that have met the requirements of pred. See also UnitFilter
     */
    Optional<Set<IUnit>> getUnits();

    Optional<Set<IChokePoint>> getChokePoints();

    Optional<Set<IBaseLocation>> getBaseLocations();

    default Optional<Boolean> isReachable(IRegion region) {
        return getReachableRegions().map(iRegions -> iRegions.contains(region));
    }

    Optional<Set<IRegion>> getReachableRegions();

    @Override
    default Optional<IRegion> getRegion() {
        return Optional.of(this);
    }
}
