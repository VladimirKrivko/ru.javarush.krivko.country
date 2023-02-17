package ru.javarush.country.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import ru.javarush.country.exception.JsonProcessingRuntimeException;
import ru.javarush.country.redis.CityCountry;

import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
public class TestingRedisService implements TestingService {

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
                    log.error("failed", e);
                    throw new JsonProcessingRuntimeException(e.getMessage());
                }
            }
        }
    }

    public void fetchData(List<Integer> ids) {
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            RedisCommands<String, String> sync = connect.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    log.error("failed", e);
                    throw new JsonProcessingRuntimeException(e.getMessage());
                }
            }
        }
    }

    public void shutdown() {
        if (nonNull(redisClient)) {
            redisClient.close();
            log.info("redisClient is closed");
        }
    }
}
