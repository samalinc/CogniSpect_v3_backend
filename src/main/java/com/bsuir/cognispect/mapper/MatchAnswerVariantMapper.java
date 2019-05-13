package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.MatchAnswerVariantModel;
import com.bsuir.cognispect.entity.MatchAnswerVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class MatchAnswerVariantMapper {
    public abstract MatchAnswerVariantModel entityToModel(
            MatchAnswerVariant matchAnswerVariant);

    public abstract List<MatchAnswerVariantModel> entitiesToModels(
            Collection<MatchAnswerVariant> matchAnswerVariants);
}
