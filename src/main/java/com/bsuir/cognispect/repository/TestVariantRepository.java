package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.TestSession;
import com.bsuir.cognispect.entity.TestVariant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TestVariantRepository
        extends JpaRepository<TestVariant, UUID> {
    List<TestVariant> findAllTestVariantsByStudentId(UUID studentId);

    Optional<TestVariant> findTestVariantByTestSessionIdAndStudentId(
            UUID testSessionId, UUID studentId);

    @Query(value = "SELECT t.* FROM test_variant t WHERE t.student_id = COALESCE(?1, t.student_id)",
            countQuery = "SELECT count(*) FROM test_variant",
            nativeQuery = true)
    Page<TestVariant> findTestVariantsByStudentId(UUID studentId, Pageable pageable);
}
