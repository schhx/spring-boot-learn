package org.schhx.springbootlearn.controller;

import org.schhx.springbootlearn.module.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@CacheConfig(cacheNames = "user")
public class UserController {

    private static Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());

    @PostMapping("")
    @CachePut(cacheNames = "user", key = "#user.id")
    public User createUser(@RequestBody User user) {
        System.out.println("create: " + user);
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping("/{id}")
    @Cacheable(cacheNames = "user", key = "#id")
    public User getUser(@PathVariable String id) {
        System.out.println("get user by id: " + id);
        return users.get(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id")
    public void deleteUser(@PathVariable String id) {
        System.out.println("delete user by id: " + id);
        users.remove(id);
    }
}
