package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.model.ChooseAnswerModel;
import com.bsuir.cognispect.model.QuestionModel;
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
    public Question createQuestion(final QuestionModel questionModel) {
        Question question = new Question();

        question.setDescription(questionModel.getDescription());
        question.setTopic(topicRepository.findTopicById(questionModel.getTopic().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", questionModel.getTopic().getId())));
        question.setType(questionModel.getType());

        switch (questionModel.getType()) {
            case CHOOSE:
            case MULTICHOOSE:
                question.setAnswers(
                        answerService.createAnswers(
                                questionModel.getAnswers(),
                                question));
                break;
            case MATCH:
                question.setMatchAnswers(
                        answerService.createMatchAnswers(
                                questionModel.getMatchAnswers(),
                                question));
                break;
            case SORT:
                question.setSortAnswers(
                        answerService.createSortAnswers(
                                questionModel.getSortAnswers(),
                                question));
                break;
            case SUBSTITUTION:
                question.setAnswers(
                        answerService.createSubstitutionAnswers(
                                questionModel.getAnswers(),
                                questionModel.getSubstitutions(),
                                question));
                break;
        }

        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(final QuestionModel questionModel) {
        Question question = questionRepository.findQuestionById(questionModel.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Question", questionModel.getId()));
        if (!question.getType().equals(questionModel.getType())) {
            throw new IllegalArgumentException("Different type of questions");
        }
        question.setTopic(topicRepository.findTopicById(questionModel.getTopic().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", questionModel.getTopic().getId())));
        question.setDescription(questionModel.getDescription());

        switch (questionModel.getType()) {
            case CHOOSE:
            case MULTICHOOSE: {
                question.setAnswers(updateAnswersInQuestion(
                        questionModel.getAnswers(), question));
            }
            break;
        }
        questionRepository.save(question);

        return question;
    }

    private List<Answer> updateAnswersInQuestion(
            List<ChooseAnswerModel> chooseAnswerModels, Question question) {
        List<Answer> questionAnswers = question.getAnswers();
        List<Answer> updatedAnswersList = new ArrayList<>();

        for (ChooseAnswerModel chooseAnswerModel : chooseAnswerModels) {
            Answer newAnswer = null;
            boolean isAnswerFound = false;

            for (Answer answer : questionAnswers) {
                if (answer.getId().equals(chooseAnswerModel.getId())) {
                    newAnswer = answerService.updateAnswer(chooseAnswerModel);
                    questionAnswers.remove(answer);
                    isAnswerFound = true;
                    break;
                }
            }
            if (!isAnswerFound) {
                newAnswer = answerService.createAnswer(chooseAnswerModel, question);
            }
            updatedAnswersList.add(newAnswer);
        }
        questionAnswers.forEach(answer -> answerService.deleteChooseAnswer(answer.getId()));

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
