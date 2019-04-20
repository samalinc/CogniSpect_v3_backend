package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Optional<Subject> getSubjectByName(String subjectName) {
        return subjectRepository.findSubjectByName(subjectName);
    }

    @Override
    public Subject createSubject(String subjectName) {
        Subject subject = new Subject();
        subject.setName(subjectName);

        return subjectRepository.save(subject);
    }
}
