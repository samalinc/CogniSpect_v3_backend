package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Question;

import java.util.List;


public interface QuestionService {
    List<Question> getQuestionsByTopic(String topicName);

    Question createQuestion(QuestionDto questionDto);

    Question updateQuestion(Question question);

    List<Question> getQuestionsByFilter(String subjectName, String topicName);
}
