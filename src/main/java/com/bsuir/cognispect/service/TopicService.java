package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.model.create.CreateTopicModel;
import com.bsuir.cognispect.model.question.TopicModel;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;


public interface TopicService {
    Optional<Topic> getTopicByNameAndSubjectId(String topicName, UUID subjectId);

    Topic createTopic(CreateTopicModel createTopicModel);

    Topic createTopicFromNameAndSubject(String topicName, Subject subject);

    Topic updateExistingTopic(TopicModel topicModel);

    Topic deleteTopicById(UUID topicId);

    Page<Topic> getTopicsByFilter(String topicName, String subjectName, int page, int pageSize);
}
