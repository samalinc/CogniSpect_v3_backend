package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.question.TopicModel;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface TopicService {
    Optional<Topic> getTopicByNameAndSubjectId(String topicName, UUID subjectId);

    Topic createTopic(TopicModel topicModel);

    Topic createTopicFromNameAndSubject(String topicName, Subject subject);

    Topic updateExistingTopic(TopicModel topicModel);

    Topic deleteTopicById(UUID topicId);

    List<Topic> getTopicsByFilter(String topicName, String subjectName);
}
