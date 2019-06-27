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
import com.bsuir.cognispect.model.create.answer.CreateChooseAnswerModel;
import com.bsuir.cognispect.model.create.answer.CreateMatchAnswerModel;
import com.bsuir.cognispect.model.create.answer.CreateSortAnswerModel;
import com.bsuir.cognispect.model.create.answer.CreateSubstitutionModel;
import com.bsuir.cognispect.model.question.*;
import com.bsuir.cognispect.repository.ChooseAnswerRepository;
import com.bsuir.cognispect.repository.MatchAnswerRepository;
import com.bsuir.cognispect.repository.SortAnswerRepository;
import com.bsuir.cognispect.repository.SubstitutionRepository;
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
    @Autowired
    private SubstitutionRepository substitutionRepository;

    @Override
    public List<ChooseAnswer> createChooseAnswers(
            List<CreateChooseAnswerModel> createChooseAnswerModels,
            Question question) {
        if (createChooseAnswerModels == null || question == null) {
            return null;
        }

        List<ChooseAnswer> chooseAnswerList = new ArrayList<>();

        for (CreateChooseAnswerModel createChooseAnswerModel : createChooseAnswerModels) {
            chooseAnswerList.add(chooseAnswerMapper.modelToEntity(createChooseAnswerModel));
        }

        chooseAnswerList.forEach(answer -> answer.setQuestion(question));

        return chooseAnswerRepository.saveAll(chooseAnswerList);
    }

    @Override
    public List<ChooseAnswer> createSubstitutionAnswers(
            List<CreateChooseAnswerModel> createChooseAnswerModels,
            List<CreateSubstitutionModel> substitutionModels,
            Question question) {
        List<ChooseAnswer> chooseAnswerList = new ArrayList<>(createChooseAnswerModels.size());

        for (CreateChooseAnswerModel createChooseAnswerModel : createChooseAnswerModels) {
            chooseAnswerList.add(chooseAnswerMapper.modelToEntity(createChooseAnswerModel));
        }

        chooseAnswerList.forEach(answer -> answer.setQuestion(question));
        chooseAnswerRepository.saveAll(chooseAnswerList);

        List<Substitution> substitutions = createSubstitutions(substitutionModels, chooseAnswerList, question);
        substitutionRepository.saveAll(substitutions);
        question.setSubstitutions(substitutions);

        return chooseAnswerList;
    }

    private List<Substitution> createSubstitutions(
            List<CreateSubstitutionModel> createSubstitutionModels,
            List<ChooseAnswer> chooseAnswers,
            Question question) {
        if (createSubstitutionModels == null || chooseAnswers == null || question == null) {
            return null;
        }

        List<Substitution> substitutionList = new ArrayList<>();

        for (CreateSubstitutionModel createSubstitutionModel : createSubstitutionModels) {
            Substitution substitution = new Substitution();
            substitution.setText(createSubstitutionModel.getText());
            substitution.setQuestion(question);
            substitution.setRightChooseAnswer(chooseAnswers.stream()
                    .filter(answer -> answer.getText().equals(createSubstitutionModel.getRightAnswer()))
                    .findAny().orElseThrow(() -> new RuntimeException("Не найдён ответ для подстановки")));
            substitutionList.add(substitution);
        }

        return substitutionList;
    }

    @Override
    public List<MatchAnswer> createMatchAnswers(
            List<CreateMatchAnswerModel> matchAnswerModels, Question question) {
        if (matchAnswerModels == null || question == null) {
            return null;
        }

        List<MatchAnswer> matchAnswerList = new ArrayList<>();
        for (CreateMatchAnswerModel createMatchAnswerModel : matchAnswerModels) {
            matchAnswerList.add(matchAnswerMapper.modelToEntity(createMatchAnswerModel));
        }
        matchAnswerList.forEach(matchAnswer -> matchAnswer.setQuestion(question));

        return matchAnswerRepository.saveAll(matchAnswerList);
    }

    @Override
    public List<SortAnswer> createSortAnswers(
            List<CreateSortAnswerModel> createSortAnswerModels, Question question) {
        if (createSortAnswerModels == null || question == null) {
            return null;
        }

        List<SortAnswer> sortAnswers = new ArrayList<>();
        for (CreateSortAnswerModel createSortAnswerModel : createSortAnswerModels) {
            sortAnswers.add(sortAnswerMapper.modelToEntity(createSortAnswerModel));
        }
        sortAnswers.forEach(matchAnswer -> matchAnswer.setQuestion(question));

        return sortAnswers;
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
        ChooseAnswer chooseAnswer = chooseAnswerMapper.modelToEntity(chooseAnswerModel);

        chooseAnswer.setQuestion(question);
        chooseAnswer.setSubstitution(createSubstitution(substitutionModel, chooseAnswer, question));

        return chooseAnswerRepository.save(chooseAnswer);
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
