package com.bsuir.cognispect.mapper.answer;

import com.bsuir.cognispect.entity.ChooseAnswerVariant;
import com.bsuir.cognispect.model.answer.ChooseAnswerVariantForTestModel;
import com.bsuir.cognispect.model.answer.ChooseAnswerVariantModel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class ChooseAnswerVariantMapper {
    public abstract ChooseAnswerVariantModel entityToModel(
            ChooseAnswerVariant chooseAnswerVariant);

    public abstract List<ChooseAnswerVariantModel> entitiesToModels(
            Collection<ChooseAnswerVariant> chooseAnswerVariants);

    public abstract ChooseAnswerVariantForTestModel entityToModelForTest(
            ChooseAnswerVariant chooseAnswerVariant);

    public abstract List<ChooseAnswerVariantForTestModel> entitiesToModelsForTest(
            Collection<ChooseAnswerVariant> chooseAnswerVariants);
}
