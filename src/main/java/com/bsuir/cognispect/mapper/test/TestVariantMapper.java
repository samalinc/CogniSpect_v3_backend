package com.bsuir.cognispect.mapper.test;

import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.mapper.question.QuestionVariantMapper;
import com.bsuir.cognispect.model.test.TestVariantForTestModel;
import com.bsuir.cognispect.model.test.TestVariantModel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {QuestionVariantMapper.class})
public abstract class TestVariantMapper {
    public abstract TestVariantModel entityToModel(TestVariant testVariant);

    public abstract List<TestVariantModel> entitiesToModels(
            Collection<TestVariant> testVariants);

    public abstract TestVariantForTestModel entityToModelForTest(
            TestVariant testVariant);

    public abstract List<TestVariantForTestModel> entitiesToModelsForTest(
            Collection<TestVariant> testVariants);
}
