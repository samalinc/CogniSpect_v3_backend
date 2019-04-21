package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> findQuestionById(UUID questionId);

    List<Question> findQuestionsByTopic(Topic topic);

    @Query(value = "SELECT question.* FROM question " +
            "JOIN topic ON question.topic_id=topic.id " +
            "JOIN subject ON topic.subject_id=subject.id " +
            "WHERE topic.name LIKE '%' || :topicName || '%' " +
            "AND subject.name LIKE '%' || :subjectName || '%'",
            nativeQuery = true)
    List<Question> findQuestionsBySubjectAndTopic(
            @Param("subjectName") String subjectName,
            @Param("topicName") String topicName);
}
