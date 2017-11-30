package gg.fel.cvut.cz.facades;

import gg.fel.cvut.cz.api.IGame;
import gg.fel.cvut.cz.data.AContainer;
import java.util.stream.Stream;

/**
 * Contract on adapter to access game instances
 */
public interface IGameDataAccessAdapter extends IGame {

  Stream<? extends AContainer> getAllGameInstances();

  //TODO getters on static types

}
