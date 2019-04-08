package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.AnswerDto;
import com.bsuir.cognispect.entity.Answer;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AnswerMapper {
    public abstract AnswerDto answerToAnswerDto(Answer answer);

    public abstract Answer answerDtoToAnswer(AnswerDto answerDto);

    public abstract List<AnswerDto> answersToAnswersDto(
            Collection<Answer> answers);

    public abstract List<Answer> answersDtoToAnswers(
            Collection<AnswerDto> answersDto);
}