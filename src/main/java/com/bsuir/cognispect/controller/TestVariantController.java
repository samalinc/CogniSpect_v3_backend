package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.entity.TestSession;
import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import com.bsuir.cognispect.generator.TestTemplateGeneratorService;
import com.bsuir.cognispect.mapper.test.TestVariantMapper;
import com.bsuir.cognispect.model.GenerateTestVariantsModel;
import com.bsuir.cognispect.model.RestResponsePage;
import com.bsuir.cognispect.model.test.TestVariantForTestModel;
import com.bsuir.cognispect.model.test.TestVariantModel;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.service.TestTemplateService;
import com.bsuir.cognispect.service.TestVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/testVariant")
public class TestVariantController {
    @Autowired
    private TestVariantService testVariantService;
    @Autowired
    private TestVariantMapper testVariantMapper;
    @Autowired
    private TestTemplateService testTemplateService;
    @Autowired
    private TestTemplateGeneratorService testTemplateGeneratorService;

    @GetMapping("/student")
    public ResponseEntity<TestVariantForTestModel> getTestVariantForStudent(
            @RequestParam(name = "testSessionId") UUID testSessionId) {
        Student student = ((UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails())
                .getAccount().getStudent();

        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        return ResponseEntity.ok(testVariantMapper.entityToModelForTest(
                testVariantService.getTestVariantForStudent(testSessionId, student.getId())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestVariantModel> getTestVariantById(@PathVariable(name = "id") UUID testVariantId) {
        return ResponseEntity.ok(testVariantMapper
                .entityToModel(testVariantService.getTestVariantById(testVariantId)));
    }

    @GetMapping
    public ResponseEntity<RestResponsePage<TestVariantModel>> getTestVariantsByFilter
            (@RequestParam(name = "studentId", required = false) UUID studentId,
             @RequestParam(name = "page", required = false, defaultValue = "0")
             @Min(0) Integer page,
             @RequestParam(name = "pageSize", required = false, defaultValue = "1")
             @Min(1) Integer pageSize) {
        return ResponseEntity.ok(new RestResponsePage<>(
                testVariantService.getTestVariantsByFilter(studentId, page, pageSize).map(
                        testVariantMapper::entityToModel)));
    }

    @PostMapping("/generate")
    public ResponseEntity<List<TestVariantModel>> generateTestVariants(
            @Valid @RequestBody GenerateTestVariantsModel generateTestVariantsModel) {
        TestTemplate testTemplate = testTemplateService
                .getTestTemplateById(generateTestVariantsModel.getTestTemplateId());

        return ResponseEntity.ok(testVariantMapper.entitiesToModels(testTemplateGeneratorService
                .generateTestVariants(testTemplate, new TestSession(), generateTestVariantsModel.getUserIds())));
    }

    @PutMapping("/finish/{id}")
    public void finishTestVariant(@PathVariable(name = "id") UUID testVariantId) {
        testVariantService.changeTestVariantStatus(testVariantId, TestVariantStatusEnum.FINISHED);
    }
}
