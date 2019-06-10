package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    Optional<Subject> findSubjectByName(String subjectName);

    boolean existsByName(String subjectName);

    @Query(value = "SELECT s.* FROM subject s " +
            "WHERE s.name ILIKE '%' || ?1 || '%'",
            countQuery = "SELECT count(*) FROM subject",
            nativeQuery = true)
    Page<Subject> findSubjectsByNameIsLike(String subjectName, Pageable pageable);

    Optional<Subject> findSubjectById(UUID subjectId);
}
