package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.model.create.CreateTopicModel;
import com.bsuir.cognispect.model.question.TopicModel;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.repository.TopicRepository;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Topic createTopic(CreateTopicModel createTopicModel) {
        Subject subject = subjectRepository
                .findSubjectById(createTopicModel.getSubjectId()).orElseThrow(
                        () -> new ResourceNotFoundException("Subject", createTopicModel.getSubjectId()));

        if (topicRepository.existsTopicByNameUnderSubject(
                createTopicModel.getName(), createTopicModel.getSubjectId())) {
            throw new UniqueException("Topic", "name", createTopicModel.getName());
        }

        Topic topic = new Topic();
        topic.setSubject(subject);
        topic.setName(createTopicModel.getName());

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
    public Page<Topic> getTopicsByFilter(String topicName, String subjectName,
                                         int page, int pageSize) {
        return topicRepository.findTopicByNameAndSubjectName(
                topicName, subjectName, PageRequest.of(page, pageSize));
    }
}
