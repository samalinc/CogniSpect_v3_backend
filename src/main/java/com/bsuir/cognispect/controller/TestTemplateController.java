package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.TestTemplateDto;
import com.bsuir.cognispect.mapper.TestTemplateMapper;
import com.bsuir.cognispect.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/testTemplate")
public class TestTemplateController {
    @Autowired
    private TestTemplateMapper testTemplateMapper;
    @Autowired
    private TestTemplateService testTemplateService;

    @PostMapping("/create")
    public ResponseEntity<?> createTestTemplate(
            @Valid @RequestBody TestTemplateDto testTemplateDto) {

        return new ResponseEntity<>(
                testTemplateMapper.testTemplateToTestTemplateDto(
                        testTemplateService.createTestTemplate(testTemplateDto)),
                HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTestTemplates() {

        return ResponseEntity.ok(
                testTemplateMapper.testTemplatesToTestTemplatesDto(
                        testTemplateService.getTestTemplates()
                )
        );
    }
}
