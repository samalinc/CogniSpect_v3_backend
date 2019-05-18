package com.bsuir.cognispect.mapper.question;

import com.bsuir.cognispect.mapper.answer.AnswerMapper;
import com.bsuir.cognispect.model.question.SubstitutionModel;
import com.bsuir.cognispect.entity.Substitution;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {AnswerMapper.class})
public abstract class SubstitutionMapper {
    public abstract SubstitutionModel entityToModel(
            Substitution substitution);

    public abstract Substitution modelToEntity(
            SubstitutionModel substitutionModel);

    public abstract List<SubstitutionModel> entitiesToModels(
            Collection<Substitution> substitutions);

    public abstract List<Substitution> modelsToEntities(
            Collection<SubstitutionModel> substitutions);
}
