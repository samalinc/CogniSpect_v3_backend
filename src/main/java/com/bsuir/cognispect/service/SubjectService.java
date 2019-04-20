package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.Subject;

import java.util.Optional;

public interface SubjectService {
    Optional<Subject> getSubjectByName(String subjectName);

    Subject createSubject(String subjectName);
}
