package gg.fel.cvut.cz.facades.managers;

import gg.fel.cvut.cz.counters.BWReplayCounter;
import gg.fel.cvut.cz.counters.IBWReplayCounter;
import gg.fel.cvut.cz.facades.IGameDataAccessAdapter;
import java.io.Serializable;

public class ReplayGameFacade extends BWDataFacade<BWReplayCounter> implements
    IGameDataAccessAdapter, Serializable, IBWReplayCounter {

}
