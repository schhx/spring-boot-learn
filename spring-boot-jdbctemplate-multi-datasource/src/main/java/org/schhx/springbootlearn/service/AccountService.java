package org.schhx.springbootlearn.service;

import org.schhx.springbootlearn.module.Account;

public interface AccountService {

    Account create(String cellphone, String password);

    void delete(String id);

    Account getById(String id);
}
