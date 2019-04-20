package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.TestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TestTemplateRepository
        extends JpaRepository<TestTemplate, UUID> {
}
