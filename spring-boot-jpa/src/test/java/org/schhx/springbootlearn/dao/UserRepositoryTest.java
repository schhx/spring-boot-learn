package org.schhx.springbootlearn.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void create() throws Exception {
        String id = UUID.randomUUID().toString();
        User user = new User().setId(id).setUsername("张三").setAge(20);
        User result = userRepository.save(user);
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    @Transactional
    public void findByUserName() throws Exception {
        String id = UUID.randomUUID().toString();
        User user = new User().setId(id).setUsername("张三").setAge(20);
        userRepository.save(user);
        List<User> result = userRepository.findByUsername("张三");
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }

    @Test
    @Transactional
    public void findByExample() throws Exception {
        String id = UUID.randomUUID().toString();
        User user = new User().setId(id).setUsername("张三").setAge(20);
        userRepository.save(user);
        List<User> result = userRepository.findAll(Example.of(new User().setUsername("张三")));
        System.out.println(result);
        Assert.assertNotEquals(null, result);
    }
}