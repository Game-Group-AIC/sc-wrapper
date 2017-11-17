package gg.fel.cvut.cz.wrappers;

import bwapi.Player;

public class WPlayer extends WrapperForClassWithID<Player> {
    WPlayer(Player scInstance) {
        super(scInstance, scInstance.getID());
    }
}
