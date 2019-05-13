package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.AnswerDto;
import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.entity.Question;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AnswerMapper {
    public abstract AnswerDto entityToModel(Answer answer);

    public abstract Answer modelToEntity(AnswerDto answerDto);

    public abstract List<AnswerDto> entitiesToModels(
            Collection<Answer> answers);

    public abstract List<Answer> modelsToEntities(
            Collection<AnswerDto> answersDto);
}