package com.springBank.code.DAO;

import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<Users, Long> {
    Optional<Users> findByNome(String nome);

    Optional<Users> findById(Long id);

    List<Users> findByAccounts(Account account);
}
