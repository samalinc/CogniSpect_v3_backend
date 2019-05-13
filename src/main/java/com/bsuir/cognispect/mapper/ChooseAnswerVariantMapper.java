package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.ChooseAnswerVariantDto;
import com.bsuir.cognispect.entity.ChooseAnswerVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class ChooseAnswerVariantMapper {
    public abstract ChooseAnswerVariantDto entityToModel(
            ChooseAnswerVariant chooseAnswerVariant);

    public abstract List<ChooseAnswerVariantDto> entitiesToModels(
            Collection<ChooseAnswerVariant> chooseAnswerVariants);
}
