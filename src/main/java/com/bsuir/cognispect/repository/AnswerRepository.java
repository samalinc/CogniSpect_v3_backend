package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    @Transactional
    @Query(value = "DELETE FROM answer a WHERE a.id=?1 RETURNING a.*", nativeQuery = true)
    Optional<Answer> deleteAnswerById(UUID chooseAnswerId);
}
