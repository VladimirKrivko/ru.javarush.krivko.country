package ru.javarush.country;

import io.lettuce.core.RedisClient;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.country.entity.City;
import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.utility.Randomizer;
import ru.javarush.country.utility.SpeedTestOfDataAvailability;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final int NUMBER_OF_TEST_ITERATIONS = 10;
    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();
        TestingMySQLService testingMySQLService = new TestingMySQLService(sessionFactory);

        RedisClientProvider redisClientProvider = new RedisClientProvider();
        RedisClient redisClient = redisClientProvider.prepareRedisClient();
        TestingRedisService testingRedisService = new TestingRedisService(redisClient);

        List<City> allCities = testingMySQLService.fetchData();

        CityTransformer cityTransformer = new CityTransformer();
        List<CityCountry> cityCountries = cityTransformer.transformData(allCities);
        testingRedisService.pushToRedis(cityCountries);


        long[] mysqlExecutionTime = new long[NUMBER_OF_TEST_ITERATIONS];
        long[] redisExecutionTime = new long[NUMBER_OF_TEST_ITERATIONS];

        for (int i = 0; i < NUMBER_OF_TEST_ITERATIONS; i++) {
            List<Integer> ids = getListIds();

            mysqlExecutionTime[i] = SpeedTestOfDataAvailability.getExecutionTime(testingMySQLService, ids);
            redisExecutionTime[i] = SpeedTestOfDataAvailability.getExecutionTime(testingRedisService, ids);
        }

        OptionalDouble averageMysql = LongStream.of(mysqlExecutionTime).average();
        OptionalDouble averageRedis = LongStream.of(redisExecutionTime).average();

        logger.info("{}:\t{} ms", "average time for MySQL", averageMysql.getAsDouble());
        logger.info("{}:\t{} ms", "average time for Redis", averageRedis.getAsDouble());

        testingMySQLService.shutdown();
        testingRedisService.shutdown();
    }

    private static List<Integer> getListIds() {
        List<Integer> ids = new ArrayList<>(NUMBER_OF_TEST_ITERATIONS);
        for (int i = 0; i < NUMBER_OF_TEST_ITERATIONS; i++) {
            ids.add(i, Randomizer.getRandomInteger(4000));
        }
        return ids;
    }
}