package org.example.utils;

import java.util.concurrent.ThreadLocalRandom;

public class Random {

    public static int getRandomInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

}
