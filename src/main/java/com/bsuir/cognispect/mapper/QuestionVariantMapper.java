package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.QuestionVariantModel;
import com.bsuir.cognispect.entity.ChooseAnswerVariant;
import com.bsuir.cognispect.entity.MatchAnswerVariant;
import com.bsuir.cognispect.entity.QuestionVariant;
import com.bsuir.cognispect.entity.SortAnswerVariant;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

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
                questionVariantModel.setChooseAnswers(chooseAnswerVariantMapper
                        .entitiesToModels(
                                (List<ChooseAnswerVariant>) (List<?>) questionVariant.getAnswers()));
                break;
            case SORT:
                questionVariantModel.setSortAnswers(sortAnswerVariantMapper
                        .entitiesToModels(
                                (List<SortAnswerVariant>) (List<?>) questionVariant.getAnswers()));
                break;
            case MATCH:
                questionVariantModel.setMatchAnswers(matchAnswerVariantMapper
                        .entitiesToModels(
                                (List<MatchAnswerVariant>) (List<?>) questionVariant.getAnswers()));
                break;
        }

        return questionVariantModel;
    }

    public abstract List<QuestionVariantModel> entitiesToModels(
            Collection<QuestionVariant> questionVariants);
}
