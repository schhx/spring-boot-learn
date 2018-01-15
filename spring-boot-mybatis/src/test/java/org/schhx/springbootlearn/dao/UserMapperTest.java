package org.schhx.springbootlearn.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.User;
import org.schhx.springbootlearn.module.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

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
        User user = new User()
                .setId("123")
                .setUsername("张三")
                .setAge(20);
        int result = userMapper.insertSelective(user);
        Assert.assertEquals(1, result);
    }

    @Test
    @Transactional
    public void selectByExample() throws Exception {
        User user;
        for(int i=0; i<10; i++){
            user = new User()
                    .setId("1000" + i)
                    .setUsername("user" + i)
                    .setAge(20 + i);
            userMapper.insertSelective(user);
        }

        PageHelper.startPage(1, 5);
        UserExample example = new UserExample();
        example.or().andAgeGreaterThanOrEqualTo(20);
        List<User> users = userMapper.selectByExample(example);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        System.out.println(userPageInfo);
    }

    @Test
    @Transactional
    public void selectByPrimaryKey() throws Exception {
        User user = new User()
                .setId("123")
                .setUsername("张三")
                .setAge(20);
        userMapper.insertSelective(user);

        User result = userMapper.selectByPrimaryKey("123");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
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