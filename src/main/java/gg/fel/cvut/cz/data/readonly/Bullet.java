package gg.fel.cvut.cz.data.readonly;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import gg.fel.cvut.cz.api.IBullet;
import gg.fel.cvut.cz.api.IPlayer;
import gg.fel.cvut.cz.api.IPosition;
import gg.fel.cvut.cz.api.IUnit;
import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.DynamicPropertyRegister;
import gg.fel.cvut.cz.data.StaticPropertyRegister;
import gg.fel.cvut.cz.enums.BulletType;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

public class Bullet extends AContainer implements IBullet, Serializable {
    protected final DynamicPropertyRegister<Boolean> exists = new DynamicPropertyRegister<>();
    protected final StaticPropertyRegister<Integer> id = new StaticPropertyRegister<>();
    protected final DynamicPropertyRegister<IPlayer> player = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<BulletType> type = new DynamicPropertyRegister<>();
    protected final StaticPropertyRegister<IUnit> source = new StaticPropertyRegister<>();
    protected final DynamicPropertyRegister<IPosition> position = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<Double> angle = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<Double> velocityX = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<Double> velocityY = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<IUnit> target = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<IPosition> targetPosition = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<Integer> removeTimer = new DynamicPropertyRegister<>();
    protected final DynamicPropertyRegister<ImmutableMap<IPlayer, Boolean>> isVisible = new DynamicPropertyRegister<>();
    private final Set<StaticPropertyRegister<?>> toHash = ImmutableSet.of(id);

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
    public Optional<BulletType> getType() {
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

    @Override
    protected Set<StaticPropertyRegister<?>> staticPropertiesForEqualsAndHashCode() {
        return toHash;
    }
}
