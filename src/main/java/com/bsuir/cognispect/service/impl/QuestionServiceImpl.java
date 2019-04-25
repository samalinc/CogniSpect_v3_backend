package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.repository.QuestionRepository;
import com.bsuir.cognispect.service.AnswerService;
import com.bsuir.cognispect.service.QuestionService;
import com.bsuir.cognispect.service.SubjectService;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TopicService topicService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private AnswerService answerService;

    @Override
    public List<Question> getQuestionsByTopicId(UUID topicId) {
        return questionRepository.findQuestionsByTopicId(topicId);
    }

    @Override
    public Question createQuestion(final QuestionDto questionDto) {
        Question question = new Question();

        question.setDescription(questionDto.getDescription());
        Subject subject = subjectService
                .getSubjectByName(
                        questionDto.getTopic().getSubject().getName())
                .orElseGet(() -> subjectService.createSubject(
                        questionDto.getTopic().getSubject()));

        Topic topic = topicService
                .getTopicByNameAndSubjectId(
                        questionDto.getTopic().getName(), subject.getId())
                .orElseGet(() -> topicService.createTopicFromNameAndSubject(
                        questionDto.getTopic().getName(), subject));

        question.setTopic(topic);
        question.setType(questionDto.getType());

        switch (questionDto.getType()) {
            case CHOOSE:
            case MULTICHOOSE:
                question.setAnswers(
                        answerService.createAnswersWithQuestion(
                                questionDto.getAnswers(),
                                question));
                break;
            case MATCH:
                question.setMatchAnswers(
                        answerService.createMatchAnswerWithQuestion(
                                questionDto.getMatchAnswers(),
                                question));
                break;
            case SORT:
                question.setSortAnswers(
                        answerService.createSortAnswerWithQuestion(
                                questionDto.getSortAnswers(),
                                question));
                break;
            case SUBSTITUTION:
                question.setAnswers(
                        answerService.createSubstitutionAnswersWithQuestion(
                                questionDto.getAnswers(),
                                questionDto.getSubstitutions(),
                                question));
                break;
        }


        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(final QuestionDto questionDto) {
        return null;
    }

    @Override
    public List<Question> getQuestionsByFilter(
            String subjectName, String topicName) {
        if (subjectName == null) {
            subjectName = "";
        }
        if (topicName == null) {
            topicName = "";
        }

        return questionRepository
                .findQuestionsBySubjectAndTopic(subjectName, topicName);
    }

    @Override
    public List<Question> getQuestionsBySubjectId(UUID subjectId) {
        return questionRepository.findQuestionsBySubjectId(subjectId);
    }
}
