package com.bsuir.cognispect.mapper.answer;

import com.bsuir.cognispect.model.answer.MatchAnswerModel;
import com.bsuir.cognispect.entity.MatchAnswer;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class MatchAnswerMapper {
    public abstract MatchAnswerModel entityToModel(
            MatchAnswer matchAnswer);

    public abstract MatchAnswer modelToEntity(
            MatchAnswerModel matchAnswerModel);

    public abstract List<MatchAnswerModel> entitiesToModels(
            Collection<MatchAnswer> matchAnswers);

    public abstract List<MatchAnswer> modelsToEntities(
            Collection<MatchAnswerModel> matchAnswerModels);
}
