package gg.fel.cvut.cz.wrappers;

import bwapi.TilePosition;

public class WTilePosition extends WrapperForPosition<TilePosition> {
    public WTilePosition(TilePosition scInstance) {
        super(scInstance, scInstance.getX(), scInstance.getY());
    }
}
