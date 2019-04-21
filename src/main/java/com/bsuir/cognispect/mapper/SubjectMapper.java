package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.SubjectDto;
import com.bsuir.cognispect.entity.Subject;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class SubjectMapper {
    public abstract SubjectDto subjectToSubjectDto(Subject subject);
}
