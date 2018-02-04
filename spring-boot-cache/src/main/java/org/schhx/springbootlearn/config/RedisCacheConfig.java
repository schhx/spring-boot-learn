package org.schhx.springbootlearn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisCacheConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public CacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(10L);
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }
}
