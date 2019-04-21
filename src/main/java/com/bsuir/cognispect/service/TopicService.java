package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;

import java.util.Optional;
import java.util.UUID;

public interface TopicService {
    Optional<Topic> getTopicByNameAndSubjectId(String topicName, UUID subjectId);

    Topic createTopic(String topicName, Subject subject);
}
