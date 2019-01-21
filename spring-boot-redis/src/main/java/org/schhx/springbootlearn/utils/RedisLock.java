package org.schhx.springbootlearn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 基于redis实现的简易分布式锁
 * 复杂的实现可参考 http://redis.io/topics/distlock
 */
@Component
public class RedisLock {

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * redis setNx的集群排它锁
     *
     * @param key
     * @param second
     * @return
     * @see <a href="http://redis.io/commands/setnx">Redis Documentation: SETNX</a>
     */
    public boolean lock(String key, long second) {
        Boolean set = redisTemplate.opsForValue().setIfAbsent(key, System.currentTimeMillis() + second * 1000 + "");
        if (set) {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        } else {
            String value = redisTemplate.opsForValue().getAndSet(key, System.currentTimeMillis() + second * 1000 + "");
            if (value != null && Long.parseLong(value) + 1 <= System.currentTimeMillis()) {
                return true;
            }
        }
        return set;
    }

    /**
     * 集群排它锁 这种实现方式更好
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     * @return
     * @see <a href="http://redis.io/commands/set">Redis Documentation: SET</a>
     */
    public boolean lock(final String key, final String value, long timeout, TimeUnit timeUnit) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            connection.set(key.getBytes(), value.getBytes(), Expiration.from(timeout, timeUnit), RedisStringCommands.SetOption.SET_IF_ABSENT);
            return null;
        });
        String lockValue = redisTemplate.opsForValue().get(key);
        return value.equals(lockValue);
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        doUnlock(key, value);
    }

    /**
     * 延时解锁
     *
     * @param key
     * @param value
     * @param delayTime
     * @param unit
     */
    public void unlock(final String key, final String value, long delayTime, TimeUnit unit) {
        if (delayTime <= 0) {
            doUnlock(key, value);
        } else {
            executorService.schedule(() -> doUnlock(key, value), delayTime, unit);
        }

    }

    private void doUnlock(final String lockKey, final String resourceValue) {
        String value = redisTemplate.opsForValue().get(lockKey);
        if (resourceValue.equals(value)) {
            redisTemplate.delete(lockKey);
        }
    }
}
