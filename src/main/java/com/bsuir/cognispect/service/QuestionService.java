package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Question;

import java.util.List;
import java.util.UUID;


public interface QuestionService {
    List<Question> getQuestionsByTopicId(UUID topicId);

    Question createQuestion(QuestionDto questionDto);

    Question updateQuestion(QuestionDto questionDto);

    List<Question> getQuestionsByFilter(String subjectName, String topicName);

    List<Question> getQuestionsBySubjectId(UUID subjectId);
}
