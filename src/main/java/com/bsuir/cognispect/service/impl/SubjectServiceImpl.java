package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject getSubjectByName(String subjectName) {
        return subjectRepository.findSubjectByName(subjectName)
                .orElseGet(() -> {
                    Subject subject = new Subject();
                    subject.setName(subjectName);
                    return subjectRepository.save(subject);
                });
    }
}
