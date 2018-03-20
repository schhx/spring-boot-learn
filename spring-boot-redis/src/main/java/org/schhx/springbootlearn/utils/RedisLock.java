package org.schhx.springbootlearn.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * redis setNx的集群排它锁
     *
     * @param key
     * @param second
     * @return
     */
    public boolean getLock(String key, long second) {
        key = "lock_tmp:" + key;
        Boolean set = redisTemplate.opsForValue().setIfAbsent(key, System.currentTimeMillis() + second * 1000 + "");
        if (set) {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        } else {
            //如果已存在这个key,尝试获取这个key
            String value = redisTemplate.opsForValue().get(key);
            //已经过了这个key的超时时间60秒还存在 则删除这个key 防止没有expire执行导致永久锁
            if (value != null && Long.parseLong(value) + 60 * 1000 <= System.currentTimeMillis()) {
                redisTemplate.delete(key);
            }
        }
        return set;
    }
}
