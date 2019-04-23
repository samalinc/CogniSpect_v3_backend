package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.SubjectDto;
import com.bsuir.cognispect.entity.Subject;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring")
public abstract class SubjectMapper {
    public abstract SubjectDto subjectToSubjectDto(Subject subject);

    public abstract List<SubjectDto> subjectsToSubjectsDto(
            Collection<Subject> subjects);
}
