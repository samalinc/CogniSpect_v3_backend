package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.model.SubjectModel;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Optional<Subject> getSubjectByName(String subjectName) {
        return subjectRepository.findSubjectByName(subjectName);
    }

    @Override
    public Subject createSubject(SubjectModel subjectModel) throws UniqueException {
        if (subjectRepository.existsByName(subjectModel.getName())) {
            throw new UniqueException("Subject", "name", subjectModel.getName());
        }
        Subject subject = new Subject();
        subject.setName(subjectModel.getName());

        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getSubjectsByFilter(String subjectName) {
        return subjectRepository.findSubjectsByNameIsLike(subjectName);
    }

    @Override
    public Subject updateExistingSubject(SubjectModel subjectModel)
            throws IllegalArgumentException, UniqueException {
        Subject subject = subjectRepository.findSubjectById(subjectModel.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject", subjectModel.getId()));

        if (subjectRepository.existsByName(subjectModel.getName())) {
            throw new UniqueException("Subject", "name", subjectModel.getName());
        }
        subject.setName(subjectModel.getName());

        return subjectRepository.save(subject);
    }

    @Override
    public Subject deleteSubjectById(UUID subjectId) {
        Subject subject = subjectRepository.findSubjectById(subjectId).orElseThrow(
                () -> new ResourceNotFoundException("Topic", subjectId));
        subjectRepository.delete(subject);

        return subject;
    }
}
