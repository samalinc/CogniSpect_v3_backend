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
            "LEFT JOIN student s ON a.id = s.account_id " +
            "LEFT JOIN teacher t ON a.id = t.account_id " +
            "WHERE cast(a.role_name AS TEXT) LIKE '%' || :roleName || '%' " +
            "AND ((t.first_name ILIKE '%' || :firstName ||'%' " +
            "AND t.last_name ILIKE '%' || :lastName || '%') " +
            "OR (s.first_name ILIKE '%' || :firstName ||'%' " +
            "AND s.last_name ILIKE '%' || :lastName || '%' " +
            "AND s.study_group LIKE '%' || :studyGroup || '%'))",
            nativeQuery = true)
    List<Account> findUsersByFilter(
            @Param("roleName") String roleName,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("studyGroup") String studyGroup);
}
