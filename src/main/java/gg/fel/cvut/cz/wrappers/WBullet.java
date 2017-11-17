package gg.fel.cvut.cz.wrappers;

import bwapi.Bullet;

public class WBullet extends WrapperForClassWithID<Bullet> {
    public WBullet(Bullet scInstance) {
        super(scInstance, scInstance.getID());
    }
}
