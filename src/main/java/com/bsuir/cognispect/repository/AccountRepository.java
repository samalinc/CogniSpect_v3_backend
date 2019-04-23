package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    Optional<Account> findByLoginOrEmail(String login, String email);

    Optional<Account> findByLogin(String login);

    @Query(value = "SELECT a.* FROM account a " +
            "JOIN student s ON a.id = s.account_id " +
            "JOIN teacher t on a.id = t.account_id " +
            "WHERE a.role_name::text LIKE '%' || :role || '%' " +
            "AND ((t.first_name LIKE '%' || :firstName ||'%' " +
            "AND t.last_name LIKE '%' || :lastName || '%') " +
            "OR (s.first_name LIKE '%' || :firstName ||'%' " +
            "AND s.last_name LIKE '%' || :lastName || '%' " +
            "AND s.study_group LIKE '%' || :studyGroup || '%'))",
            nativeQuery = true)
    List<Account> findUsersByFilter(
            @Param("role") String userType,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("studyGroup") String studyGroup);
}
