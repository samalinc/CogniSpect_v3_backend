package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.TestVariantModel;
import com.bsuir.cognispect.entity.TestVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {QuestionVariantMapper.class})
public abstract class TestVariantMapper {
    public abstract TestVariantModel entityToModel(TestVariant testVariant);

    public abstract List<TestVariantModel> entitiesToModels(
            Collection<TestVariant> testVariants);
}
