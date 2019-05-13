package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.TestVariantDto;
import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.mapper.TestVariantMapper;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.service.TestVariantService;
import com.bsuir.cognispect.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @JsonView(View.DefaultView.class)
    @GetMapping
    public ResponseEntity<TestVariantDto> getTestVariantForStudent(
            @RequestParam(name = "testSessionId") UUID testSessionId) {
        Student student = ((UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails())
                .getAccount().getStudent();

        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        return ResponseEntity.ok(testVariantMapper.entityToModel(
                testVariantService.getTestVariantForStudent(testSessionId, student.getId())));
    }
}
