package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.SortAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface SortAnswerRepository extends JpaRepository<SortAnswer, UUID> {
    @Transactional
    @Query(value = "DELETE FROM sort_answer s WHERE s.id=?1 RETURNING s.*", nativeQuery = true)
    Optional<SortAnswer> deleteSortAnswerById(UUID SortAnswerAnswerId);
}
