package ru.javarush.country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.country.utility.Randomizer;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public class SpeedTestOfDataAvailability {

    private static final Logger logger = LoggerFactory.getLogger(SpeedTestOfDataAvailability.class);
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
        
        //TODO: добавить в сервис получение имени ресурса.
        logger.info("{}:\t{} ms", "average time for MySQL", averageFirstResult.getAsDouble());
        logger.info("{}:\t{} ms", "average time for Redis", averageSecondResult.getAsDouble());
    }

    private static long getExecutionTime(TestingService ts, List<Integer> ids) {
        long start = System.currentTimeMillis();
        ts.testData(ids);
        long stop = System.currentTimeMillis();
        long result = stop - start;

        logger.info("{}:\t{} ms", ts.getClass().getSimpleName(), result);
        return result;
    }

    private List<Integer> getListIds() {
        List<Integer> ids = new ArrayList<>(numberOfTestIterations);
        for (int i = 0; i < numberOfTestIterations; i++) {

            //TODO: получать bound из базки? или не?
            ids.add(i, Randomizer.getRandomInteger(4000));
        }
        return ids;
    }
}