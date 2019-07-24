package org.schhx.springbootlearn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.bean.Someone;
import org.schhx.springbootlearn.bean.Someone3;
import org.schhx.springbootlearn.bean.Someone2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shanchao
 * @date 2018-09-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest {

    @Autowired
    private Someone someoneProperties;

    @Autowired
    private Someone3 someone2;

    @Autowired
    private Someone2 someone3;

    @Value("${someone.name}")
    private String name;

    @Value("${someone.age}")
    private String age;

    @Value("${someone.best-friend}")
    private String bestFriend;

    @Test
    public void printConfig(){
        System.out.println("Properties from @Value");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("bestFriend = " + bestFriend);

        System.out.println();
        System.out.println("Properties from bean");
        System.out.println(someoneProperties);

        System.out.println();
        System.out.println("Properties from bean2");
        System.out.println(someone2);

        System.out.println();
        System.out.println("Properties from bean3");
        System.out.println(someone3);
    }
}
