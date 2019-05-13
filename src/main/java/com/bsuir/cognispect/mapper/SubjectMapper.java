package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.SubjectModel;
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
