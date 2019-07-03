package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.mapper.test.TestTemplateMapper;
import com.bsuir.cognispect.model.RestResponsePage;
import com.bsuir.cognispect.model.create.CreateTestTemplateModel;
import com.bsuir.cognispect.model.test.TestTemplateModel;
import com.bsuir.cognispect.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.UUID;


@RestController
@RequestMapping("/api/testTemplate")
public class TestTemplateController {
    @Autowired
    private TestTemplateMapper testTemplateMapper;
    @Autowired
    private TestTemplateService testTemplateService;

    @GetMapping
    public ResponseEntity<RestResponsePage<TestTemplateModel>> getTestTemplates(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "1")
            @Min(1) Integer pageSize) {

        return ResponseEntity.ok(new RestResponsePage<>(testTemplateService
                .getTestTemplates(name, page, pageSize)
                .map(testTemplateMapper::entityToModel)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestTemplateModel> getTestTemplateById(
            @PathVariable(name = "id") UUID testTemplateId) {

        return ResponseEntity.ok(testTemplateMapper.entityToModel(
                testTemplateService.getTestTemplateById(testTemplateId)));
    }

    @PostMapping
    public ResponseEntity<TestTemplateModel> createTestTemplate(
            @Valid @RequestBody CreateTestTemplateModel createTestTemplateModel) {

        return new ResponseEntity<>(testTemplateMapper.entityToModel(
                testTemplateService.createTestTemplate(createTestTemplateModel)),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TestTemplateModel> deleteTestTemplate(
            @PathVariable(name = "id") UUID testTemplateId) {
        TestTemplate testTemplate = testTemplateService
                .deleteTestTemplateById(testTemplateId);

        return ResponseEntity.ok(testTemplateMapper.entityToModel(testTemplate));
    }
}
