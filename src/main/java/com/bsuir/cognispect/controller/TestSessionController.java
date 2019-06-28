package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.entity.TestSession;
import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.generator.TestTemplateGeneratorService;
import com.bsuir.cognispect.mapper.test.TestSessionMapper;
import com.bsuir.cognispect.model.RestResponsePage;
import com.bsuir.cognispect.model.create.CreateTestSessionModel;
import com.bsuir.cognispect.model.test.TestSessionSimpleModel;
import com.bsuir.cognispect.model.test.TestSessionModel;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.service.TestSessionService;
import com.bsuir.cognispect.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/testSession")
public class TestSessionController {
    @Autowired
    private TestSessionService testSessionService;
    @Autowired
    private TestSessionMapper testSessionMapper;
    @Autowired
    private TestTemplateService testTemplateService;
    @Autowired
    private TestTemplateGeneratorService testTemplateGeneratorService;

    @GetMapping("/student")
    public ResponseEntity<List<TestSessionSimpleModel>> getTestSessionsForStudent() {
        Student student = ((UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails())
                .getAccount().getStudent();

        if (student == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(testSessionMapper.entitiesToModelsForStudent(
                testSessionService
                        .getEnabledTestSessionsForStudent(student.getId())));
    }

    @GetMapping
    public ResponseEntity<RestResponsePage<TestSessionModel>> getTestSessionsByParams(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "1")
            @Min(1) Integer pageSize) {

        return ResponseEntity.ok(new RestResponsePage<>(
                testSessionService.getTestSessionsByFilter(name, page, pageSize).map(
                        testSessionMapper::entityToModel)));
    }

    @PostMapping
    public ResponseEntity<TestSessionModel> createTestSession(
            @Valid @RequestBody CreateTestSessionModel createTestSessionModel) {
        TestSession testSession = testSessionService.createTestSession(createTestSessionModel);
        TestTemplate testTemplate = testTemplateService
                .getTestTemplateById(createTestSessionModel.getTestTemplateId());

        testSession.setTestVariants(testTemplateGeneratorService
                .generateTestVariants(testTemplate, testSession, createTestSessionModel.getStudentIds()));

        return new ResponseEntity<>(
                testSessionMapper.entityToModel(testSession),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TestSessionModel> updateTestSession(
            @Valid @RequestBody TestSessionSimpleModel testSessionSimpleModel) {

        return ResponseEntity.ok(testSessionMapper.entityToModel(
                testSessionService.updateTestSession(testSessionSimpleModel)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TestSessionModel> deleteTestSession(@PathVariable(name = "id") UUID testSessionId) {
        return new ResponseEntity<>(testSessionMapper.entityToModel(testSessionService
                .deleteTestSessionById(testSessionId)), HttpStatus.NO_CONTENT);
    }
}
