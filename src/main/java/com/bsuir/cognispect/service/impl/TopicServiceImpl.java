package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.repository.TopicRepository;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic getTopicByName(String topicName) {
        return topicRepository.findTopicByName(topicName)
                .orElseGet(() -> {
                    Topic topic = new Topic();
                    topic.setName(topicName);
                    return topicRepository.save(topic);
                });
    }
}
