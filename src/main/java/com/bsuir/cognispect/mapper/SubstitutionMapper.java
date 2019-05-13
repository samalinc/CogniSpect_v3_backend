package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.SubstitutionDto;
import com.bsuir.cognispect.entity.Substitution;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {AnswerMapper.class})
public abstract class SubstitutionMapper {
    public abstract SubstitutionDto entityToModel(
            Substitution substitution);

    public abstract Substitution modelToEntity(
            SubstitutionDto substitutionDto);

    public abstract List<SubstitutionDto> entitiesToModels(
            Collection<Substitution> substitutions);

    public abstract List<Substitution> modelsToEntities(
            Collection<SubstitutionDto> substitutions);
}
