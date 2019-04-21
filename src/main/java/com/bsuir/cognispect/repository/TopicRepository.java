package com.bsuir.cognispect.repository;

import com.bsuir.cognispect.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    Optional<Topic> findTopicByNameAndSubjectId(
            String topicName, UUID subjectId);

    boolean existsByName(String topicName);
}
