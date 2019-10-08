package org.schhx.springbootlearn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 基于redis实现的简易分布式锁
 * 复杂的实现可参考 http://redis.io/topics/distlock
 */
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final static DefaultRedisScript<Long> LOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then return redis.call('expire',KEYS[1],ARGV[2])  else return 0 end"
            , Long.class
    );

    private final static DefaultRedisScript<Long> UNLOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end"
            , Long.class
    );

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @param timeInSeconds
     * @return
     */
    public boolean lock(final String key, final String value, long timeInSeconds) {
        Long result = redisTemplate.execute(LOCK_LUA_SCRIPT, Arrays.asList(key), value, String.valueOf(timeInSeconds));
        return result == 1;
    }


    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public boolean unlock(String key, String value) {
        Long result = redisTemplate.execute(UNLOCK_LUA_SCRIPT, Arrays.asList(key), value);
        return result != null && result == 1;
    }
}
