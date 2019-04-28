package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.AnswerVariantDto;
import com.bsuir.cognispect.entity.AnswerVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AnswerVariantMapper {
    public abstract AnswerVariantDto answerVariantToAnswerVariantDto(AnswerVariant answerVariant);

    public abstract List<AnswerVariantDto> answerVariantsToAnswerVariantsDto(
            Collection<AnswerVariant> answerVariants);
}
