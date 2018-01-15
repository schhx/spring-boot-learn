package org.schhx.springbootlearn.dao;

import org.schhx.springbootlearn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findFirstByUsername(String username);
}
