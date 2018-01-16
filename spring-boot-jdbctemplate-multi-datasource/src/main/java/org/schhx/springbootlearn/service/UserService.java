package org.schhx.springbootlearn.service;

import org.schhx.springbootlearn.module.User;

public interface UserService {

    User create(String username, Integer age);

    void delete(String id);

    User getById(String id);
}
