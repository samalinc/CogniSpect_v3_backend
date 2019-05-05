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
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectMapper subjectMapper;

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getSubjectsByFiler(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String subjectName) {

        return ResponseEntity.ok(
                subjectMapper.subjectsToSubjectsDto(
                        subjectService.getSubjectsByFilter(subjectName)));
    }

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(
            @RequestBody @Valid final SubjectDto subjectDto) {
        Subject subject = subjectService.createSubject(subjectDto);

        return new ResponseEntity<>(
                subjectMapper.subjectToSubjectDto(subject),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SubjectDto> updateSubject(
            @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectService.updateExistingSubject(subjectDto);

        return ResponseEntity.ok(subjectMapper.subjectToSubjectDto(subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubjectDto> deleteSubject(
            @PathVariable(name = "id") UUID subjectId) {
        Subject subject = subjectService.deleteSubjectById(subjectId);

        return ResponseEntity.ok(subjectMapper.subjectToSubjectDto(subject));
    }
}
