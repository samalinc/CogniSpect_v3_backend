package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.mapper.TestVariantMapper;
import com.bsuir.cognispect.service.TestVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/testVariant")
public class TestVariantController {
    @Autowired
    private TestVariantService testVariantService;
    @Autowired
    private TestVariantMapper testVariantMapper;

    @GetMapping
    public ResponseEntity<?> getTestVariantForStudent(
            @RequestParam(name = "testSessionId") UUID testSessionId,
            @RequestParam(name = "studentId") UUID studentId) {

        return ResponseEntity.ok(testVariantMapper.testVariantToTestVariantDto(
                testVariantService.getTestVariantForStudent(testSessionId, studentId)));
    }
}
