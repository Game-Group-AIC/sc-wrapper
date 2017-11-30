package gg.fel.cvut.cz.api;

import gg.fel.cvut.cz.enums.BulletTypeEnum;
import java.io.Serializable;
import java.util.Optional;

public interface IBulletType extends InGameInterface, Serializable {

  Optional<BulletTypeEnum> getBulletType();

}
