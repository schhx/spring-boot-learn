package org.schhx.springbootlearn.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

    @Autowired
    RedisDao redisDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void save() throws Exception {
        String key = UUID.randomUUID().toString();
        String value = UUID.randomUUID().toString();
        redisDao.save(key, value);
        redisDao.expire(key, 5, TimeUnit.SECONDS);
        Assert.assertEquals(value, redisDao.getValue(key));
        Thread.sleep(5000);
        Assert.assertEquals(null, redisDao.getValue(key));
    }

    @Test
    public void saveObject() throws Exception {
        String key = UUID.randomUUID().toString();
        User user = new User().setName("张三").setAge(20);
        redisDao.saveObject(key, user);
        redisDao.expire(key, 5, TimeUnit.SECONDS);
        Assert.assertEquals(user, redisDao.getObjectValue(key, User.class));
        Thread.sleep(5000);
        Assert.assertEquals(null, redisDao.getValue(key));
    }

}