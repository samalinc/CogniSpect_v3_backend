package com.bsuir.cognispect.mapper.answer;

import com.bsuir.cognispect.entity.ChooseAnswer;
import com.bsuir.cognispect.model.answer.ChooseAnswerModel;
import com.bsuir.cognispect.entity.Answer;
import com.bsuir.cognispect.model.question.CreateChooseAnswerModel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class ChooseAnswerMapper {
    public abstract ChooseAnswerModel entityToModel(ChooseAnswer chooseAnswer);

    public abstract ChooseAnswer modelToEntity(ChooseAnswerModel chooseAnswerModel);

    public abstract ChooseAnswer modelToEntity(CreateChooseAnswerModel createChooseAnswerModel);

    public abstract List<ChooseAnswerModel> entitiesToModels(
            Collection<ChooseAnswer> answers);

    public abstract List<ChooseAnswer> modelsToEntities(
            Collection<ChooseAnswerModel> chooseAnswerModels);
}