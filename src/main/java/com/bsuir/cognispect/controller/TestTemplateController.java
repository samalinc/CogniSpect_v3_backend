package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.model.TestTemplateModel;
import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.mapper.TestTemplateMapper;
import com.bsuir.cognispect.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/testTemplate")
public class TestTemplateController {
    @Autowired
    private TestTemplateMapper testTemplateMapper;
    @Autowired
    private TestTemplateService testTemplateService;

    @PostMapping
    public ResponseEntity<TestTemplateModel> createTestTemplate(
            @Valid @RequestBody TestTemplateModel testTemplateModel) {

        return new ResponseEntity<>(
                testTemplateMapper.entityToModel(
                        testTemplateService.createTestTemplate(testTemplateModel)),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestTemplateModel>> getAllTestTemplates() {

        return ResponseEntity.ok(
                testTemplateMapper.entitiesToModels(
                        testTemplateService.getTestTemplates()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TestTemplateModel> deleteTestTemplate(
            @PathVariable(name = "id") UUID testTemplateId) {
        TestTemplate testTemplate = testTemplateService
                .deleteTestTemplateById(testTemplateId);

        return ResponseEntity.ok(testTemplateMapper
                .entityToModel(testTemplate));
    }
}
