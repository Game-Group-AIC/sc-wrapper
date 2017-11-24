package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.api.IBullet;
import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.counters.IBWCounter;
import gg.fel.cvut.cz.enums.BulletType;
import gg.fel.cvut.cz.enums.IGameTypes;

/**
 * Template for facades to access SC:BW data
 *
 * @param <T>
 * @param <A>
 */
public abstract class BWDataFacade<T extends BWCounter, A extends IBullet> implements IBWCounter {
    private final T bwCounter;

    protected BWDataFacade(T bwCounter, StrategyToGetGameObjectByType<A, BulletType> bulletEnumToTypeStrategy) {
        this.bwCounter = bwCounter;
        //TODO finish
        this.Melee = bulletEnumToTypeStrategy.get(BulletType.Melee);
    }
    //TODO other types + game instance

    @Override
    public void increaseClocks() {
        bwCounter.increaseClocks();
    }

    @Override
    public int getCurrentFrame() {
        return bwCounter.getCurrentFrame();
    }

    /**
     * Template - to get concrete type of game object based on its enum type
     *
     * @param <K>
     * @param <V>
     */
    protected interface StrategyToGetGameObjectByType<K extends InGameInterface, V extends IGameTypes> {
        K get(V gameType);
    }

    //TODO other types
    public final A Melee;

}
