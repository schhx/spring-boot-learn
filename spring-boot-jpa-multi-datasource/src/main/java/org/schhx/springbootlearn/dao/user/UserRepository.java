package org.schhx.springbootlearn.dao.user;

import org.schhx.springbootlearn.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
