package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.SubjectDto;
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
    public Subject createSubject(SubjectDto subjectDto) throws UniqueException {
        if (subjectRepository.existsByName(subjectDto.getName())) {
            throw new UniqueException("Subject", "name", subjectDto.getName());
        }
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());

        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getSubjectsByFilter(String subjectName) {
        return subjectRepository.findSubjectsByNameIsLike(subjectName);
    }

    @Override
    public Subject updateExistingSubject(SubjectDto subjectDto)
            throws IllegalArgumentException, UniqueException {
        Subject subject = subjectRepository.findSubjectById(subjectDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject", subjectDto.getId()));

        if (subjectRepository.existsByName(subjectDto.getName())) {
            throw new UniqueException("Subject", "name", subjectDto.getName());
        }
        subject.setName(subjectDto.getName());

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
