package ru.javarush.country;

import io.lettuce.core.RedisClient;
import org.hibernate.SessionFactory;
import ru.javarush.country.entity.City;
import ru.javarush.country.redis.CityCountry;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionProvider sessionProvider = new SessionProvider();
        SessionFactory sessionFactory = sessionProvider.getSessionFactory();
        TestingMySQLService testingMySQLService = new TestingMySQLService(sessionFactory);

        RedisClientProvider redisClientProvider = new RedisClientProvider();
        RedisClient redisClient = redisClientProvider.prepareRedisClient();
        TestingRedisService testingRedisService = new TestingRedisService(redisClient);

//        List<City> allCities = testingMySQLService.fetchData();

//        CityTransformer cityTransformer = new CityTransformer();
//        List<CityCountry> cityCountries = cityTransformer.transformData(allCities);
//        testingRedisService.pushToRedis(cityCountries);

//        sessionFactory.close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        testingRedisService.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        testingMySQLService.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));

        testingRedisService.shutdown();
        testingMySQLService.shutdown();
    }
}