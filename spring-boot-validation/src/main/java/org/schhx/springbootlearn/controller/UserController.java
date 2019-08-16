package org.schhx.springbootlearn.controller;

import org.schhx.springbootlearn.entity.User;
import org.schhx.springbootlearn.group.Insert;
import org.schhx.springbootlearn.group.Update;
import org.schhx.springbootlearn.validation.BeanNotEmpty;
import org.schhx.springbootlearn.vo.FindUsersReqVO;
import org.schhx.springbootlearn.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Validated //用于方法级别的校验
public class UserController {
    private static Map<String, User> users = new ConcurrentHashMap<>();

    @PostMapping("")
    public User createUser(@RequestBody @Validated({Insert.class}) UserVO userVO) { //校验失败会抛出 MethodArgumentNotValidException
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        String id = UUID.randomUUID().toString();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @PatchMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody @Validated({Update.class}) @BeanNotEmpty UserVO userVO) {
        User user = users.get(id);
        BeanUtils.copyProperties(userVO, user);
        return user;
    }

    @GetMapping("")
    public List<User> findUsers(@Valid FindUsersReqVO reqVO) { //校验失败会抛出 BindException
        return getUsersByName(reqVO.getUsername());
    }

    @GetMapping("/{id}")
    public User getUsersById(@PathVariable String id) {
        return users.get(id);
    }

    @GetMapping("/by/name")
    public List<User> getUsersByName(@NotEmpty(message = "姓名不能为空") String name) {//校验失败会抛出 ConstraintViolationException
        return users.values().stream()
                .filter(user -> name.equals(user.getUsername()))
                .collect(Collectors.toList());
    }
}
