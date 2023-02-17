package ru.javarush.country.utility;

import java.util.concurrent.ThreadLocalRandom;

public final class Randomizer {

    private Randomizer() {
        throw new IllegalStateException("Utility class");
    }

    public static int getRandomInteger(int bound) {
        return ThreadLocalRandom.current().nextInt(1, bound + 1);
    }
}
