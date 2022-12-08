package com.example.task775.security.repo;

import com.example.task775.security.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByName(String name);
}
