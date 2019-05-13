package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.TestSessionDto;
import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.mapper.TestSessionMapper;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.service.TestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/testSession")
public class TestSessionController {
    @Autowired
    private TestSessionService testSessionService;
    @Autowired
    private TestSessionMapper testSessionMapper;

    @GetMapping("/getStudentTests")
    public ResponseEntity<List<TestSessionDto>> getTestSessionsForStudent() {
        Student student = ((UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails())
                .getAccount().getStudent();

        if (student == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(testSessionMapper.entitiesToModels(
                testSessionService
                        .getEnabledTestSessionsForStudent(student.getId())));
    }
}
