package com.bsuir.cognispect.mapper.answer;

import com.bsuir.cognispect.entity.MatchAnswerVariant;
import com.bsuir.cognispect.model.answer.MatchAnswerVariantForTestModel;
import com.bsuir.cognispect.model.answer.MatchAnswerVariantModel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class MatchAnswerVariantMapper {
    public abstract MatchAnswerVariantModel entityToModel(
            MatchAnswerVariant matchAnswerVariant);

    public abstract List<MatchAnswerVariantModel> entitiesToModels(
            Collection<MatchAnswerVariant> matchAnswerVariants);

    public abstract MatchAnswerVariantForTestModel entityToModelForTest(
            MatchAnswerVariant matchAnswerVariant);

    public abstract List<MatchAnswerVariantForTestModel> entitiesToModelsForTest(
            Collection<MatchAnswerVariant> matchAnswerVariants);
}
