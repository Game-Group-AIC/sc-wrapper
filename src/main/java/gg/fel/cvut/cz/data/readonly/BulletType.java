package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBulletType;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.BulletTypeEnum;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

//TODO implement
public class BulletType extends AContainer implements IBulletType, Serializable {

  private final StaticPropertyRegister<BulletTypeEnum> bulletType = new StaticPropertyRegister<>();
  private final Set<StaticPropertyRegister<?>> toHash = ImmutableSet.of(bulletType);

  public BulletType(BWCounter bwCounter) {
    super(bwCounter);
  }

  @Override
  public Optional<BulletTypeEnum> getBulletType() {
    return getPropertyOnTimeLineStrategy(bulletType);
  }

  @Override
  protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
    return toHash;
  }
}
