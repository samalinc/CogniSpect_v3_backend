package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.TopicDto;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.repository.TopicRepository;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Optional<Topic> getTopicByNameAndSubjectId(
            String topicName, UUID subjectId) {
        return topicRepository
                .findTopicByNameAndSubjectId(topicName, subjectId);
    }

    @Override
    public Topic createTopic(TopicDto topicDto) {
        Subject subject = subjectRepository
                .findSubjectById(topicDto.getSubject().getId()).orElseThrow(
                        () -> new IllegalArgumentException("Subject with ID: "
                                + topicDto.getSubject().getId()
                                + " not found"));

        if (topicRepository.existsTopicByNameUnderSubject(
                topicDto.getName(), topicDto.getSubject().getId())) {
            throw new UniqueException("Topic with name: "
                    + topicDto.getName() + " already exist");
        }

        Topic topic = new Topic();
        topic.setSubject(subject);
        topic.setName(topicDto.getName());

        return topicRepository.save(topic);
    }

    @Override
    public Topic createTopicFromNameAndSubject(String topicName, Subject subject) {
        Topic topic = new Topic();
        topic.setSubject(subject);
        topic.setName(topicName);

        return topicRepository.save(topic);
    }

    @Override
    public Topic updateExistingTopic(TopicDto topicDto) {
        Topic topic = topicRepository.findTopicById(topicDto.getId()).orElseThrow(
                () -> new IllegalArgumentException("Topic with ID: " + topicDto.getId()
                        + " not found"));

        if (topicRepository.existsTopicByNameUnderSubject(
                topicDto.getName(), topic.getSubject().getId())) {
            throw new UniqueException("Topic with name: " + topicDto.getName()
                    + " already exist");
        }
        topic.setName(topicDto.getName());

        return topicRepository.save(topic);
    }

    @Override
    public Topic deleteTopicById(UUID topicId) {
        Topic topic = topicRepository.findTopicById(topicId).orElseThrow(
                () -> new ResourceNotFoundException("Topic with ID: " + topicId +
                        " not found"));
        topicRepository.delete(topic);

        return topic;
    }

    @Override
    public List<Topic> getTopicsByFilter(String topicName, String subjectName) {
        return topicRepository.findTopicByNameAndSubjectName(
                topicName, subjectName);
    }
}
