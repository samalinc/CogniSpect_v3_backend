package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.AnswerDto;
import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.repository.QuestionRepository;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.repository.TopicRepository;
import com.bsuir.cognispect.service.AnswerService;
import com.bsuir.cognispect.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private SubjectRepository subjectRepository;
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
        question.setTopic(topicRepository.findTopicById(questionDto.getTopic().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", questionDto.getTopic().getId())));
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
        Question question = questionRepository.findQuestionById(questionDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Question", questionDto.getId()));
        if (!question.getType().equals(questionDto.getType())) {
            throw new IllegalArgumentException("Different type of questions");
        }
        question.setTopic(topicRepository.findTopicById(questionDto.getTopic().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", questionDto.getTopic().getId())));
        question.setDescription(questionDto.getDescription());

        switch (questionDto.getType()) {
            case CHOOSE:
            case MULTICHOOSE: {
                question.setAnswers(updateAnswersInQuestion(
                        questionDto.getAnswers(), question));
            }
            break;
        }
        questionRepository.save(question);

        return question;
    }

    private List<Answer> updateAnswersInQuestion(
            List<AnswerDto> answersDto, Question question) {
        List<Answer> questionAnswers = question.getAnswers();
        List<Answer> updatedAnswersList = new ArrayList<>();

        for (AnswerDto answerDto : answersDto) {
            Answer newAnswer = null;
            boolean isAnswerFound = false;

            for (Answer answer : questionAnswers) {
                if (answer.getId().equals(answerDto.getId())) {
                    newAnswer = answerService.updateAnswer(answerDto);
                    questionAnswers.remove(answer);
                    isAnswerFound = true;
                    break;
                }
            }
            if (!isAnswerFound) {
                newAnswer = answerService.createAnswer(answerDto, question);
            }
            updatedAnswersList.add(newAnswer);
        }
        questionAnswers.forEach(answer -> answerService.deleteAnswer(answer.getId()));

        return updatedAnswersList;
    }

    @Override
    public Question deleteQuestionById(UUID questionId) {
        Question question = questionRepository.findQuestionById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question", questionId));
        questionRepository.delete(question);

        return question;
    }

    @Override
    public Question getQuestionById(UUID questionId) {
        return questionRepository.findQuestionById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question", questionId));
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
