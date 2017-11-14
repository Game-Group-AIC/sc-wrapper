package gg.fel.cvut.cz.api;

/**
 * The Race object is used to get information about a particular race. For example, the default worker and supply provider UnitType. As you should already know, Starcraft has three races: Terran , Protoss , and Zerg . See also UnitType::getRace, PlayerInterface::getRace, Races
 */
public interface Race {
    /**
     * Retrieves the default worker type for this Race. Note In Starcraft, workers are the units that are used to construct structures. Returns UnitType of the worker that this race uses.
     */
    UnitType getWorker();

    /**
     * Retrieves the default resource center UnitType that is used to create expansions for this Race. Note In Starcraft, the center is the very first structure of the Race's technology tree. Also known as its base of operations or resource depot. Returns UnitType of the center that this race uses.
     */
    UnitType getCenter();

    /**
     * Retrieves the default structure UnitType for this Race that is used to harvest gas from Vespene Geysers. Note In Starcraft, you must first construct a structure over a Vespene Geyser in order to begin harvesting Vespene Gas. Returns UnitType of the structure used to harvest gas.
     */
    UnitType getRefinery();

    /**
     * Retrieves the default transport UnitType for this race that is used to transport ground units across the map. Note In Starcraft, transports will allow you to carry ground units over unpassable terrain. Returns UnitType for transportation.
     */
    UnitType getTransport();

    /**
     * Retrieves the default supply provider UnitType for this race that is used to construct units. Note In Starcraft, training, morphing, or warping in units requires that the player has sufficient supply available for their Race. Returns UnitType that provides the player with supply.
     */
    UnitType getSupplyProvider();

}
