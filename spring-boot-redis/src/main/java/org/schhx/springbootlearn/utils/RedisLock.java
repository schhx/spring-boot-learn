package org.schhx.springbootlearn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis实现的简易分布式锁
 * 复杂的实现可参考 http://redis.io/topics/distlock
 */
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final static DefaultRedisScript<Long> UNLOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call(\"get\",KEYS[1]) == KEYS[2] then return redis.call(\"del\",KEYS[1]) else return -1 end"
            , Long.class
    );

    /**
     * 加锁
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean lock(final String key, final String value, long timeout, TimeUnit timeUnit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
    }


    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public boolean unlock(String key, String value) {
        Long result = redisTemplate.execute(UNLOCK_LUA_SCRIPT, Arrays.asList(key, value));
        return result != null && result == 1;
    }
}
