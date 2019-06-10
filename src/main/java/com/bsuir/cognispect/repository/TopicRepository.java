package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Optional<Topic> findTopicByNameAndSubjectId(
            String topicName, UUID subjectId);

    boolean existsByName(String topicName);

    Optional<Topic> findTopicById(UUID topicId);

    @Query(value = "SELECT t.* FROM topic t " +
            "JOIN subject s ON t.subject_id = s.id " +
            "WHERE t.name ILIKE '%' || ?1 || '%' " +
            "AND s.name ILIKE '%' || ?2 || '%'",
            countQuery = "SELECT count(*) FROM topic",
            nativeQuery = true)
    Page<Topic> findTopicByNameAndSubjectName(
            String topicName, String subjectName, Pageable pageable);

    @Query(value = "SELECT COUNT(t)>0 FROM topic t " +
            "JOIN subject s on t.subject_id = ?2 " +
            "WHERE t.name=?1", nativeQuery = true)
    boolean existsTopicByNameUnderSubject(String topicName, UUID subjectId);
}
