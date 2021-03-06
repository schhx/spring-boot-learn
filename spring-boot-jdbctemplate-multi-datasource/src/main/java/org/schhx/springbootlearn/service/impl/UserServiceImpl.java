package org.schhx.springbootlearn.service.impl;

import org.schhx.springbootlearn.module.User;
import org.schhx.springbootlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userJdbcTemplate")
    private JdbcTemplate userJdbcTemplate;

    @Override
    public User create(String username, Integer age) {
        String id = UUID.randomUUID().toString();
        userJdbcTemplate.update("insert into user ( id, username, age ) values ( ?, ?, ? ) ", id, username, age);
        return getById(id);
    }

    @Override
    public void delete(String id) {
        userJdbcTemplate.update("delete from user where id = ?", id);
    }

    @Override
    public User getById(String id) {
        try {
            return userJdbcTemplate.queryForObject("select * from user where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
