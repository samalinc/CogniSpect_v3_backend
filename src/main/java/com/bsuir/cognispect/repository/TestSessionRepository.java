package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.TestSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TestSessionRepository
        extends JpaRepository<TestSession, UUID> {
    Page<TestSession> findByNameContaining(String name, Pageable pageable);

    @Transactional
    @Query(value = "DELETE FROM test_session t WHERE t.id=?1 RETURNING t.*", nativeQuery = true)
    Optional<TestSession> deleteTestSessionById(UUID testSessionId);
}
