package gg.fel.cvut.cz.facades;


import gg.fel.cvut.cz.data.AContainer;
import gg.fel.cvut.cz.data.IUpdatableContainer;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Template for wrapping facades
 *
 * @param <T>
 * @param <K>
 */
@AllArgsConstructor
public abstract class ATypeUpdater<T, K, L extends AContainer, U extends IUpdatableContainer<T>> implements IUpdater<T, L> {
    private final ConvertInstanceToKeyStrategy<T, K> bwInstanceToKeyConvertingStrategy;
    private final ConvertInstanceToKeyStrategy<L, K> containerToKeyConvertingStrategy;
    private final WrapInstanceToUpdatableContainerStrategy<T, U> wrapInstanceToUpdatableContainerStrategy;

    //to store data + update times
    private final Map<K, U> register = new HashMap<>();
    private final Map<K, Integer> updates = new HashMap<>();

    private final BWDataFacade aWrappingFactoryFacade;

    @Override
    public Optional<L> getWrappedInstance(T instanceToWrap) {
        //TODO + thread safe
        return null;
    }

    @Override
    public void update(L containerToUpdate) {
        //TODO + thread safe
    }

    @Override
    public Optional<T> getBWInstance(L container) {
        return Optional.ofNullable(register.get(containerToKeyConvertingStrategy.convertInstanceToKey(container))).map(IUpdatableContainer::getSCInstance);
    }

    /**
     * Template for strategy to wrap instance to updatable container
     *
     * @param <T>
     * @param <U>
     */
    protected interface WrapInstanceToUpdatableContainerStrategy<T, U extends IUpdatableContainer<T>> {
        U wrapInstanceToUpdatableContainer(T instance);
    }

    /**
     * Template for strategy which takes SC instance and returns its key - to be able to use it in dictionary
     *
     * @param <T>
     * @param <K>
     */
    protected interface ConvertInstanceToKeyStrategy<T, K> {
        K convertInstanceToKey(T instance);
    }
}
