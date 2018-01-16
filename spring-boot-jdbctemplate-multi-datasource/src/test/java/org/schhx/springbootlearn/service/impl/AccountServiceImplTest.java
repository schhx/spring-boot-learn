package org.schhx.springbootlearn.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private AccountServiceImpl accountService;

    @Test
    @Transactional("accountTransactionManager")
    public void create() throws Exception {
        Account account =accountService.create("18888888888", "1234");
        System.out.println(account);
        Assert.assertNotEquals(null, account);
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void getById() throws Exception {
    }

}