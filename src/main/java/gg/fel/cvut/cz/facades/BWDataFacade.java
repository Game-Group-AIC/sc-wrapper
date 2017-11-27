package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.counters.BWCounter;
import gg.fel.cvut.cz.counters.IBWCounter;
import gg.fel.cvut.cz.enums.IGameTypes;

import java.util.Optional;

/**
 * Template for facades to access SC:BW data
 *
 * @param <T>
 */
public abstract class BWDataFacade<T extends BWCounter> implements IBWCounter {
    protected final T bwCounter;

    BWDataFacade(T bwCounter) {
        this.bwCounter = bwCounter;
        //TODO finish
    }
    //TODO other types + game instance

    /**
     * Template - to get concrete type of game object based on its enum type
     *
     * @param <K>
     * @param <V>
     */
    protected interface StrategyToGetGameObjectByType<K extends InGameInterface, V extends IGameTypes> {
        Optional<K> get(V gameType);
    }

    //TODO other types + getters
//    private final StrategyToGetGameObjectByType<gg.fel.cvut.cz.data.readonly.EBulletType, EBulletType> Melee;

}
