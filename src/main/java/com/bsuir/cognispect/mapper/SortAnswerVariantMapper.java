package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.SortAnswerVariantModel;
import com.bsuir.cognispect.entity.SortAnswerVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SortAnswerVariantMapper {
    public abstract SortAnswerVariantModel entityToModel(
            SortAnswerVariant sortAnswerVariant);

    public abstract List<SortAnswerVariantModel> entitiesToModels(
            Collection<SortAnswerVariant> sortAnswerVariants);
}
