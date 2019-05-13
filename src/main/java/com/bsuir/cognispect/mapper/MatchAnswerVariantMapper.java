package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.MatchAnswerVariantDto;
import com.bsuir.cognispect.entity.MatchAnswerVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class MatchAnswerVariantMapper {
    public abstract MatchAnswerVariantDto entityToModel(
            MatchAnswerVariant matchAnswerVariant);

    public abstract List<MatchAnswerVariantDto> entitiesToModels(
            Collection<MatchAnswerVariant> matchAnswerVariants);
}
