package ru.javarush.country.utility;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    private Randomizer() {
        throw new IllegalStateException("Utility class");
    }

    public static int getRandomInteger(int bound) {
        return ThreadLocalRandom.current().nextInt(0, bound + 1);
    }
}
