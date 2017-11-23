package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.TilePosition;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

/**
 * Build Tiles - each build tile is a 4x4 square of walk tiles, or a 32x32 square of pixels.
 * These are called build tiles because buildability data is available at this resolution, and correspond to the tiles seen in game.
 * For example, a Command Center occupies an area of 4x3 build tiles.
 */
@JsonDeserialize(as = TilePosition.class)
public interface ITilePosition extends IAbstractPoint, InGameInterface, Serializable {
    int SIZE_IN_PIXELS = 32;

    //TODO distance computation - use in other classes as well
    //TODO neighbouring tiles - to add other related methods - can be player dependant
    //TODO is accessible
    //TODO find path using A* with air distance heuristic? - it could be cool to plan various paths (e.g. one maximizing enemy avoidance, one considering walls,...)
//    /**
//     * Checks if the given tile position has Zerg creep on it. Parameters tileX The x tile coordinate to check. tileY The y tile coordinate to check. Return values true If the given tile has creep on it. false If the given tile does not have creep, or if it is concealed by the fog of war.
//     */
//    boolean hasCreep(ITilePosition position);
//
//    /**
//     * Checks if the given tile position if powered by an owned Protoss Pylon for an optional unit type. Parameters tileX The x tile coordinate to check. tileY The y tile coordinate to check. unitType (optional) Checks if the given IUnitType will be powered if placed at the given tile position. If omitted, then only the immediate tile position is checked for power, and the function will assume that the location requires power for any unit type. Return values true if the type at the given tile position will receive power. false if the type will be unpowered at the given tile position.
//     */
//    boolean hasPower(ITilePosition position);
//
//    /**
//     * Checks if the given unit type can be built at the given build tile position. This function checks for creep, power, and resource distance requirements in addition to the tiles' buildability and possible units obstructing the build location. Note If the type is an addon and a builer is provided, then the location of the addon will be placed 4 tiles to the right and 1 tile down from the given position. If the builder is not given, then the check for the addon will be conducted at position. Parameters position Indicates the tile position that the top left corner of the structure is intended to go. type The IUnitType to check for. builder (optional) The intended unit that will build the structure. If specified, then this function will also check if there is a path to the build site and exclude the builder from the set of units that may be blocking the build site. checkExplored (optional) If this parameter is true, it will also check if the target position has been explored by the current player. This value is false by default, ignoring the explored state of the build site. Returns true indicating that the structure can be placed at the given tile position, and false if something may be obstructing the build location.
//     */
//
//    boolean canBuildHere(ITilePosition position, IUnitType type);

    Optional<Integer> getGroundHeight();

    Optional<Set<IUnit>> getUnitsOnTile();

    Optional<IPosition> getPosition();

    default Optional<ITilePosition> getTilePosition() {
        return Optional.of(this);
    }
}