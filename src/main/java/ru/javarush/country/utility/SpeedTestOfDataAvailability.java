package ru.javarush.country.utility;

import ru.javarush.country.TestingService;

import java.util.ArrayList;
import java.util.List;

public class SpeedTestOfDataAvailability {

//    private static final int NUMBER_OF_TEST_ITERATIONS = 10;

//    private final List<Integer> ids = List.of(1, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

    private SpeedTestOfDataAvailability() {
        throw new IllegalStateException("Utility class");
    }

    public static long getExecutionTime(TestingService ts, List<Integer> ids) {
        long start = System.currentTimeMillis();
        ts.testData(ids);
        long stop = System.currentTimeMillis();
        long result = stop - start;

        //TODO: заменить на логер с выводом в консоль
        System.out.printf("%s:\t%d ms\n", ts.getClass().getSimpleName(), result);
        return result;
    }

//    private static List<Integer> getListIds() {
//        List<Integer> ids = new ArrayList<>(NUMBER_OF_TEST_ITERATIONS);
//        for (int i = 0; i < NUMBER_OF_TEST_ITERATIONS; i++) {
//            ids.add(i, Randomizer.getRandomInteger(4000));
//        }
//        return ids;
//    }

}
