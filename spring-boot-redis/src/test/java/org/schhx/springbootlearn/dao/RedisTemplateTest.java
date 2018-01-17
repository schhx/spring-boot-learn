package org.schhx.springbootlearn.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void save() throws Exception {
        String key = UUID.randomUUID().toString();
        String value = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, 5, TimeUnit.SECONDS);
        Assert.assertEquals(value, stringRedisTemplate.opsForValue().get(key));
        Thread.sleep(5000);
        Assert.assertEquals(null, stringRedisTemplate.opsForValue().get(key));
    }

    @Test
    public void saveObject() throws Exception {
        String key = UUID.randomUUID().toString();
        User user = new User().setName("张三").setAge(20);
        redisTemplate.opsForValue().set(key, user);
        redisTemplate.expire(key, 5, TimeUnit.SECONDS);
        Assert.assertEquals(user, redisTemplate.opsForValue().get(key));
        Thread.sleep(5000);
        Assert.assertEquals(null, redisTemplate.opsForValue().get(key));
    }

}