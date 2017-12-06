package gg.fel.cvut.cz.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import gg.fel.cvut.cz.data.readonly.Game;

/**
 * The abstract IGame class is implemented by BWAPI and is the primary means of obtaining all game
 * state information from Starcraft Broodwar. IGame state information includes all units, resources,
 * players, forces, bullets, terrain, fog of war, regions, etc.
 */
@JsonDeserialize(as = Game.class)
public interface IGame extends InGameInterface, IGameFacade {

}
