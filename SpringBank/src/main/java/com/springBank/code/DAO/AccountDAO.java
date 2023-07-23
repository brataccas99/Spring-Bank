package com.springBank.code.DAO;

import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {
    Account findByIban(String iban);

    Optional<Account> findById(Long id);

    Account findByOwner(Users owner);
}
