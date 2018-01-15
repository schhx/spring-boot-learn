package org.schhx.springbootlearn.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void create() throws Exception {
        User user = userService.create("张三", 20);
        System.out.println(user);
        Assert.assertNotEquals(null, user);
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getById() throws Exception {
    }

}