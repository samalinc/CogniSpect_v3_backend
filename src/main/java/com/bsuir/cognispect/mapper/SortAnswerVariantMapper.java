package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.SortAnswerVariantDto;
import com.bsuir.cognispect.entity.SortAnswerVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SortAnswerVariantMapper {
    public abstract SortAnswerVariantDto sortAnswerVariantToSortAnswerVariantDto(
            SortAnswerVariant sortAnswerVariant);

    public abstract List<SortAnswerVariantDto> sortAnswerVariantsToSortAnswerVariantsDto(
            Collection<SortAnswerVariant> sortAnswerVariants);
}
