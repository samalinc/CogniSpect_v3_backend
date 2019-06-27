package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.TestSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TestSessionRepository
        extends JpaRepository<TestSession, UUID> {
    Page<TestSession> findByNameContaining(String name, Pageable pageable);
}
