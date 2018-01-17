package org.schhx.springbootlearn.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class RedisDao {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public RedisDao save(String key, String value){
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (RedisConnectionFailureException e){
            throw new RuntimeException("缓存连接失败", e);
        }
        return this;
    }

    public RedisDao saveObject(String key, Object o){
        try {
            String value = objectMapper.writeValueAsString(o);
            save(key, value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("数据转换失败", e);
        } catch (RedisConnectionFailureException e){
            throw new RuntimeException("缓存连接失败", e);
        }
        return this;
    }

    public String getValue(String key){
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (RedisConnectionFailureException e){
            throw new RuntimeException("缓存连接失败", e);
        }
    }

    public <T> T getObjectValue(String key, Class<T> clazz){
        try {
            String value = getValue(key);
            if(value == null){
                return null;
            }
            return objectMapper.readValue(value, clazz);
        } catch (IOException e) {
            throw new RuntimeException("数据转换失败", e);
        }
    }

    public void expireAt(String key, Date date){
        try {
            redisTemplate.expireAt(key, date);
        } catch (RedisConnectionFailureException e){
            throw new RuntimeException("缓存连接失败", e);
        }
    }

    public void expire(String key, long timeout, TimeUnit timeUnit){
        try {
            redisTemplate.expire(key, timeout, timeUnit);
        } catch (RedisConnectionFailureException e){
            throw new RuntimeException("缓存连接失败", e);
        }
    }

    public void delete(String key){
        try {
            redisTemplate.delete(key);
        } catch (RedisConnectionFailureException e){
            throw new RuntimeException("缓存连接失败", e);
        }
    }

    public void expireAtTheEndOfToday(String key){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        try {
            redisTemplate.expireAt(key, cal.getTime());
        } catch (RedisConnectionFailureException e){
            throw new RuntimeException("缓存连接失败", e);
        }
    }
}
