package gg.fel.cvut.cz.wrappers;

import bwapi.Game;

//There is no way to distinguish between games... :(
public class WGame extends Wrapper<Game> {

    public WGame(Game scInstance) {
        super(scInstance);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
