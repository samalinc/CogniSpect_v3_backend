package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.ChooseAnswerModel;
import com.bsuir.cognispect.entity.Answer;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class AnswerMapper {
    public abstract ChooseAnswerModel entityToModel(Answer answer);

    public abstract Answer modelToEntity(ChooseAnswerModel chooseAnswerModel);

    public abstract List<ChooseAnswerModel> entitiesToModels(
            Collection<Answer> answers);

    public abstract List<Answer> modelsToEntities(
            Collection<ChooseAnswerModel> chooseAnswerModels);
}