package com.bsuir.cognispect.mapper.answer;

import com.bsuir.cognispect.entity.SortAnswerVariant;
import com.bsuir.cognispect.model.answer.SortAnswerVariantForTestModel;
import com.bsuir.cognispect.model.answer.SortAnswerVariantModel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SortAnswerVariantMapper {
    public abstract SortAnswerVariantModel entityToModel(
            SortAnswerVariant sortAnswerVariant);

    public abstract List<SortAnswerVariantModel> entitiesToModels(
            Collection<SortAnswerVariant> sortAnswerVariants);

    public abstract SortAnswerVariantForTestModel entityToModelForTest(
            SortAnswerVariant sortAnswerVariant);

    public abstract List<SortAnswerVariantForTestModel> entitiesToModelsForTest(
            Collection<SortAnswerVariant> sortAnswerVariants);
}
