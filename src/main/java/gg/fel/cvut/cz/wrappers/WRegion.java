package gg.fel.cvut.cz.wrappers;

import bwta.Region;

public class WRegion extends WrapperForPosition<Region> {
    public WRegion(Region scInstance) {
        super(scInstance, scInstance.getX(), scInstance.getY());
    }
}
