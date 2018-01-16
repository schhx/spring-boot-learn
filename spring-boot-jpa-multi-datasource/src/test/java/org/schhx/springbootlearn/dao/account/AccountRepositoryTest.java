package org.schhx.springbootlearn.dao.account;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional("accountTransactionManager")
    public void test() throws Exception {
        Account account = accountRepository.save(new Account("111", "10909090909", "1234"));
        System.out.println(account);
        Assert.assertNotEquals(null, account);
    }

}