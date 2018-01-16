package org.schhx.springbootlearn.service.impl;

import org.schhx.springbootlearn.module.Account;
import org.schhx.springbootlearn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    @Qualifier("accountJdbcTemplate")
    private JdbcTemplate accountJdbcTemplate;

    @Override
    public Account create(String cellphone, String password) {
        String id = UUID.randomUUID().toString();
        accountJdbcTemplate.update("insert into account(id, cellphone, password) values (?, ?, ?)", id, cellphone, password);
        return getById(id);
    }

    @Override
    public void delete(String id) {
        accountJdbcTemplate.update("delete from account where id = ?", id);
    }

    @Override
    public Account getById(String id) {
        try {
            return accountJdbcTemplate.queryForObject("select * from account where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Account.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
