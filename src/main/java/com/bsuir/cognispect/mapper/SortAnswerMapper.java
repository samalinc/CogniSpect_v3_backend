package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.SortAnswerDto;
import com.bsuir.cognispect.entity.SortAnswer;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SortAnswerMapper {
    public abstract SortAnswerDto entityToModel(
            SortAnswer sortAnswer);

    public abstract SortAnswer modelToEntity(
            SortAnswerDto sortAnswerDto);

    public abstract List<SortAnswerDto> entitiesToModels(
            Collection<SortAnswer> answers);

    public abstract List<SortAnswer> modelsToEntities(
            Collection<SortAnswerDto> answersDto);
}
