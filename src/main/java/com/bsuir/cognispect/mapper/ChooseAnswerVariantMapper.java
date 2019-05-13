package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.ChooseAnswerVariantModel;
import com.bsuir.cognispect.entity.ChooseAnswerVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class ChooseAnswerVariantMapper {
    public abstract ChooseAnswerVariantModel entityToModel(
            ChooseAnswerVariant chooseAnswerVariant);

    public abstract List<ChooseAnswerVariantModel> entitiesToModels(
            Collection<ChooseAnswerVariant> chooseAnswerVariants);
}
