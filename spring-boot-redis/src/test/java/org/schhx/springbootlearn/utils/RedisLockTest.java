package org.schhx.springbootlearn.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockTest {

    @Autowired
    private RedisLock redisLock;

    private String key = "xxx";
    private String value = "xxx";

    @Test
    public void lock() throws Exception {
        boolean result = redisLock.lock(key, value, 10);
        Assert.assertTrue(result);
        result = redisLock.lock(key, value, 10);
        Assert.assertTrue(!result);
        Thread.sleep(10000);
        result = redisLock.lock(key, value, 10);
        Assert.assertTrue(result);
    }

    @Test
    public void unlock() throws Exception {
        boolean result = redisLock.lock(key, value, 10);
        Assert.assertTrue(result);
        result = redisLock.lock(key, value, 10);
        Assert.assertTrue(!result);
        Assert.assertTrue(!redisLock.unlock(key, "xxx1"));
        Assert.assertTrue(!redisLock.lock(key, value, 10));
        Assert.assertTrue(redisLock.unlock(key, value));
        Assert.assertTrue(redisLock.lock(key, value, 10));
    }

}