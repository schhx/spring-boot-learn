package org.schhx.springbootlearn.service.impl;

import org.schhx.springbootlearn.module.User;
import org.schhx.springbootlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceJdbcTemplateImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User create(String username, int age) {
        String id = UUID.randomUUID().toString();
        jdbcTemplate.update("insert into user ( id, username, age ) values ( ?, ?, ? ) ", id, username, age);
        return getById(id);
    }

    @Override
    public void delete(String id) {
        jdbcTemplate.update("delete from user where id = ?", id);
    }

    @Override
    public User update(String id, String username, int age) {
        jdbcTemplate.update("update user set username = ?, age = ? where id = ?", username, age, id);
        return getById(id);
    }

    /**
     *  获取一个对象时可能会报错，对象数为0是报 EmptyResultDataAccessException，对象数多于1个时报 IncorrectResultSizeDataAccessException
     * @param id
     * @return
     */
    @Override
    public User getById(String id) {
        try {
            return jdbcTemplate.queryForObject("select * from user where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByAge(int age) {
        return jdbcTemplate.query("select * from user where age = ?", new Object[]{age}, new BeanPropertyRowMapper<>(User.class));
    }
}
