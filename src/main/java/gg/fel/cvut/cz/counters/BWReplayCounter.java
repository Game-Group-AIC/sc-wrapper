package gg.fel.cvut.cz.counters;

/**
 * Tracks time in replays for SC:BW
 */
public class BWReplayCounter extends BWCounter implements IBWReplayCounter {
    private final int gameLength;

    public BWReplayCounter(int gameLength) {
        this.gameLength = gameLength;
    }

    @Override
    public int lengthOfReplay() {
        return gameLength;
    }

    @Override
    public synchronized boolean nextFrame() {
        if (getCurrentFrame() + 1 <= gameLength) {
            increaseClocks();
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean previousFrame() {
        if (getCurrentFrame() == 0) {
            return false;
        }
        decreaseClock();
        return true;
    }
}
