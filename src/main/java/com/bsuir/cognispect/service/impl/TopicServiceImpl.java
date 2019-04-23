package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.TopicDto;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
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
        if (topicRepository.existsByName(topicDto.getName())) {
            throw new UniqueException("Topic with name: "
                    + topicDto.getName() + " already exist");
        }
        Optional<Subject> subjectOptional = subjectRepository.findSubjectById(
                topicDto.getSubject().getId());

        Topic topic = new Topic();
        topic.setSubject(subjectOptional.orElseThrow(
                () -> new IllegalArgumentException("Subject with ID: "
                        + topicDto.getSubject().getId()
                        + " not found")
        ));
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
        Optional<Topic> topicOptional =
                topicRepository.findTopicById(topicDto.getId());
        Topic topic = topicOptional.orElseThrow(
                () -> new IllegalArgumentException("Topic with ID: "
                        + topicDto.getId()
                        + " not found"));

        if (topicRepository.existsByName(topicDto.getName())) {
            throw new UniqueException("Topic with name: "
                    + topicDto.getName()
                    + " already exist");
        }
        topic.setName(topicDto.getName());

        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> getTopicsByFilter(String topicName, String subjectName) {
        return topicRepository.findTopicByNameAndSubjectName(
                topicName, subjectName);
    }
}
