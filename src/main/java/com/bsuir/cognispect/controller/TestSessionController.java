package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.service.TestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/testSession")
public class TestSessionController {
    @Autowired
    private TestSessionService testSessionService;

    @GetMapping("/getStudentTests")
    public ResponseEntity<?> getTestSessionsForStudent() {
        Student student = ((UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails())
                .getAccount().getStudent();

        if (student == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(testSessionService
                .getEnabledTestSessionsForStudent(student.getId()));
    }
}
