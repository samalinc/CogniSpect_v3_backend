package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.question.SubjectModel;
import com.bsuir.cognispect.entity.Subject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SubjectService {
    Optional<Subject> getSubjectByName(String subjectName);

    Subject createSubject(SubjectModel subjectModel);

    List<Subject> getSubjectsByFilter(String subjectName);

    Subject updateExistingSubject(SubjectModel subjectModel);

    Subject deleteSubjectById(UUID subjectId);
}
