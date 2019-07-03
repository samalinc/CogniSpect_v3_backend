package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.TestTemplateQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TestTemplateQuestionRepository
        extends JpaRepository<TestTemplateQuestion, UUID> {
    Optional<TestTemplateQuestion> findByQuestionId(UUID questionId);
}
