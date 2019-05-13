package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.QuestionVariantDto;
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

    public QuestionVariantDto entityToModel(
            QuestionVariant questionVariant) {
        if (questionVariant == null) {
            return null;
        }

        QuestionVariantDto questionVariantDto = new QuestionVariantDto();

        questionVariantDto.setId(questionVariant.getId());
        questionVariantDto.setDescription(questionVariant.getDescription());
        questionVariantDto.setType(questionVariant.getType());
        questionVariantDto.setAnswered(questionVariant.isAnswered());

        switch (questionVariant.getType()) {
            case CHOOSE:
            case MULTICHOOSE:
            case SUBSTITUTION:
                questionVariantDto.setChooseAnswers(chooseAnswerVariantMapper
                        .entitiesToModels(
                                (List<ChooseAnswerVariant>) (List<?>) questionVariant.getAnswers()));
                break;
            case SORT:
                questionVariantDto.setSortAnswers(sortAnswerVariantMapper
                        .entitiesToModels(
                                (List<SortAnswerVariant>) (List<?>) questionVariant.getAnswers()));
                break;
            case MATCH:
                questionVariantDto.setMatchAnswers(matchAnswerVariantMapper
                        .entitiesToModels(
                                (List<MatchAnswerVariant>) (List<?>) questionVariant.getAnswers()));
                break;
        }

        return questionVariantDto;
    }

    public abstract List<QuestionVariantDto> entitiesToModels(
            Collection<QuestionVariant> questionVariants);
}
