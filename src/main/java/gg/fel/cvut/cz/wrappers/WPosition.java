package gg.fel.cvut.cz.wrappers;

import bwapi.Position;

public class WPosition extends WrapperForPosition<Position> {
    public WPosition(Position scInstance) {
        super(scInstance, scInstance.getX(), scInstance.getY());
    }
}
