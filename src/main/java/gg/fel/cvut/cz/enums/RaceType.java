package gg.fel.cvut.cz.enums;

import java.util.Random;

public enum RaceType {
    Zerg,
    Terran,
    Protoss,
    Random,
    None,
    Unknown;

    private static final java.util.Random RANDOM = new Random();

    /**
     * Get random Race - from Z, T, P
     *
     * @return
     */
    public static RaceType getRandomRace() {
        return values()[RANDOM.nextInt(3)];
    }

}
