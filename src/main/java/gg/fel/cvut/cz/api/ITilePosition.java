package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.TilePosition;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Build Tiles: each build tile is
 *
 * - a 4x4 square of walk tiles,
 * - or a 32x32 square of pixels.
 *
 * These are called build tiles because build-ability data
 * is available at this resolution, and correspond to the tiles seen in game.
 *
 * For example, a Command Center occupies an area of 4x3 build tiles.
 */
@JsonDeserialize(as = TilePosition.class)
public interface ITilePosition extends IAbstractPoint, InGameInterface, Serializable {
  int SIZE_IN_PIXELS = 32;
  int SIZE_IN_WALK_TILES = 4;

  /**
   * Is this tile visible by any of the player's or player's allies units?
   * @return bool, or empty if impossible to know
   */
  default Optional<Boolean> isVisible(IPlayer currentPlayer) {
    return getPosition().flatMap(
        position_tile -> currentPlayer.getPlayerOrAlliedUnits()
            .map(unitsThatCanLook -> unitsThatCanLook

                // make sure we will be able to get radius and position
                .filter(iUnit -> iUnit.getTilePosition().isPresent())
                .filter(iUnit -> iUnit.getSightRange().isPresent())

                // find first unit that can see this tile
                .anyMatch(iUnit -> {
                  ITilePosition position_unit = iUnit.getTilePosition().get();
                  Integer unit_sight_radius = iUnit.getSightRange().get();

                  boolean isFlyer = iUnit.getType().get().isFlyer().get();
                  Integer height_tile = getGroundHeight().get();
                  Integer height_unit = position_unit.getGroundHeight().get();

                  // either it's flyer XOR it is ground unit it must be on the same level.
                  boolean canSee = isFlyer ^ (height_unit >= height_tile);
                  double distance = position_tile.getApproxDistance(position_unit).get();

                  return (canSee && distance < unit_sight_radius);
                })
            ));
  }

  /**
   * Checks if the given tile position has Zerg creep on it.
   *
   * Return values
   * - true if the tile has creep on it,
   * - false if the given tile does not have creep,
   */
  boolean hasCreep();

  /**
   * Checks if the given tile position has Zerg creep on it.
   *
   * Zerg can build on any creep, including its Zerg opponent's creep.
   *
   * Return values
   * - true if the tile has creep on it,
   * - false if the given tile does not have creep,
   * - empty if it is concealed by the fog of war for the user
   */
  default Optional<Boolean> hasCreep(IPlayer currentPlayer) {
    return isVisible(currentPlayer)
        .map(iCurrentPlayer -> hasCreep());
  }

  /**
   * Checks if the given tile position if powered by an owned Protoss Pylon
   */
  Optional<Boolean> hasPower(IPlayer currentPlayer);

  /**
   * Checks if the given unit type can be built at the given build tile position.
   *
   * This function checks for creep, power, and resource distance requirements in addition
   * to the tiles' buildability and possible units obstructing the build location.
   *
   * Note: If the type is an addon and a builer is provided, then the location of the addon
   * will be placed 4 tiles to the right
   * and 1 tile down from the given position. If the builder is not given, then the check for the
   * addon will be conducted at position. Parameters position Indicates the tile position that the
   * top left corner of the structure is intended to go. type The IUnitType to check for. builder
   * (optional) The intended unit that will build the structure. If specified, then this function
   * will also check if there is a path to the build site and exclude the builder from the set of
   * units that may be blocking the build site. checkExplored (optional) If this parameter is true,
   * it will also check if the target position has been explored by the current player. This value
   * is false by default, ignoring the explored state of the build site. Returns true indicating
   * that the structure can be placed at the given tile position, and false if something may be
   * obstructing the build location.
   */
  Optional<Boolean> canBuildHere(IUnitType type);
  // todo: ^

  /**
   * Checks if the given unit type can be built at the given build tile position.
   *
   * This function checks for creep, power, and resource distance requirements in addition
   * to the tiles' buildability and possible units obstructing the build location.
   *
   * Note: If the type is an addon and a builer is provided, then the location of the addon
   * will be placed 4 tiles to the right
   * and 1 tile down from the given position. If the builder is not given, then the check for the
   * addon will be conducted at position. Parameters position Indicates the tile position that the
   * top left corner of the structure is intended to go. type The IUnitType to check for. builder
   * (optional) The intended unit that will build the structure. If specified, then this function
   * will also check if there is a path to the build site and exclude the builder from the set of
   * units that may be blocking the build site. checkExplored (optional) If this parameter is true,
   * it will also check if the target position has been explored by the current player. This value
   * is false by default, ignoring the explored state of the build site. Returns true indicating
   * that the structure can be placed at the given tile position, and false if something may be
   * obstructing the build location.
   */
  Optional<Boolean> canBuildHere(IPlayer player, IUnitType type);
  // todo: ^

//  Optional<Boolean> isAccessible(IUnitType type);
  // todo ^
  // Some condititions:
  // - fly / ground units
  // - building prevention (wallin)
  // - if every other tile is not accessible

//  Optional<List<ITilePosition>> findPath(ITilePosition destinationTile);
  // todo ^
  // A* algo with air distance heuristic?
  // it could be cool to plan various paths (e.g. one maximizing enemy avoidance, one considering walls,...)

//  Optional<List<ITilePosition>> findPathAvoidTiles(ITilePosition destinationTile, List<ITilePosition> avoidTiles);
  // todo ^
  // one day, find path with list of excluded tiles

//  Optional<List<ITilePosition>> findPathThroughCheckpoints(List<ITilePosition> checkpointTiles);
  // todo ^
  // last tile is destination

  Optional<Stream<ITilePosition>> getNeighbours();
  // todo ^


  Optional<Integer> getGroundHeight();

  Optional<Stream<IUnit>> getUnitsOnTile();

  Optional<IPosition> getPosition();

  default Optional<ITilePosition> getTilePosition() {
    return Optional.of(this);
  }
}
