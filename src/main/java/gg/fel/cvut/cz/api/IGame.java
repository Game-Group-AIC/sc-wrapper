package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.GameTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

/**
 * The abstract IGame class is implemented by BWAPI and is the primary means of obtaining all game
 * state information from Starcraft Broodwar. IGame state information includes all units, resources,
 * players, forces, bullets, terrain, fog of war, regions, etc.
 */
public interface IGame extends InGameInterface, Serializable {

  Optional<IPlayer> getSelf();

  /**
   * Retrieves the set of all players in the match. This includes the neutral player, which owns all
   * the resources and critters by default. Returns Playerset containing all players in the game.
   */
  Optional<Set<IPlayer>> getPlayers();

  /**
   * Retrieves the GameTypeEnum of the current game. Returns GameTypeEnum indicating the rules of
   * the match. See also GameTypeEnum
   */
  Optional<GameTypeEnum> getGameType();

  /**
   * Retrieves the number of logical frames since the beginning of the match. If the game is paused,
   * then getFrameCount will not increase. Returns Number of logical frames that have elapsed since
   * the game started as an integer.
   */
  Optional<Integer> getFrameCount();

  /**
   * Retrieves the logical frame rate of the game in frames per second (FPS). Example:
   * BWAPI::Broodwar->setLocalSpeed(0); // Log and display the best logical FPS seen in the game
   * static int bestFPS = 0; bestFPS = std::max(bestFPS, BWAPI::Broodwar->getFPS());
   * BWAPI::Broodwar->drawTextScreen(BWAPI::Positions::Origin, "%cBest: %d GFPS\nCurrent: %d GFPS",
   * BWAPI::Text::White, bestFPS, BWAPI::Broodwar->getFPS()); Returns Logical frames per second that
   * the game is currently running at as an integer. See also getAverageFPS
   */
  Optional<Integer> getFPS();

  /**
   * Retrieves the average logical frame rate of the game in frames per second (FPS). Returns
   * Average logical frames per second that the game is currently running at as a double. See also
   * getFPS
   */
  Optional<Double> getAverageFPS();

  /**
   * Retrieves current amount of time in seconds that the game has elapsed. Returns Time, in
   * seconds, that the game has elapsed as an integer.
   */
  Optional<Integer> elapsedTime();

  Optional<Set<IRegion>> getRegions();

  Optional<Set<IChokePoint>> getChokePoints();

  Optional<Set<IBaseLocation>> getBaseLocations();

  Optional<Set<IBaseLocation>> getStartLocations();

  /**
   * Retrieves the width of the map in build tile units. Returns Width of the map in tiles. See also
   * mapHeight
   */
  Optional<Integer> mapWidth();

  /**
   * Retrieves the height of the map in build tile units. Returns Height of the map in tiles. See
   * also mapHeight
   */
  Optional<Integer> mapHeight();

  /**
   * Retrieves the title of the currently loaded map. Returns Map title as std::string object. See
   * also mapFileName, mapPathName
   */
  Optional<String> mapName();

  /**
   * Returns grid
   */
  Optional<Set<ITilePosition>> getGrid();

}
