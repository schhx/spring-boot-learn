package org.schhx.springbootlearn.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

//    @Autowired
//    private UserServiceJdbcTemplateImpl userService;

    @Autowired
    private UserServiceNamedParamterJdbcTemplateImpl userService;

    @Test
    @Transactional
    public void create() throws Exception {
        User user = userService.create("张三", 20);
        System.out.println(user);
        Assert.assertNotEquals(null, user);
    }

    @Test
    @Transactional
    public void delete() throws Exception {
        User user = userService.create("张三", 20);
        Assert.assertNotEquals(null, user);
        userService.delete(user.getId());
        user = userService.getById(user.getId());
        Assert.assertEquals(null, user);
    }

    @Test
    @Transactional
    public void update() throws Exception {
        User user = userService.create("张三", 20);
        System.out.println(user);
        Assert.assertNotEquals(null, user);
        user = userService.update(user.getId(), "李四", 30);
        System.out.println(user);
        Assert.assertEquals("李四", user.getUsername());
    }

    @Test
    @Transactional
    public void getUsersByAge() throws Exception {
        for(int i=0; i<10; i++){
            userService.create("username" + i, 14);
        }

        List<User> users = userService.getUsersByAge(14);
        Assert.assertTrue(users.size() >= 10);
    }

}