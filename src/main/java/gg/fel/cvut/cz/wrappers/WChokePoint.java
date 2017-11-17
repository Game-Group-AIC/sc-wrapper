package gg.fel.cvut.cz.wrappers;

import bwta.Chokepoint;

public class WChokePoint extends WrapperForPosition<Chokepoint> {
    public WChokePoint(Chokepoint scInstance) {
        super(scInstance, scInstance.getX(), scInstance.getY());
    }
}
