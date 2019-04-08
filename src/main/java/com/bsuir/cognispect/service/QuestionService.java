package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.Question;

import java.util.List;


public interface QuestionService {
    List<Question> getQuestionsByTopic(String topicName);

    List<Question> getQuestionsBySubject(String subjectName);

    Question createQuestion(Question question);

    Question updateQuestion(Question question);
}
