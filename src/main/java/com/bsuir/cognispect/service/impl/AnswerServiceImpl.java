package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.mapper.answer.ChooseAnswerMapper;
import com.bsuir.cognispect.mapper.answer.MatchAnswerMapper;
import com.bsuir.cognispect.mapper.answer.SortAnswerMapper;
import com.bsuir.cognispect.mapper.question.SubstitutionMapper;
import com.bsuir.cognispect.model.answer.ChooseAnswerModel;
import com.bsuir.cognispect.model.answer.MatchAnswerModel;
import com.bsuir.cognispect.model.answer.SortAnswerModel;
import com.bsuir.cognispect.model.question.SubstitutionModel;
import com.bsuir.cognispect.repository.ChooseAnswerRepository;
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
    private ChooseAnswerMapper chooseAnswerMapper;
    @Autowired
    private MatchAnswerMapper matchAnswerMapper;
    @Autowired
    private SortAnswerMapper sortAnswerMapper;
    @Autowired
    private SubstitutionMapper substitutionMapper;
    @Autowired
    private ChooseAnswerRepository chooseAnswerRepository;
    @Autowired
    private MatchAnswerRepository matchAnswerRepository;
    @Autowired
    private SortAnswerRepository sortAnswerRepository;

    @Override
    public List<ChooseAnswer> createChooseAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            Question question) {
        if (chooseAnswerModels == null || question == null) {
            return null;
        }

        List<ChooseAnswer> answerList = chooseAnswerMapper.modelsToEntities(chooseAnswerModels);
        answerList.forEach(answer -> answer.setQuestion(question));

        return chooseAnswerRepository.saveAll(answerList);
    }

    @Override
    public List<ChooseAnswer> createSubstitutionAnswers(
            List<ChooseAnswerModel> chooseAnswerModels,
            List<SubstitutionModel> substitutionModels,
            Question question) {
        List<ChooseAnswer> answerList = chooseAnswerMapper.modelsToEntities(chooseAnswerModels);
        answerList.forEach(answer -> answer.setQuestion(question));

        question.setSubstitutions(
                createSubstitutions(substitutionModels, answerList, question));

        return chooseAnswerRepository.saveAll(answerList);
    }

    private List<Substitution> createSubstitutions(
            List<SubstitutionModel> substitutionModels,
            List<ChooseAnswer> chooseAnswers,
            Question question) {
        if (substitutionModels == null || chooseAnswers == null || question == null) {
            return null;
        }

        List<Substitution> substitutionList = new ArrayList<>();

        for (SubstitutionModel substitutionModel : substitutionModels) {
            Substitution substitution = new Substitution();
            substitution.setText(substitutionModel.getText());
            substitution.setQuestion(question);
            substitution.setRightChooseAnswer(chooseAnswers.stream()
                    .filter(answer -> answer.getText().equals(substitutionModel.getRightAnswer().getText()))
                    .findAny().orElseThrow(() -> new RuntimeException("Не найдён ответ для подстановки")));
            substitutionList.add(substitution);
        }

        return substitutionList;
    }

    @Override
    public List<MatchAnswer> createMatchAnswers(
            List<MatchAnswerModel> matchAnswerModels, Question question) {
        if (matchAnswerModels == null || question == null) {
            return null;
        }

        List<MatchAnswer> matchAnswerList = matchAnswerMapper.modelsToEntities(matchAnswerModels);
        matchAnswerList.forEach(matchAnswer -> matchAnswer.setQuestion(question));

        return matchAnswerRepository.saveAll(matchAnswerList);
    }

    @Override
    public List<SortAnswer> createSortAnswers(
            List<SortAnswerModel> sortAnswerModels, Question question) {
        if (sortAnswerModels == null || question == null) {
            return null;
        }

        List<SortAnswer> sortAnswerList = sortAnswerMapper.modelsToEntities(sortAnswerModels);
        sortAnswerList.forEach(sortAnswer -> sortAnswer.setQuestion(question));

        return sortAnswerList;
    }

    @Override
    public ChooseAnswer createChooseAnswer(ChooseAnswerModel chooseAnswerModel, Question question) {
        if (chooseAnswerModel == null || question == null) {
            return null;
        }

        ChooseAnswer chooseAnswer = chooseAnswerMapper.modelToEntity(chooseAnswerModel);
        chooseAnswer.setQuestion(question);

        return chooseAnswerRepository.save(chooseAnswer);
    }

    @Override
    public ChooseAnswer createSubstitutionAnswer(ChooseAnswerModel chooseAnswerModel, SubstitutionModel substitutionModel, Question question) {
        ChooseAnswer answerList = chooseAnswerMapper.modelToEntity(chooseAnswerModel);

        answerList.setQuestion(question);

        return chooseAnswerRepository.save(answerList);
    }

    private Substitution createSubstitution(SubstitutionModel substitutionModel,
                                            ChooseAnswer rightChooseAnswer,
                                            Question question) {
        if (substitutionModel == null || rightChooseAnswer == null || question == null) {
            return null;
        }

        Substitution substitution = substitutionMapper.modelToEntity(substitutionModel);
        substitution.setQuestion(question);
        substitution.setRightChooseAnswer(rightChooseAnswer);

        return substitution;
    }

    @Override
    public MatchAnswer createMatchAnswer(MatchAnswerModel matchAnswerModel, Question question) {
        if (matchAnswerModel == null || question == null) {
            return null;
        }

        MatchAnswer matchAnswer = matchAnswerMapper.modelToEntity(matchAnswerModel);
        matchAnswer.setQuestion(question);

        return matchAnswerRepository.save(matchAnswer);
    }

    @Override
    public SortAnswer createSortAnswer(SortAnswerModel sortAnswerModel, Question question) {
        if (sortAnswerModel == null || question == null) {
            return null;
        }

        SortAnswer sortAnswer = sortAnswerMapper.modelToEntity(sortAnswerModel);
        sortAnswer.setQuestion(question);

        return sortAnswerRepository.save(sortAnswer);
    }

    @Override
    public List<Answer> updateAnswersInQuestion(List<ChooseAnswerModel> chooseAnswerModels) {
        List<Answer> answers = new ArrayList<>();

        for (ChooseAnswerModel chooseAnswerModel : chooseAnswerModels) {
            answers.add(updateChooseAnswer(chooseAnswerModel));
        }

        return answers;
    }

    @Override
    public ChooseAnswer updateChooseAnswer(ChooseAnswerModel chooseAnswerModel) {
        ChooseAnswer answer = chooseAnswerRepository.findById(chooseAnswerModel.getId()).orElseThrow(
                () -> new ResourceNotFoundException("ChooseAnswer", chooseAnswerModel.getId()));

        answer.setCorrect(chooseAnswerModel.isCorrect());
        answer.setText(chooseAnswerModel.getText());

        return chooseAnswerRepository.save(answer);
    }

    @Override
    public MatchAnswer updateMatchAnswer(MatchAnswerModel matchAnswerModel) {
        MatchAnswer matchAnswer = matchAnswerRepository.findById(matchAnswerModel.getId()).orElseThrow(
                () -> new ResourceNotFoundException("MatchAnswer", matchAnswerModel.getId()));

        matchAnswer.setKey(matchAnswerModel.getKey());
        matchAnswer.setValue(matchAnswerModel.getValue());

        return matchAnswerRepository.save(matchAnswer);
    }

    @Override
    public SortAnswer updateSortAnswer(SortAnswerModel sortAnswerModel) {
        SortAnswer sortAnswer = sortAnswerRepository.findById(sortAnswerModel.getId()).orElseThrow(
                () -> new ResourceNotFoundException("SortAnswer", sortAnswerModel.getId()));

        sortAnswer.setPosition(sortAnswerModel.getPosition());
        sortAnswer.setText(sortAnswerModel.getText());

        return sortAnswerRepository.save(sortAnswer);
    }

    @Override
    public ChooseAnswer deleteChooseAnswer(UUID chooseAnswerId) {
        return chooseAnswerRepository.deleteAnswerById(chooseAnswerId).orElseThrow(
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
