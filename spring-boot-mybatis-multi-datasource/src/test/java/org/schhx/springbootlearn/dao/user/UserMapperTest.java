package org.schhx.springbootlearn.dao.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void countByExample() throws Exception {
    }

    @Test
    public void deleteByExample() throws Exception {
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    @Transactional
    public void insertSelective() throws Exception {
        int result = userMapper.insertSelective(new User("123", "456", 20));
        Assert.assertEquals(1, result);
    }

    @Test
    public void selectByExample() throws Exception {
    }

    @Test
    @Transactional
    public void selectByPrimaryKey() throws Exception {
        User user = userMapper.selectByPrimaryKey("8fcba16e-2c7e-4de2-817f-b785c17fd1a7");
        System.out.println(user);
    }

    @Test
    public void updateByExampleSelective() throws Exception {
    }

    @Test
    public void updateByExample() throws Exception {
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
    }

}