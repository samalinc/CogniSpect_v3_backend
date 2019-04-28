package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.TestVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TestVariantRepository
        extends JpaRepository<TestVariant, UUID> {
    List<TestVariant> findAllTestVariantsByStudentId(UUID studentId);

    Optional<TestVariant> findTestVariantByTestSessionIdAndStudentId(
            UUID testSessionId, UUID studentId);
}
