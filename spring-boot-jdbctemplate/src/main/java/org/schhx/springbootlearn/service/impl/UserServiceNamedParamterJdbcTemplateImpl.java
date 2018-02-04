package org.schhx.springbootlearn.service.impl;

import org.schhx.springbootlearn.module.User;
import org.schhx.springbootlearn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceNamedParamterJdbcTemplateImpl implements UserService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 参数较少时可以使用 MapSqlParameterSource，参数较多时使用 BeanPropertySqlParameterSource 比较方便，特别是方法的入参是对象时
     *
     * @param username
     * @param age
     * @return
     */
    @Override
    public User create(String username, int age) {
        String id = UUID.randomUUID().toString();

//        MapSqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("id", id)
//                .addValue("username", username)
//                .addValue("age", age);

        User user = new User()
                .setId(id)
                .setUsername(username)
                .setAge(age);
        BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update("insert into user ( id, username, age ) values ( :id, :username, :age) ", parameters);

        return getById(id);
    }

    @Override
    public void delete(String id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        namedParameterJdbcTemplate.update("delete from user where id = :id", parameters);
    }

    @Override
    public User update(String id, String username, int age) {
//        MapSqlParameterSource parameters = new MapSqlParameterSource()
//                .addValue("id", id)
//                .addValue("username", username)
//                .addValue("age", age);

        User user = new User()
                .setId(id)
                .setUsername(username)
                .setAge(age);
        BeanPropertySqlParameterSource parameters = new BeanPropertySqlParameterSource(user);
        namedParameterJdbcTemplate.update("update user set username = :username, age = :age where id = :id", parameters);
        return getById(id);
    }

    @Override
    public User getById(String id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id);
        try {
            return namedParameterJdbcTemplate.queryForObject("select * from user where id = :id", parameters, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getUsersByAge(int age) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("age", age);
        return namedParameterJdbcTemplate.query("select * from user where age = :age", parameters, new BeanPropertyRowMapper<>(User.class));
    }
}
