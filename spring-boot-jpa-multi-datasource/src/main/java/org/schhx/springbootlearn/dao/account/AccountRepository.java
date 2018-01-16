package org.schhx.springbootlearn.dao.account;

import org.schhx.springbootlearn.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
