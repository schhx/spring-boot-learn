package org.schhx.springbootlearn.service;

import org.schhx.springbootlearn.module.User;

/**
 * @author shanchao
 * @date 2018-08-30
 */
public interface UserService {

    User createUser(User user);

    User getUserById(String id);

    User getUserByIdFromSlave(String id);
}
