package ru.javarush.country;

import io.lettuce.core.RedisClient;
import org.hibernate.SessionFactory;
import ru.javarush.country.entity.City;
import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.utility.Randomizer;
import ru.javarush.country.utility.SpeedTestOfDataAvailability;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.LongStream;

public class Main {
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


        long[] redisExecutionTime = new long[NUMBER_OF_TEST_ITERATIONS];
        long[] mysqlExecutionTime = new long[NUMBER_OF_TEST_ITERATIONS];

        for (int i = 0; i < NUMBER_OF_TEST_ITERATIONS; i++) {
            List<Integer> ids = getListIds();

            redisExecutionTime[i] = SpeedTestOfDataAvailability.getExecutionTime(testingRedisService, ids);
            mysqlExecutionTime[i] = SpeedTestOfDataAvailability.getExecutionTime(testingMySQLService, ids);
        }

        OptionalDouble averageRedis = LongStream.of(redisExecutionTime).average();
        OptionalDouble averageMysql = LongStream.of(mysqlExecutionTime).average();

        System.out.printf("%s:\t%.2f ms\n", "average time for Redis", averageRedis.getAsDouble());
        System.out.printf("%s:\t%.2f ms\n", "average time for MySQL", averageMysql.getAsDouble());


        testingRedisService.shutdown();
        testingMySQLService.shutdown();
    }

    private static List<Integer> getListIds() {
        List<Integer> ids = new ArrayList<>(NUMBER_OF_TEST_ITERATIONS);
        for (int i = 0; i < NUMBER_OF_TEST_ITERATIONS; i++) {
            ids.add(i, Randomizer.getRandomInteger(4000));
        }
        return ids;
    }
}






//        Пересоздаю testingMySQLService
//        sessionFactory.close();
//        SessionProvider newSessionProvider = new SessionProvider();
//        sessionFactory = newSessionProvider.getSessionFactory();
//        testingMySQLService = new TestingMySQLService(sessionFactory);