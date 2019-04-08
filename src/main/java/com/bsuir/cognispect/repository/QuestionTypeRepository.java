package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.QuestionType;
import com.bsuir.cognispect.entity.enums.QuestionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, UUID> {
    Optional<QuestionType> findQuestionTypeByQuestionType(QuestionEnum questionEnum);
}
