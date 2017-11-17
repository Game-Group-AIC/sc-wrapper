package gg.fel.cvut.cz.wrappers;

import bwapi.Unit;

public class WUnit extends WrapperForClassWithID<Unit> {
    public WUnit(Unit scInstance) {
        super(scInstance, scInstance.getID());
    }
}
