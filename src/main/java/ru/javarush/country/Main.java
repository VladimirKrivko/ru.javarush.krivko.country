package ru.javarush.country;

import io.lettuce.core.RedisClient;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.country.entity.City;
import ru.javarush.country.redis.CityCountry;

import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final int NUMBER_OF_TEST_ITERATIONS = 30;

    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();
        TestingMysqlService testingMySQLService = new TestingMysqlService(sessionFactory);

        RedisClientProvider redisClientProvider = new RedisClientProvider();
        RedisClient redisClient = redisClientProvider.prepareRedisClient();
        TestingRedisService testingRedisService = new TestingRedisService(redisClient);

        List<City> allCities = testingMySQLService.fetchData();

        CityTransformer cityTransformer = new CityTransformer();
        List<CityCountry> cityCountries = cityTransformer.transformData(allCities);
        testingRedisService.pushToRedis(cityCountries);

        SpeedTestOfDataAvailability speedTest = new SpeedTestOfDataAvailability(testingMySQLService, testingRedisService, NUMBER_OF_TEST_ITERATIONS);
        speedTest.runSpeedTest();

        testingMySQLService.shutdown();
        testingRedisService.shutdown();
    }
}