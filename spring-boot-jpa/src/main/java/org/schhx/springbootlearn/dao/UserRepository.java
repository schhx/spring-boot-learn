package org.schhx.springbootlearn.dao;

import org.schhx.springbootlearn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByUsername(String username);
}
