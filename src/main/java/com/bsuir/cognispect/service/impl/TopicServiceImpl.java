package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.repository.TopicRepository;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Optional<Topic> getTopicByName(String topicName) {
        return topicRepository.findTopicByName(topicName);
    }

    @Override
    public Topic createTopic(String topicName, Subject subject) {
        Topic topic = new Topic();
        topic.setSubject(subject);
        topic.setName(topicName);

        return topicRepository.save(topic);
    }
}
