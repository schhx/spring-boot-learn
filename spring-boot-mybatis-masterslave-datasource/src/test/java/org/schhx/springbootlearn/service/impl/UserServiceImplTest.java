package org.schhx.springbootlearn.service.impl;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    @Transactional
    public void createUser() throws Exception {
        User user = new User()
                .setId(UUID.randomUUID().toString())
                .setUsername("张三")
                .setAge(19);
        User result = userService.createUser(user);
        assertNotEquals(null, result);
        log.info(user.toString());
    }

    @Test
    public void getUserById() throws Exception {
        User user = userService.getUserById("master");
        assertNotEquals(null, user);
    }

    @Test
    public void getUserByIdFromSlave() throws Exception {
        User user = userService.getUserByIdFromSlave("master");
        assertEquals(null, user);

        user = userService.getUserByIdFromSlave("slave");
        assertNotEquals(null, user);
    }

}