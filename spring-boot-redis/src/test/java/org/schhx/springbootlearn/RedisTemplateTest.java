package org.schhx.springbootlearn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author shanchao
 * @date 2018-08-28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() throws Exception {
        String key = "string";
        String value = "abc, 123";
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 设值
        valueOperations.set(key, value);
        // 取值
        String result = (String) valueOperations.get(key);
        assertEquals(value, result);
        // 删除
        redisTemplate.delete(key);
        assertEquals(null, valueOperations.get(key));

        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        valueOperations.multiSet(map);
        List list = valueOperations.multiGet(Arrays.asList("key1", "key2"));
        assertEquals(2, list.size());



        valueOperations.set(key, value, 5, TimeUnit.SECONDS);
        assertEquals(value, valueOperations.get(key));
        Thread.sleep(5000);
        assertEquals(null, valueOperations.get(key));
    }

}
