package com.springBank.code.DAO;

import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String iban);
    Account findByOwner(User owner);
    Account findByTransactionId(Long transactionId);
}
