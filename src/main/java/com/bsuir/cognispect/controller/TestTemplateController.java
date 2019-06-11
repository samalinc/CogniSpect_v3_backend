package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.generator.TestTemplateGeneratorService;
import com.bsuir.cognispect.mapper.test.TestTemplateMapper;
import com.bsuir.cognispect.mapper.test.TestVariantMapper;
import com.bsuir.cognispect.model.GenerateTestVariantsModel;
import com.bsuir.cognispect.model.RestResponsePage;
import com.bsuir.cognispect.model.test.TestTemplateModel;
import com.bsuir.cognispect.model.test.TestVariantModel;
import com.bsuir.cognispect.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/testTemplate")
public class TestTemplateController {
    @Autowired
    private TestTemplateMapper testTemplateMapper;
    @Autowired
    private TestTemplateService testTemplateService;
    @Autowired
    private TestTemplateGeneratorService testTemplateGeneratorService;
    @Autowired
    private TestVariantMapper testVariantMapper;

    @PostMapping
    public ResponseEntity<TestTemplateModel> createTestTemplate(
            @Valid @RequestBody TestTemplateModel testTemplateModel) {

        return new ResponseEntity<>(
                testTemplateMapper.entityToModel(
                        testTemplateService.createTestTemplate(testTemplateModel)),
                HttpStatus.CREATED);
    }

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

        return ResponseEntity.ok(
                testTemplateMapper.entityToModel(
                        testTemplateService.getTestTemplateById(testTemplateId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TestTemplateModel> deleteTestTemplate(
            @PathVariable(name = "id") UUID testTemplateId) {
        TestTemplate testTemplate = testTemplateService
                .deleteTestTemplateById(testTemplateId);

        return ResponseEntity.ok(testTemplateMapper
                .entityToModel(testTemplate));
    }

    @PostMapping("/generate")
    public ResponseEntity<List<TestVariantModel>> generateTestVariants(
            @Valid @RequestBody GenerateTestVariantsModel generateTestVariantsModel) {
        TestTemplate testTemplate = testTemplateService
                .getTestTemplateById(generateTestVariantsModel.getTestTemplateId());

        return ResponseEntity.ok(testVariantMapper.entitiesToModels(testTemplateGeneratorService
                .generateTestVariants(testTemplate, generateTestVariantsModel.getNumOfVariants())));
    }
}
