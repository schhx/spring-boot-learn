package org.schhx.springbootlearn.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    @Transactional
    public void create() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("张三");
        user.setAge(18);
        int count = userMapper.insert(user);
        assertEquals(1, count);
    }

}