package com.springBank.code.DAO;

import com.springBank.code.Entity.Account;
import com.springBank.code.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    User findByName(String name);
    List<User> findByAccount(Account account);
}
