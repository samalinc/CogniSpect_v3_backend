package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.MatchAnswer;
import com.bsuir.cognispect.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> findQuestionById(UUID questionId);

    List<Question> findQuestionsByTopicId(UUID topicId);

    @Query(value = "SELECT question.* FROM question " +
            "JOIN topic ON question.topic_id=topic.id " +
            "JOIN subject ON topic.subject_id=subject.id " +
            "WHERE topic.name LIKE '%' || :topicName || '%' " +
            "AND subject.name LIKE '%' || :subjectName || '%'",
            nativeQuery = true)
    List<Question> findQuestionsBySubjectAndTopic(
            @Param("subjectName") String subjectName,
            @Param("topicName") String topicName);

    @Query(value = "SELECT q.* FROM question q " +
            "JOIN topic t on q.topic_id = t.id " +
            "JOIN subject s on t.subject_id = ?1",
            nativeQuery = true)
    List<Question> findQuestionsBySubjectId(UUID subjectId);

    @Transactional
    @Query(value = "DELETE FROM question q WHERE q.id=?1 RETURNING q.*", nativeQuery = true)
    Optional<Question> deleteQuestionById(UUID questionId);
}
