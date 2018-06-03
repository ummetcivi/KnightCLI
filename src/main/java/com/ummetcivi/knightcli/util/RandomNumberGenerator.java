package com.ummetcivi.knightcli.util;

import java.util.Random;

public class RandomNumberGenerator {

    private static RandomNumberGenerator instance;
    private final Random random;

    private RandomNumberGenerator() {
        random = new Random();
    }

    public static RandomNumberGenerator getInstance() {
        if (instance == null) {
            instance = new RandomNumberGenerator();
        }
        return instance;
    }

    public int generateRandomBetween(int start, int end) {
        return random.ints(start, end).findFirst().getAsInt();
    }
}
