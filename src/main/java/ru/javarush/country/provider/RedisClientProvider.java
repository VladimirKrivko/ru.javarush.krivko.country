package ru.javarush.country.provider;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisClientProvider {

    public RedisClient prepareRedisClient() {
        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            log.info("Connected to Redis");
        }
        return redisClient;
    }
}
