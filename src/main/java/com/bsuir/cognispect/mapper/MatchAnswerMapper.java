package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.MatchAnswerDto;
import com.bsuir.cognispect.entity.MatchAnswer;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class MatchAnswerMapper {
    public abstract MatchAnswerDto entityToModel(
            MatchAnswer matchAnswer);

    public abstract MatchAnswer modelToEntity(
            MatchAnswerDto matchAnswerDto);

    public abstract List<MatchAnswerDto> entitiesToModels(
            Collection<MatchAnswer> matchAnswers);

    public abstract List<MatchAnswer> modelsToEntities(
            Collection<MatchAnswerDto> matchAnswersDto);
}
