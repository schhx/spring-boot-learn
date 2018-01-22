package org.schhx.springbootlearn.controller;

import org.schhx.springbootlearn.entity.User;
import org.schhx.springbootlearn.service.UserService;
import org.schhx.springbootlearn.vo.FindUsersReqVO;
import org.schhx.springbootlearn.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public User createUser(@RequestBody @Valid UserVO userVO) { //校验失败会抛出 MethodArgumentNotValidException
        return userService.createUser(userVO);
    }

    @GetMapping("")
    public List<User> findUsers(@Valid FindUsersReqVO reqVO){ //校验失败会抛出 BindException
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public User getUsersById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @GetMapping("/by/name")
    public User getUsersByName(String name){
        return userService.getUserByName(name);
    }
}
