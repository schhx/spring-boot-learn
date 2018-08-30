package org.schhx.springbootlearn.service.impl;

import org.schhx.springbootlearn.config.SlaveDataSource;
import org.schhx.springbootlearn.dao.UserMapper;
import org.schhx.springbootlearn.module.User;
import org.schhx.springbootlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shanchao
 * @date 2018-08-30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User createUser(User user) {
        userMapper.insertSelective(user);
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    @SlaveDataSource
    public User getUserByIdFromSlave(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
