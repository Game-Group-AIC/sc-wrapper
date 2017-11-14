package gg.fel.cvut.cz.api;

import java.util.List;

/**
 * The Force class is used to get information about each force in a match. Normally this is considered a team. Note It is not called a team because players on the same force do not necessarily need to be allied at the beginning of a match.
 */
public interface Force {

    /**
     * Retrieves the unique ID that represents this Force. Returns An integer containing the ID for the Force.
     */
    int getID();

    /**
     * Retrieves the name of the Force. Returns A std::string object containing the name of the force. Example usage: BWAPI::Force myForce = BWAPI::Broodwar->self()->getForce(); if ( myForce->getName() == "Observers" ) BWAPI::Broodwar << "Looks like we're observing a match." << std::endl; Note Don't forget to use std::string::c_str() when passing this parameter to Game::sendText and other variadic functions.
     */
    String getName();

    /**
     * Retrieves the set of players that belong to this Force. Returns A Playerset object containing the players that are part of this Force. Example usage: // Get the enemy force, but make sure we have an enemy BWAPI::Force myEnemyForce = BWAPI::Broodwar->enemy() ? BWAPI::Broodwar->enemy()->getForce() : nullptr; if ( myEnemyForce != nullptr ) { Broodwar << "The allies of my enemy are..." << std::endl; for ( auto i = myEnemyForce.begin(); i != myEnemyForce.end(); ++i ) Broodwar << " - " << i->getName() << std::endl; }
     */
    List<Player> getPlayers();

}
