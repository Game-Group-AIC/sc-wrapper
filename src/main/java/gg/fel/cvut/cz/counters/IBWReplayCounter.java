package gg.fel.cvut.cz.counters;

/**
 * Interface for replay counter
 */
public interface IBWReplayCounter extends IBWCounter {

    int lengthOfReplay();

    boolean nextFrame();

    boolean previousFrame();

}
