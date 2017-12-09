package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableMap;
import gg.fel.cvut.cz.api.IBullet;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.data.AContainerWithID;
import gg.fel.cvut.cz.data.properties.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.properties.Property;
import gg.fel.cvut.cz.data.properties.PropertyMap;
import gg.fel.cvut.cz.data.properties.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.BulletTypeEnum;
import java.io.Serializable;
import java.util.Optional;

public class Bullet extends AContainerWithID implements IBullet, Serializable {

  protected final DynamicPropertyRegister<Boolean, Property<Boolean>> exists = new DynamicPropertyRegister<Boolean, Property<Boolean>>(
      Property::new);
  protected final StaticPropertyRegister<Integer, Property<Integer>> id = new StaticPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final DynamicPropertyRegister<IPlayer, Property<IPlayer>> player = new DynamicPropertyRegister<IPlayer, Property<IPlayer>>(
      Property::new);
  protected final DynamicPropertyRegister<BulletTypeEnum, Property<BulletTypeEnum>> type = new DynamicPropertyRegister<BulletTypeEnum, Property<BulletTypeEnum>>(
      Property::new);
  protected final StaticPropertyRegister<IUnit, Property<IUnit>> source = new StaticPropertyRegister<IUnit, Property<IUnit>>(
      Property::new);
  protected final DynamicPropertyRegister<IPosition, Property<IPosition>> position = new DynamicPropertyRegister<IPosition, Property<IPosition>>(
      Property::new);
  protected final DynamicPropertyRegister<Double, Property<Double>> angle = new DynamicPropertyRegister<Double, Property<Double>>(
      Property::new);
  protected final DynamicPropertyRegister<Double, Property<Double>> velocityX = new DynamicPropertyRegister<Double, Property<Double>>(
      Property::new);
  protected final DynamicPropertyRegister<Double, Property<Double>> velocityY = new DynamicPropertyRegister<Double, Property<Double>>(
      Property::new);
  protected final DynamicPropertyRegister<IUnit, Property<IUnit>> target = new DynamicPropertyRegister<IUnit, Property<IUnit>>(
      Property::new);
  protected final DynamicPropertyRegister<IPosition, Property<IPosition>> targetPosition = new DynamicPropertyRegister<IPosition, Property<IPosition>>(
      Property::new);
  protected final DynamicPropertyRegister<Integer, Property<Integer>> removeTimer = new DynamicPropertyRegister<Integer, Property<Integer>>(
      Property::new);
  protected final DynamicPropertyRegister<ImmutableMap<IPlayer, Boolean>, PropertyMap<IPlayer, Boolean>> isVisible = new DynamicPropertyRegister<ImmutableMap<IPlayer, Boolean>, PropertyMap<IPlayer, Boolean>>(
      PropertyMap::new);

  public Bullet(BWReplayCounter bwCounter, int id) {
    super(bwCounter, id);
  }

  @Override
  public Optional<Integer> getID() {
    return getPropertyOnTimeLineStrategy(id);
  }

  @Override
  public Optional<Boolean> exists() {
    return getPropertyOnTimeLineStrategy(exists);
  }

  @Override
  public Optional<IPlayer> getPlayer() {
    return getPropertyOnTimeLineStrategy(player);
  }

  @Override
  public Optional<BulletTypeEnum> getType() {
    return getPropertyOnTimeLineStrategy(type);
  }

  @Override
  public Optional<IUnit> getSource() {
    return getPropertyOnTimeLineStrategy(source);
  }

  @Override
  public Optional<IPosition> getPosition() {
    return getPropertyOnTimeLineStrategy(position);
  }

  @Override
  public Optional<Double> getAngle() {
    return getPropertyOnTimeLineStrategy(angle);
  }

  @Override
  public Optional<Double> getVelocityX() {
    return getPropertyOnTimeLineStrategy(velocityX);
  }

  @Override
  public Optional<Double> getVelocityY() {
    return getPropertyOnTimeLineStrategy(velocityY);
  }

  @Override
  public Optional<IUnit> getTarget() {
    return getPropertyOnTimeLineStrategy(target);
  }

  @Override
  public Optional<IPosition> getTargetPosition() {
    return getPropertyOnTimeLineStrategy(targetPosition);
  }

  @Override
  public Optional<Integer> getRemoveTimer() {
    return getPropertyOnTimeLineStrategy(removeTimer);
  }

  @Override
  public Optional<Boolean> isVisible(IPlayer player) {
    return getPropertyOnTimeLineStrategy(isVisible, player);
  }
}
