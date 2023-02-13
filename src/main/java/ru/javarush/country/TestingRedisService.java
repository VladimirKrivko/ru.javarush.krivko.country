package ru.javarush.country;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javarush.country.redis.CityCountry;

import java.util.List;

import static java.util.Objects.nonNull;

public class TestingRedisService implements TestingService {

    private static final Logger logger = LoggerFactory.getLogger(TestingRedisService.class);
    private final RedisClient redisClient;
    private final ObjectMapper mapper;

    public TestingRedisService(RedisClient redisClient) {
        this.redisClient = redisClient;
        mapper = new ObjectMapper();
    }

    public void pushToRedis(List<CityCountry> cities) {
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            RedisCommands<String, String> sync = connect.sync();
            for (CityCountry cityCountry : cities) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    logger.error("failed", e);
                }
            }
        }
    }

    public void testData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            RedisCommands<String, String> sync = connect.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    logger.error("failed", e);
                }
            }
        }
    }

    public void shutdown() {
        if (nonNull(redisClient)) {
            redisClient.close();
            logger.info("redisClient is closed");
        }
    }
}