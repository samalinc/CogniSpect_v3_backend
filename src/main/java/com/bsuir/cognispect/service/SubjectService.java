package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.question.SubjectModel;
import com.bsuir.cognispect.entity.Subject;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface SubjectService {
    Optional<Subject> getSubjectByName(String subjectName);

    Subject createSubject(SubjectModel subjectModel);

    Page<Subject> getSubjectsByFilter(String subjectName, int page, int pageSize);

    Subject updateExistingSubject(SubjectModel subjectModel);

    Subject deleteSubjectById(UUID subjectId);
}
