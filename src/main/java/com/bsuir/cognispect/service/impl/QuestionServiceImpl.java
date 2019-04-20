package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.exception.TopicNotFoundException;
import com.bsuir.cognispect.repository.QuestionRepository;
import com.bsuir.cognispect.service.QuestionService;
import com.bsuir.cognispect.service.SubjectService;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TopicService topicService;
    @Autowired
    private SubjectService subjectService;

    @Override
    public List<Question> getQuestionsByTopic(final String topicName) {
        return questionRepository.findQuestionsByTopic(
                topicService.getTopicByName(topicName)
                        .orElseThrow(() -> new TopicNotFoundException(
                                "Topic with name: " + topicName + "not found")
                        )
        );
    }


    @Override
    public Question createQuestion(final QuestionDto questionDto) {
        Question question = new Question();

        question.setDescription(questionDto.getDescription());
        Subject subject = subjectService.getSubjectByName(questionDto.getSubject())
                .orElse(subjectService.createSubject(questionDto.getSubject()));

        Topic topic = topicService.getTopicByName(questionDto.getTopic())
                .orElse(topicService.createTopic(questionDto.getTopic(), subject));

        question.setTopic(topic);

        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(final Question question) {
        return null;
    }
}
