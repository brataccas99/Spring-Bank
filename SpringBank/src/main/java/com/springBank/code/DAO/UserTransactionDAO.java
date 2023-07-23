package com.springBank.code.DAO;

import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.UserTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTransactionDAO extends JpaRepository<UserTransactions, Long> {
    Optional<UserTransactions> findById(Long transactionId);
    UserTransactions findByAccountIban(String iban);
    UserTransactions findByAccount(Account account);
    UserTransactions findByDate(String transactionDate);

}
