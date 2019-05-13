package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.model.TopicModel;
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
    public Topic createTopic(TopicModel topicModel) {
        Subject subject = subjectRepository
                .findSubjectById(topicModel.getSubject().getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Subject", topicModel.getSubject().getId()));

        if (topicRepository.existsTopicByNameUnderSubject(
                topicModel.getName(), topicModel.getSubject().getId())) {
            throw new UniqueException("Topic", "name", topicModel.getName());
        }

        Topic topic = new Topic();
        topic.setSubject(subject);
        topic.setName(topicModel.getName());

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
    public Topic updateExistingTopic(TopicModel topicModel) {
        Topic topic = topicRepository.findTopicById(topicModel.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Topic", topicModel.getId()));

        if (topicRepository.existsTopicByNameUnderSubject(
                topicModel.getName(), topic.getSubject().getId())) {
            throw new UniqueException("Topic", "name", topicModel.getName());
        }
        topic.setName(topicModel.getName());

        return topicRepository.save(topic);
    }

    @Override
    public Topic deleteTopicById(UUID topicId) {
        Topic topic = topicRepository.findTopicById(topicId).orElseThrow(
                () -> new ResourceNotFoundException("Topic", topicId));
        topicRepository.delete(topic);

        return topic;
    }

    @Override
    public List<Topic> getTopicsByFilter(String topicName, String subjectName) {
        return topicRepository.findTopicByNameAndSubjectName(
                topicName, subjectName);
    }
}
