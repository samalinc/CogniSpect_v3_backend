package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;

import java.util.Optional;

public interface TopicService {
    Optional<Topic> getTopicByName(String topicName);

    Topic createTopic(String topicName, Subject subject);
}
