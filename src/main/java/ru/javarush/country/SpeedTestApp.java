package ru.javarush.country;

import io.lettuce.core.RedisClient;
import org.hibernate.SessionFactory;
import ru.javarush.country.entity.City;
import ru.javarush.country.provider.RedisClientProvider;
import ru.javarush.country.provider.SessionProvider;
import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.redis.CityTransformer;
import ru.javarush.country.service.SpeedTestOfDataAvailability;
import ru.javarush.country.service.TestingMysqlService;
import ru.javarush.country.service.TestingRedisService;

import java.util.List;

public class SpeedTestApp {

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
