package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Subject;
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
            nativeQuery = true)
    List<Subject> findSubjectsByNameIsLike(String subjectName);

    Optional<Subject> findSubjectById(UUID subjectId);
}
