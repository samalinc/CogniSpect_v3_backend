package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> findQuestionById(UUID questionId);

    List<Question> findQuestionsByTopic(Topic topic);
}
