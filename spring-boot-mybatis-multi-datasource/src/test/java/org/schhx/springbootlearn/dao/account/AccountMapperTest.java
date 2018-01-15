package org.schhx.springbootlearn.dao.account;

import com.github.pagehelper.PageHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.schhx.springbootlearn.module.account.Account;
import org.schhx.springbootlearn.module.account.AccountExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {

    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void countByExample() throws Exception {
    }

    @Test
    public void deleteByExample() throws Exception {
    }

    @Test
    public void deleteByPrimaryKey() throws Exception {
    }

    @Test
    @Transactional("accountTransactionManager")
    public void insert() throws Exception {
        String id = UUID.randomUUID().toString();
        Account account = new Account(id, "18888888888", "1234");
        int result = accountMapper.insert(account);
        Assert.assertEquals(1, result);
    }

    @Test
    public void insertSelective() throws Exception {
    }

    @Test
    @Transactional("accountTransactionManager")
    public void selectByExample() throws Exception {
        for (int i = 0; i < 10; i++) {
            accountMapper.insert(new Account("123" + i, "1888888888" + i, "1234" + i));
        }
        AccountExample example = new AccountExample();
        PageHelper.startPage(1, 5);
        List<Account> accountList = accountMapper.selectByExample(example);
        System.out.println(accountList);
        Assert.assertEquals(5, accountList.size());

    }

    @Test
    public void selectByPrimaryKey() throws Exception {
    }

    @Test
    public void updateByExampleSelective() throws Exception {
    }

    @Test
    public void updateByExample() throws Exception {
    }

    @Test
    public void updateByPrimaryKeySelective() throws Exception {
    }

    @Test
    public void updateByPrimaryKey() throws Exception {
    }

}