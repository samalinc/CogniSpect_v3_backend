package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.exception.SubjectNotFoundException;
import com.bsuir.cognispect.exception.TopicNotFoundException;
import com.bsuir.cognispect.repository.QuestionRepository;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.repository.TopicRepository;
import com.bsuir.cognispect.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Question> getQuestionsByTopic(final String topicName) {
        return questionRepository.findQuestionsByTopic(
                topicRepository.findTopicByName(topicName)
                        .orElseThrow(() -> new TopicNotFoundException(
                                "Topic with name: " + topicName + "not found")
                        )
        );
    }


    @Override
    public Question createQuestion(final Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(final Question question) {
        return null;
    }
}
