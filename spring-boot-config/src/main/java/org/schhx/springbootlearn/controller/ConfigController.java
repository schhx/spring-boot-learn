package org.schhx.springbootlearn.controller;

import org.schhx.springbootlearn.config.SomeoneProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Autowired
    private SomeoneProperties someoneProperties;

    @Value("${someone.name}")
    private String name;

    @Value("${someone.age}")
    private String age;

    @Value("${someone.best-friend}")
    private String bestFriend;


    @GetMapping("/print")
    public void printConfig(){
        System.out.println("Properties from @Value");
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("bestFriend = " + bestFriend);

        System.out.println();
        System.out.println("Properties from bean");
        System.out.println(someoneProperties);
    }
}
