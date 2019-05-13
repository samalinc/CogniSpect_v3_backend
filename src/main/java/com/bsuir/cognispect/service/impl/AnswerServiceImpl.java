package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.mapper.AnswerMapper;
import com.bsuir.cognispect.mapper.MatchAnswerMapper;
import com.bsuir.cognispect.mapper.SortAnswerMapper;
import com.bsuir.cognispect.model.ChooseAnswerModel;
import com.bsuir.cognispect.model.MatchAnswerModel;
import com.bsuir.cognispect.model.SortAnswerModel;
import com.bsuir.cognispect.model.SubstitutionModel;
import com.bsuir.cognispect.repository.AnswerRepository;
import com.bsuir.cognispect.repository.MatchAnswerRepository;
import com.bsuir.cognispect.repository.SortAnswerRepository;
import com.bsuir.cognispect.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private MatchAnswerMapper matchAnswerMapper;
    @Autowired
    private SortAnswerMapper sortAnswerMapper;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private MatchAnswerRepository matchAnswerRepository;
    @Autowired
    private SortAnswerRepository sortAnswerRepository;

    @Override
    public List<Answer> createAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            Question question) {
        if (chooseAnswerModels == null || question == null) {
            return null;
        }

        List<Answer> answerList = answerMapper.modelsToEntities(chooseAnswerModels);
        answerList.forEach(answer -> answer.setQuestion(question));

        return answerRepository.saveAll(answerList);
    }

    @Override
    public List<Answer> createSubstitutionAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            List<SubstitutionModel> substitutionModels,
            Question question) {
        List<Answer> answerList = answerMapper.modelsToEntities(chooseAnswerModels);
        answerList.forEach(answer -> answer.setQuestion(question));

        question.setSubstitutions(
                createSubstitutions(substitutionModels, answerList, question));

        return answerRepository.saveAll(answerList);
    }

    @Override
    public List<MatchAnswer> createMatchAnswers(
            List<MatchAnswerModel> matchAnswerModels, Question question) {
        if (matchAnswerModels == null || question == null) {
            return null;
        }

        List<MatchAnswer> matchAnswerList = matchAnswerMapper
                .modelsToEntities(matchAnswerModels);
        matchAnswerList.forEach(
                matchAnswer -> matchAnswer.setQuestion(question));

        return matchAnswerRepository.saveAll(matchAnswerList);
    }

    @Override
    public List<SortAnswer> createSortAnswers(
            List<SortAnswerModel> sortAnswerModels, Question question) {
        if (sortAnswerModels == null || question == null) {
            return null;
        }

        List<SortAnswer> sortAnswerList = sortAnswerMapper
                .modelsToEntities(sortAnswerModels);
        sortAnswerList.forEach(
                sortAnswer -> sortAnswer.setQuestion(question));

        return sortAnswerList;
    }

    private List<Substitution> createSubstitutions(
            List<SubstitutionModel> substitutionModels,
            List<Answer> answers,
            Question question) {
        if (substitutionModels == null || answers == null || question == null) {
            return null;
        }

        List<Substitution> substitutionList = new ArrayList<>();

        for (SubstitutionModel substitutionModel : substitutionModels) {
            Substitution substitution = new Substitution();
            substitution.setText(substitutionModel.getText());
            substitution.setQuestion(question);
            substitution.setRightAnswer(answers.stream()
                    .filter(answer -> answer.getText().equals(
                            substitutionModel.getRightAnswer().getText()))
                    .findAny().orElseThrow(RuntimeException::new));
            substitutionList.add(substitution);
        }

        return substitutionList;
    }

    @Override
    public List<Answer> updateAnswersInQuestion(List<ChooseAnswerModel> chooseAnswerModels) {
        List<Answer> answers = new ArrayList<>();

        for (ChooseAnswerModel chooseAnswerModel : chooseAnswerModels) {
            answers.add(updateAnswer(chooseAnswerModel));
        }

        return answers;
    }

    @Override
    public Answer updateAnswer(ChooseAnswerModel chooseAnswerModel) {
        Answer answer = answerRepository.findById(chooseAnswerModel.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Answer", chooseAnswerModel.getId()));
        answer.setCorrect(chooseAnswerModel.isCorrect());
        answer.setText(chooseAnswerModel.getText());

        return answerRepository.save(answer);
    }

    @Override
    public Answer createAnswer(ChooseAnswerModel chooseAnswerModel, Question question) {
        if (chooseAnswerModel == null || question == null) {
            return null;
        }

        Answer answer = answerMapper.modelToEntity(chooseAnswerModel);
        answer.setQuestion(question);

        return answerRepository.save(answer);
    }

    @Override
    public Answer deleteChooseAnswer(UUID chooseAnswerId) {
        return answerRepository.deleteAnswerById(chooseAnswerId).orElseThrow(
                () -> new ResourceNotFoundException("ChooseAnswer", chooseAnswerId));
    }

    @Override
    public SortAnswer deleteSortAnswer(UUID sortAnswerId) {
        return sortAnswerRepository.deleteSortAnswerById(sortAnswerId).orElseThrow(
                () -> new ResourceNotFoundException("SortAnswer", sortAnswerId));
    }

    @Override
    public MatchAnswer deleteMatchAnswer(UUID matchAnswerId) {
        return matchAnswerRepository.deleteMatchAnswerById(matchAnswerId).orElseThrow(
                () -> new ResourceNotFoundException("MatchAnswer", matchAnswerId));
    }
}
