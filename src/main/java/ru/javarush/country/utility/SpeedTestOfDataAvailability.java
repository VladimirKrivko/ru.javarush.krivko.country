package ru.javarush.country.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.country.TestingService;

import java.util.List;

public class SpeedTestOfDataAvailability {

    public static final Logger logger = LoggerFactory.getLogger(SpeedTestOfDataAvailability.class);

    private SpeedTestOfDataAvailability() {
        throw new IllegalStateException("Utility class");
    }

    public static long getExecutionTime(TestingService ts, List<Integer> ids) {
        long start = System.currentTimeMillis();
        ts.testData(ids);
        long stop = System.currentTimeMillis();
        long result = stop - start;

        logger.info("{}:\t{} ms", ts.getClass().getSimpleName(), result);
        return result;
    }
}
