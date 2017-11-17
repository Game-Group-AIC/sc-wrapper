package gg.fel.cvut.cz.wrappers;

import bwta.BaseLocation;

public class WBaseLocation extends WrapperForPosition<BaseLocation> {
    public WBaseLocation(BaseLocation scInstance) {
        super(scInstance, scInstance.getX(), scInstance.getY());
    }
}
