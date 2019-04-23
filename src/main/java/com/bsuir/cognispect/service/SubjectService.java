package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.SubjectDto;
import com.bsuir.cognispect.entity.Subject;

import java.util.List;
import java.util.Optional;


public interface SubjectService {
    Optional<Subject> getSubjectByName(String subjectName);

    Subject createSubject(SubjectDto subjectDto);

    List<Subject> getSubjectsByFilter(String subjectName);

    Subject updateExistingSubject(SubjectDto subjectDto);
}
