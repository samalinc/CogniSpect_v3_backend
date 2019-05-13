package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.SortAnswerModel;
import com.bsuir.cognispect.entity.SortAnswer;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SortAnswerMapper {
    public abstract SortAnswerModel entityToModel(
            SortAnswer sortAnswer);

    public abstract SortAnswer modelToEntity(
            SortAnswerModel sortAnswerModel);

    public abstract List<SortAnswerModel> entitiesToModels(
            Collection<SortAnswer> answers);

    public abstract List<SortAnswer> modelsToEntities(
            Collection<SortAnswerModel> sortAnswerModels);
}
