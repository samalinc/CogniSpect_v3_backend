package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.MatchAnswer;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.SortAnswer;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.model.answer.ChooseAnswerModel;
import com.bsuir.cognispect.model.answer.MatchAnswerModel;
import com.bsuir.cognispect.model.answer.SortAnswerModel;
import com.bsuir.cognispect.model.question.QuestionModel;
import com.bsuir.cognispect.repository.QuestionRepository;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.repository.TopicRepository;
import com.bsuir.cognispect.service.AnswerService;
import com.bsuir.cognispect.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
                        answerService.createChooseAnswers(
                                questionModel.getChooseAnswers(),
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
                                questionModel.getChooseAnswers(),
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
            case MULTICHOOSE:
                question.setAnswers(updateChooseAnswersInQuestion(
                        questionModel.getChooseAnswers(), question));
                break;
            case MATCH:
                question.setMatchAnswers(updateMatchAnswerInQuestion(
                        questionModel.getMatchAnswers(), question));
                break;
            case SORT:
                question.setSortAnswers(updateSortAnswerInQuestion(
                        questionModel.getSortAnswers(), question));
                break;
        }
        questionRepository.save(question);

        return question;
    }

    private List<Answer> updateChooseAnswersInQuestion(
            List<ChooseAnswerModel> chooseAnswerModels, Question question) {
        List<Answer> questionAnswers = question.getAnswers();
        List<Answer> updatedAnswersList = new ArrayList<>();

        for (ChooseAnswerModel chooseAnswerModel : chooseAnswerModels) {
            Answer newAnswer = null;
            boolean isAnswerFound = false;

            for (Answer answer : questionAnswers) {
                if (answer.getId().equals(chooseAnswerModel.getId())) {
                    newAnswer = answerService.updateChooseAnswer(chooseAnswerModel);
                    questionAnswers.remove(answer);
                    isAnswerFound = true;
                    break;
                }
            }
            if (!isAnswerFound) {
                newAnswer = answerService.createChooseAnswer(chooseAnswerModel, question);
            }
            updatedAnswersList.add(newAnswer);
        }
        questionAnswers.forEach(answer -> answerService.deleteChooseAnswer(answer.getId()));

        return updatedAnswersList;
    }

    private List<MatchAnswer> updateMatchAnswerInQuestion(
            List<MatchAnswerModel> matchAnswerModels, Question question) {
        List<MatchAnswer> questionAnswers = question.getMatchAnswers();
        List<MatchAnswer> updatedMatchAnswersList = new ArrayList<>();

        for (MatchAnswerModel matchAnswerModel : matchAnswerModels) {
            MatchAnswer newAnswer = null;
            boolean isAnswerFound = false;

            for (MatchAnswer answer : questionAnswers) {
                if (answer.getId().equals(matchAnswerModel.getId())) {
                    newAnswer = answerService.updateMatchAnswer(matchAnswerModel);
                    questionAnswers.remove(answer);
                    isAnswerFound = true;
                    break;
                }
            }
            if (!isAnswerFound) {
                newAnswer = answerService.createMatchAnswer(matchAnswerModel, question);
            }
            updatedMatchAnswersList.add(newAnswer);
        }
        questionAnswers.forEach(answer -> answerService.deleteMatchAnswer(answer.getId()));

        return updatedMatchAnswersList;
    }

    private List<SortAnswer> updateSortAnswerInQuestion(
            List<SortAnswerModel> sortAnswerModels, Question question) {
        List<SortAnswer> questionAnswers = question.getSortAnswers();
        List<SortAnswer> updatedSortAnswersList = new ArrayList<>();

        for (SortAnswerModel sortAnswerModel : sortAnswerModels) {
            SortAnswer newAnswer = null;
            boolean isAnswerFound = false;

            for (SortAnswer answer : questionAnswers) {
                if (answer.getId().equals(sortAnswerModel.getId())) {
                    newAnswer = answerService.updateSortAnswer(sortAnswerModel);
                    questionAnswers.remove(answer);
                    isAnswerFound = true;
                    break;
                }
            }
            if (!isAnswerFound) {
                newAnswer = answerService.createSortAnswer(sortAnswerModel, question);
            }
            updatedSortAnswersList.add(newAnswer);
        }
        questionAnswers.forEach(answer -> answerService.deleteSortAnswer(answer.getId()));

        return updatedSortAnswersList;
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
    public Page<Question> getQuestionsByFilter(
            String subjectName, String topicName, int page, int pageSize) {
        if (subjectName == null) {
            subjectName = "";
        }
        if (topicName == null) {
            topicName = "";
        }

        return questionRepository
                .findQuestionsBySubjectAndTopic(subjectName, topicName,
                        PageRequest.of(page, pageSize));
    }

    @Override
    public List<Question> getQuestionsBySubjectId(UUID subjectId) {
        return questionRepository.findQuestionsBySubjectId(subjectId);
    }
}
