package org.schhx.springbootlearn.controller;

import org.schhx.springbootlearn.service.UserService;
import org.schhx.springbootlearn.vo.FindUsersReqVO;
import org.schhx.springbootlearn.vo.ResultVO;
import org.schhx.springbootlearn.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResultVO createUser(@RequestBody @Valid UserVO userVO) { //校验失败会抛出 MethodArgumentNotValidException
        return ResultVO.success(userService.createUser(userVO));
    }

    @GetMapping("")
    public ResultVO findUsers(@Valid FindUsersReqVO reqVO){ //校验失败会抛出 BindException
        return ResultVO.success(reqVO);
    }

    @GetMapping("/{id}")
    public ResultVO getUsersById(@PathVariable String id){
        return ResultVO.success(userService.getUserById(id));
    }

    @GetMapping("/by/name")
    public ResultVO getUsersByName(String name){
        return ResultVO.success(userService.getUserByName(name));
    }
}
