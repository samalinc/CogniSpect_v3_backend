package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.TestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TestTemplateRepository
        extends JpaRepository<TestTemplate, UUID> {
    Optional<TestTemplate> findById(UUID uuid);

    Page<TestTemplate> findByNameContaining(String name, Pageable pageable);
}
