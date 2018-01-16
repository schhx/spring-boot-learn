package org.schhx.springbootlearn.dao.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void test() throws Exception {
        User user = userRepository.save(new User("111", "姓名", 20));
        System.out.println(user);
        Assert.assertNotEquals(null, user);
    }

}