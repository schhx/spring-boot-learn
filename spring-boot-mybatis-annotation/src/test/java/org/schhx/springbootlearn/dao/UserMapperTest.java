package org.schhx.springbootlearn.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.User;
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
    public void insert() throws Exception {
        User user = new User()
                .setId(UUID.randomUUID().toString())
                .setUsername("张三")
                .setAge(20);
        int result = userMapper.insert(user);
        Assert.assertEquals(1, result);
    }

    @Test
    @Transactional
    public void deleteByPrimaryKey() throws Exception {
        User user = new User()
                .setId(UUID.randomUUID().toString())
                .setUsername("张三")
                .setAge(20);
        userMapper.insert(user);
        int result = userMapper.deleteByPrimaryKey(user.getId());
        Assert.assertEquals(1, result);

    }

    @Test
    @Transactional
    public void updateByPrimaryKey() throws Exception {
        User user = new User()
                .setId(UUID.randomUUID().toString())
                .setUsername("张三")
                .setAge(20);
        userMapper.insert(user);
        user.setUsername("李四")
                .setAge(30);
        userMapper.updateByPrimaryKey(user);
        User result = userMapper.selectByPrimaryKey(user.getId());
        Assert.assertEquals(user, result);

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
    }

}