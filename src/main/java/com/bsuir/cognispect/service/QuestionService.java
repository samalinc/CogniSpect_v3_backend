package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.QuestionModel;
import com.bsuir.cognispect.entity.Question;

import java.util.List;
import java.util.UUID;


public interface QuestionService {
    List<Question> getQuestionsByTopicId(UUID topicId);

    Question createQuestion(QuestionModel questionModel);

    Question updateQuestion(QuestionModel questionModel);

    Question deleteQuestionById(UUID questionId);

    Question getQuestionById(UUID questionId);

    List<Question> getQuestionsByFilter(String subjectName, String topicName);

    List<Question> getQuestionsBySubjectId(UUID subjectId);
}
