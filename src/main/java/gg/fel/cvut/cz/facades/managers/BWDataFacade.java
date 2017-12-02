package gg.fel.cvut.cz.facades.managers;

import gg.fel.cvut.cz.counters.BWCounter;
import lombok.AllArgsConstructor;

/**
 * Template for facades to access SC:BW data
 */
@AllArgsConstructor
public abstract class BWDataFacade<T extends BWCounter> {

  protected final T bwCounter;
}
