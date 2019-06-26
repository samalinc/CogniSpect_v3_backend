package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.question.CreateQuestionModel;
import com.bsuir.cognispect.model.question.QuestionModel;
import com.bsuir.cognispect.entity.Question;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface QuestionService {
    List<Question> getQuestionsByTopicId(UUID topicId);

    Question createQuestion(CreateQuestionModel createQuestionModel);

    Question updateQuestion(QuestionModel questionModel);

    Question deleteQuestionById(UUID questionId);

    Question getQuestionById(UUID questionId);

    Page<Question> getQuestionsByFilter(String subjectName, String topicName, int page, int pageSize);

    List<Question> getQuestionsBySubjectId(UUID subjectId);
}
