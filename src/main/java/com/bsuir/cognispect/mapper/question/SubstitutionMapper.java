package com.bsuir.cognispect.mapper.question;

import com.bsuir.cognispect.entity.Substitution;
import com.bsuir.cognispect.mapper.answer.ChooseAnswerMapper;
import com.bsuir.cognispect.model.question.SubstitutionModel;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SubstitutionMapper {
    @Autowired
    private ChooseAnswerMapper chooseAnswerMapper;

    public SubstitutionModel entityToModel(
            Substitution substitution) {
        if (substitution == null) {
            return null;
        }

        SubstitutionModel substitutionModel = new SubstitutionModel();

        substitutionModel.setId(substitution.getId());
        substitutionModel.setText(substitution.getText());
        substitutionModel.setRightAnswer(chooseAnswerMapper.entityToModel(substitution.getRightChooseAnswer()));
        return substitutionModel;
    }

    public abstract Substitution modelToEntity(
            SubstitutionModel substitutionModel);

    public abstract List<SubstitutionModel> entitiesToModels(
            Collection<Substitution> substitutions);

    public abstract List<Substitution> modelsToEntities(
            Collection<SubstitutionModel> substitutions);
}
