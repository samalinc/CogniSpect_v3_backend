package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.SubjectDto;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.mapper.SubjectMapper;
import com.bsuir.cognispect.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectMapper subjectMapper;

    @GetMapping("/filter")
    public ResponseEntity<?> getSubjectsByFiler(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String subjectName) {

        return ResponseEntity.ok(
                subjectMapper.subjectsToSubjectsDto(
                        subjectService.getSubjectsByFilter(subjectName)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSubject(
            @RequestBody @Valid final SubjectDto subjectDto) {
        Subject subject = subjectService.createSubject(subjectDto);

        return new ResponseEntity<>(
                subjectMapper.subjectToSubjectDto(
                        subject),
                HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSubject(
            @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectService.updateExistingSubject(subjectDto);

        return new ResponseEntity<>(
                subjectMapper.subjectToSubjectDto(
                        subject),
                HttpStatus.OK);
    }
}
