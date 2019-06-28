package com.bsuir.cognispect.mapper.question;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.mapper.answer.ChooseAnswerVariantMapper;
import com.bsuir.cognispect.mapper.answer.MatchAnswerVariantMapper;
import com.bsuir.cognispect.mapper.answer.SortAnswerVariantMapper;
import com.bsuir.cognispect.model.question.QuestionVariantForTestModel;
import com.bsuir.cognispect.model.question.QuestionVariantModel;
import com.bsuir.cognispect.repository.ChooseAnswerVariantRepository;
import com.bsuir.cognispect.repository.MatchAnswerVariantRepository;
import com.bsuir.cognispect.repository.SortAnswerVariantRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class QuestionVariantMapper {
    @Autowired
    private ChooseAnswerVariantMapper chooseAnswerVariantMapper;
    @Autowired
    private SortAnswerVariantMapper sortAnswerVariantMapper;
    @Autowired
    private MatchAnswerVariantMapper matchAnswerVariantMapper;
    @Autowired
    private ChooseAnswerVariantRepository chooseAnswerVariantRepository;
    @Autowired
    private SortAnswerVariantRepository sortAnswerVariantRepository;
    @Autowired
    private MatchAnswerVariantRepository matchAnswerVariantRepository;

    public QuestionVariantModel entityToModel(
            QuestionVariant questionVariant) {
        if (questionVariant == null) {
            return null;
        }

        QuestionVariantModel questionVariantModel = new QuestionVariantModel();

        questionVariantModel.setId(questionVariant.getId());
        questionVariantModel.setDescription(questionVariant.getDescription());
        questionVariantModel.setType(questionVariant.getType());
        questionVariantModel.setAnswered(questionVariant.isAnswered());

        switch (questionVariant.getType()) {
            case CHOOSE:
            case MULTICHOOSE:
            case SUBSTITUTION:
                List<ChooseAnswerVariant> chooseAnswerVariants = new ArrayList<>();

                for (AnswerVariant answerVariant : questionVariant.getAnswers()) {
                    chooseAnswerVariants.add(chooseAnswerVariantRepository.findById(answerVariant.getId()).get());
                }
                questionVariantModel.setChooseAnswers(
                        chooseAnswerVariantMapper.entitiesToModels(chooseAnswerVariants));
                break;
            case SORT:
                List<SortAnswerVariant> sortAnswerVariants = new ArrayList<>();

                for (AnswerVariant answerVariant : questionVariant.getAnswers()) {
                    sortAnswerVariants.add(sortAnswerVariantRepository.findById(answerVariant.getId()).get());
                }
                questionVariantModel.setSortAnswers(
                        sortAnswerVariantMapper.entitiesToModels(sortAnswerVariants));
                break;
            case MATCH:
                List<MatchAnswerVariant> matchAnswerVariants = new ArrayList<>();

                for (AnswerVariant answerVariant : questionVariant.getAnswers()) {
                    matchAnswerVariants.add(matchAnswerVariantRepository.findById(answerVariant.getId()).get());
                }
                questionVariantModel.setMatchAnswers(
                        matchAnswerVariantMapper.entitiesToModels(matchAnswerVariants));
                break;
        }

        return questionVariantModel;
    }

    public abstract List<QuestionVariantModel> entitiesToModels(
            Collection<QuestionVariant> questionVariants);

    public QuestionVariantForTestModel entityToModelForTest(
            QuestionVariant questionVariant) {
        if (questionVariant == null) {
            return null;
        }

        QuestionVariantForTestModel questionVariantForTestModel = new QuestionVariantForTestModel();

        questionVariantForTestModel.setId(questionVariant.getId());
        questionVariantForTestModel.setDescription(questionVariant.getDescription());
        questionVariantForTestModel.setType(questionVariant.getType());

        switch (questionVariant.getType()) {
            case CHOOSE:
            case MULTICHOOSE:
            case SUBSTITUTION:
                List<ChooseAnswerVariant> chooseAnswerVariants = new ArrayList<>();

                for (AnswerVariant answerVariant : questionVariant.getAnswers()) {
                    chooseAnswerVariants.add(chooseAnswerVariantRepository.findById(answerVariant.getId()).get());
                }
                questionVariantForTestModel.setChooseAnswers(
                        chooseAnswerVariantMapper.entitiesToModelsForTest(chooseAnswerVariants));
                break;
            case SORT:
                List<SortAnswerVariant> sortAnswerVariants = new ArrayList<>();

                for (AnswerVariant answerVariant : questionVariant.getAnswers()) {
                    sortAnswerVariants.add(sortAnswerVariantRepository.findById(answerVariant.getId()).get());
                }
                questionVariantForTestModel.setSortAnswers(
                        sortAnswerVariantMapper.entitiesToModelsForTest(sortAnswerVariants));
                break;
            case MATCH:
                List<MatchAnswerVariant> matchAnswerVariants = new ArrayList<>();

                for (AnswerVariant answerVariant : questionVariant.getAnswers()) {
                    matchAnswerVariants.add(matchAnswerVariantRepository.findById(answerVariant.getId()).get());
                }
                questionVariantForTestModel.setMatchAnswers(
                        matchAnswerVariantMapper.entitiesToModelsForTest(matchAnswerVariants));
                break;
        }

        return questionVariantForTestModel;
    }

    public abstract List<QuestionVariantForTestModel> entitiesToModelsForTest(
            Collection<QuestionVariant> questionVariants);
}
