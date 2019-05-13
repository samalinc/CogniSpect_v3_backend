package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.SubjectDto;
import com.bsuir.cognispect.entity.Subject;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SubjectMapper {
    public abstract SubjectDto entityToModel(Subject subject);

    public abstract List<SubjectDto> entitiesToModels(
            Collection<Subject> subjects);
}
