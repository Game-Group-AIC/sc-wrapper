package gg.fel.cvut.cz.api;

import java.util.List;

/**
 * IRegion objects are created by Starcraft: Broodwar to contain several tiles with the same properties, and create a node in pathfinding and other algorithms. Regions may not contain detailed information, but have a sufficient amount of data to identify general chokepoints, accessibility to neighboring terrain, be used in general pathing algorithms, and used as nodes to rally units to. Most parameters that are available are explicitly assigned by Broodwar itself. See also IGame::getAllRegions, IGame::getRegionAt, UnitInterface::getRegion
 */
public interface IRegion extends IAbstractPoint {

    /**
     * Retrieves a unique identifier for this region. Note This identifier is explicitly assigned by Broodwar. Returns An integer that represents this region. See also IGame::getRegion
     */
    int getID();

    /**
     * Retrieves the center of the region. This position is used as the node of the region. Returns A IPosition indicating the center location of the IRegion, in pixels.
     */
    IPosition getCenter();

    /**
     * Checks if this region is part of higher ground. Higher ground may be used in strategic placement of units and structures. Returns true if this region is part of strategic higher ground, and false otherwise.
     */
    boolean isHigherGround();

    /**
     * Retrieves a value that represents the strategic advantage of this region relative to other regions. A value of 2 may indicate a possible choke point, and a value of 3 indicates a signficant strategic position. Note This value is explicitly assigned by Broodwar. Returns An integer indicating this region's strategic potential.
     */
    int getDefensePriority();

    /**
     * Retrieves the state of accessibility of the region. The region is considered accessible if it can be accessed by ground units. Returns true if ground units can traverse this region, and false if the tiles in this region are inaccessible or unwalkable.
     */
    boolean isAccessible();

    /**
     * Retrieves the set of neighbor Regions that this one is connected to. Returns A reference to a Regionset containing the neighboring Regions.
     */
    List<IRegion> getNeighbors();

    /**
     * Retrieves the approximate left boundary of the region. Returns The x coordinate, in pixels, of the approximate left boundary of the region.
     */
    int getBoundsLeft();

    /**
     * Retrieves the approximate top boundary of the region. Returns The y coordinate, in pixels, of the approximate top boundary of the region.
     */
    int getBoundsTop();

    /**
     * Retrieves the approximate right boundary of the region. Returns The x coordinate, in pixels, of the approximate right boundary of the region.
     */
    int getBoundsRight();

    /**
     * Retrieves the approximate bottom boundary of the region. Returns The y coordinate, in pixels, of the approximate bottom boundary of the region.
     */
    int getBoundsBottom();

    /**
     * Retrieves the closest accessible neighbor region. Returns The closest IRegion that is accessible.
     */
    IRegion getClosestAccessibleRegion();

    /**
     * Retrieves the closest inaccessible neighbor region. Returns The closest IRegion that is inaccessible.
     */
    IRegion getClosestInaccessibleRegion();

    /**
     * Retrieves the center-to-center distance between two regions. Parameters other The target IRegion to calculate distance to. Returns The integer distance from this IRegion to other.
     */
    int getDistance(IRegion other);

    /**
     * Retrieves a IUnit set containing all the units that are in this region. Also has the ability to filter the units before the creation of the IUnit set. Parameters pred (optional) If this parameter is used, it is a UnitFilter or function predicate that will retrieve only the units whose attributes match the given criteria. If omitted, then a default value of nullptr is used, in which case there is no filter. Returns A Unitset containing all units in this region that have met the requirements of pred. See also UnitFilter
     */
    List<IUnit> getUnits();

    List<IChokePoint> getChokePoints();

    List<IBaseLocation> getBaseLocations();

    boolean isReachable(IRegion region);

    List<IRegion> getReachableRegions();

    @Override
    default IRegion getRegion() {
        return this;
    }
}
