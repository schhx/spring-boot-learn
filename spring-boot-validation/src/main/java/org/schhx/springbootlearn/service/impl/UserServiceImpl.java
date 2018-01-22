package org.schhx.springbootlearn.service.impl;

import org.schhx.springbootlearn.entity.User;
import org.schhx.springbootlearn.service.UserService;
import org.schhx.springbootlearn.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());

    @Override
    public User createUser(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        String id = UUID.randomUUID().toString();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    @Override
    public User getUserById(String id) {
        return users.get(id);
    }

    @Override
    public User getUserByName(String name) {
        return new User();
    }
}
