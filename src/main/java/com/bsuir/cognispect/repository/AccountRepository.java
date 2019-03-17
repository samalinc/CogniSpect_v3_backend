package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
