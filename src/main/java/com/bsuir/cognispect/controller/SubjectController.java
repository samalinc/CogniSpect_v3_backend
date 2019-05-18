package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.model.question.SubjectModel;
import com.bsuir.cognispect.entity.Subject;
import com.bsuir.cognispect.mapper.question.SubjectMapper;
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
    public ResponseEntity<List<SubjectModel>> getSubjectsByFiler(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String subjectName) {

        return ResponseEntity.ok(
                subjectMapper.entitiesToModels(
                        subjectService.getSubjectsByFilter(subjectName)));
    }

    @PostMapping
    public ResponseEntity<SubjectModel> createSubject(
            @RequestBody @Valid final SubjectModel subjectModel) {
        Subject subject = subjectService.createSubject(subjectModel);

        return new ResponseEntity<>(
                subjectMapper.entityToModel(subject),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SubjectModel> updateSubject(
            @RequestBody SubjectModel subjectModel) {
        Subject subject = subjectService.updateExistingSubject(subjectModel);

        return ResponseEntity.ok(subjectMapper.entityToModel(subject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubjectModel> deleteSubject(
            @PathVariable(name = "id") UUID subjectId) {
        Subject subject = subjectService.deleteSubjectById(subjectId);

        return ResponseEntity.ok(subjectMapper.entityToModel(subject));
    }
}
