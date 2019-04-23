package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.SubjectDto;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.repository.SubjectRepository;
import com.bsuir.cognispect.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Subject createSubject(SubjectDto subjectDto) throws UniqueException {
        if (subjectRepository.existsByName(subjectDto.getName())) {
            throw new UniqueException("Subject with name: "
                    + subjectDto.getName() + " already exist");
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
        Optional<Subject> subjectOptional = subjectRepository.findSubjectById(
                subjectDto.getId());
        Subject subject = subjectOptional.orElseThrow(
                () -> new IllegalArgumentException("Subject with ID: "
                        + subjectDto.getId()
                        + " not found"));

        if (subjectRepository.existsByName(subjectDto.getName())) {
            throw new UniqueException("Subject with name: "
                    + subjectDto.getName()
                    + " already exist");
        }
        subject.setName(subjectDto.getName());

        return subjectRepository.save(subject);
    }
}
