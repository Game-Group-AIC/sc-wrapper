package gg.fel.cvut.cz.data;

import gg.fel.cvut.cz.api.InGameInterface;
import gg.fel.cvut.cz.wrappers.Wrapper;

/**
 * @param <T>
 * @param <V>
 * @param <K>
 */
public interface IUpdatableContainer<T, V extends Wrapper<T>, K extends AContainer> extends InGameInterface, IContainer {

}

