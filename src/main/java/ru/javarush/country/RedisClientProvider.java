package ru.javarush.country;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

public class RedisClientProvider {
    public RedisClient prepareRedisClient() {
        RedisClient redisClient = RedisClient.create(RedisURI.create("localhost", 6379));
        try(StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            //TODO: заменить вывод в консоль на логирование в консоль.
            System.out.println("/nConnected to Redis/n");
        }
        return redisClient;
    }
}
