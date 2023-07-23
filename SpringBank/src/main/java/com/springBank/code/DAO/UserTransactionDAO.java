package com.springBank.code.DAO;

import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.UserTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionDAO extends JpaRepository<UserTransactions, Long> {
    UserTransactions findByTransactionId(Long transactionId);
    UserTransactions findByAccountNumber(String iban);
    UserTransactions findByAccount(Account account);
    UserTransactions findByTransactionDate(String transactionDate);

}
