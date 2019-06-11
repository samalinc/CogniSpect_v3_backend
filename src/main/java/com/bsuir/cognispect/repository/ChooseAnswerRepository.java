package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.ChooseAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ChooseAnswerRepository extends JpaRepository<ChooseAnswer, UUID> {
    @Transactional
    @Query(value = "DELETE FROM choose_answer c WHERE c.id=?1 RETURNING c.*", nativeQuery = true)
    Optional<ChooseAnswer> deleteAnswerById(UUID chooseAnswerId);
}
