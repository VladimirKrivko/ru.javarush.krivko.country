package ru.javarush.country.service;

import lombok.extern.slf4j.Slf4j;
import ru.javarush.country.utility.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

@Slf4j
public class SpeedTestOfDataAvailability {

    private static final int UPPER_BOUND = 3000;
    private final TestingService testingServiceFirst;
    private final TestingService testingServiceSecond;
    private final long[] resultsFirstExecutionTime;
    private final long[] resultsSecondExecutionTime;
    private final int numberOfTestIterations;

    public SpeedTestOfDataAvailability(TestingService testingServiceFirst, TestingService testingServiceSecond, int numberOfTestIterations) {
        this.testingServiceFirst = testingServiceFirst;
        this.testingServiceSecond = testingServiceSecond;
        this.numberOfTestIterations = numberOfTestIterations;
        resultsFirstExecutionTime = new long[numberOfTestIterations];
        resultsSecondExecutionTime = new long[numberOfTestIterations];
    }

    public void runSpeedTest() {
        for (int i = 0; i < numberOfTestIterations; i++) {
            List<Integer> ids = getListIds();

            resultsFirstExecutionTime[i] = SpeedTestOfDataAvailability.getExecutionTime(testingServiceFirst, ids);
            resultsSecondExecutionTime[i] = SpeedTestOfDataAvailability.getExecutionTime(testingServiceSecond, ids);
        }
        OptionalDouble averageFirstResult = LongStream.of(resultsFirstExecutionTime).average();
        OptionalDouble averageSecondResult = LongStream.of(resultsSecondExecutionTime).average();

        String nameFirst = testingServiceFirst.getClass().getSimpleName();
        String nameSecond = testingServiceSecond.getClass().getSimpleName();

        System.out.printf("%s:\t%.2f ms\n", "Average time for " + nameFirst, averageFirstResult.getAsDouble());
        System.out.printf("%s:\t%.2f ms\n", "Average time for " + nameSecond, averageSecondResult.getAsDouble());
    }

    private static long getExecutionTime(TestingService ts, List<Integer> ids) {
        long start = System.currentTimeMillis();
        ts.fetchData(ids);
        long stop = System.currentTimeMillis();
        long result = stop - start;

        log.info("{}:\t{} ms", ts.getClass().getSimpleName(), result);
        return result;
    }

    private List<Integer> getListIds() {
        List<Integer> ids = new ArrayList<>(numberOfTestIterations);
        for (int i = 0; i < numberOfTestIterations; i++) {
            ids.add(i, Randomizer.getRandomInteger(UPPER_BOUND));
        }
        return ids;
    }
}
