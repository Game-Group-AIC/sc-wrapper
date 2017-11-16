package gg.fel.cvut.cz.counters;

import lombok.NoArgsConstructor;

/**
 * Tracks time in SC:BW
 */
@NoArgsConstructor
public class BWCounter implements IBWCounter {
    private int currentFrame = 0;

    public synchronized void increaseClocks() {
        currentFrame++;
    }

    public synchronized int getCurrentFrame() {
        return currentFrame;
    }

    synchronized void decreaseClock() {
        currentFrame = Math.max(0, currentFrame - 1);
    }
}
