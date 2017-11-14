package gg.fel.cvut.cz.api;

import java.util.List;

/**
 * Region objects are created by Starcraft: Broodwar to contain several tiles with the same properties, and create a node in pathfinding and other algorithms. Regions may not contain detailed information, but have a sufficient amount of data to identify general chokepoints, accessibility to neighboring terrain, be used in general pathing algorithms, and used as nodes to rally units to. Most parameters that are available are explicitly assigned by Broodwar itself. See also Game::getAllRegions, Game::getRegionAt, UnitInterface::getRegion
 */
public interface Region extends AbstractPoint {

    /**
     * Retrieves a unique identifier for this region. Note This identifier is explicitly assigned by Broodwar. Returns An integer that represents this region. See also Game::getRegion
     */
    int getID();

    /**
     * Retrieves a unique identifier for a group of regions that are all connected and accessible by each other. That is, all accessible regions will have the same group ID. This function is generally used to check if a path is available between two points in constant time. Note This identifier is explicitly assigned by Broodwar. Returns An integer that represents the group of regions that this one is attached to.
     */
    int getRegionGroupID();

    /**
     * Retrieves the center of the region. This position is used as the node of the region. Returns A Position indicating the center location of the Region, in pixels.
     */
    Position getCenter();

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
    List<Region> getNeighbors();

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
     * Retrieves the closest accessible neighbor region. Returns The closest Region that is accessible.
     */
    Region getClosestAccessibleRegion();

    /**
     * Retrieves the closest inaccessible neighbor region. Returns The closest Region that is inaccessible.
     */
    Region getClosestInaccessibleRegion();

    /**
     * Retrieves the center-to-center distance between two regions. Parameters other The target Region to calculate distance to. Returns The integer distance from this Region to other.
     */
    int getDistance(Region other);

    /**
     * Retrieves a Unit set containing all the units that are in this region. Also has the ability to filter the units before the creation of the Unit set. Parameters pred (optional) If this parameter is used, it is a UnitFilter or function predicate that will retrieve only the units whose attributes match the given criteria. If omitted, then a default value of nullptr is used, in which case there is no filter. Returns A Unitset containing all units in this region that have met the requirements of pred. See also UnitFilter
     */
    List<Unit> getUnits();

    Polygon getPolygon();

    List<Chokepoint> getChokepoints();

    List<BaseLocation> getBaseLocations();

    boolean isReachable(Region region);

    List<Region> getReachableRegions();

    int getMaxDistance();

}
