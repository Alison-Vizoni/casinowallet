package com.casinowallet.casinowallet.controller.utils;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

@Component
public class RedisConnection implements AutoCloseable {

    private RLock lock = null;

    private final RedissonClient redissonClient;

    RedisConnection(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        this.redissonClient = Redisson.create(config);
    }

    public void acquireLock(String key) {
        this.lock = this.redissonClient.getLock(key);
    }

    @Override
    public void close() {
        if (null != this.lock) {
            this.lock.unlock();
        }
        this.lock = null;
    }
}
