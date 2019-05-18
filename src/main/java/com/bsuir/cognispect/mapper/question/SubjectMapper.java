package com.bsuir.cognispect.mapper.question;

import com.bsuir.cognispect.model.question.SubjectModel;
import com.bsuir.cognispect.entity.Subject;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SubjectMapper {
    public abstract SubjectModel entityToModel(Subject subject);

    public abstract List<SubjectModel> entitiesToModels(
            Collection<Subject> subjects);
}
