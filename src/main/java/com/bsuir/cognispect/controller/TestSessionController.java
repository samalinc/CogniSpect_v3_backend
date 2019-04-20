package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.service.TestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api/testSession")
public class TestSessionController {
    @Autowired
    private TestSessionService testSessionService;

    @GetMapping("/getStudentTests")
    public ResponseEntity<?> getTestSessionsForStudent(
            @RequestParam(name = "studentId") UUID studentId) {
        return ResponseEntity.ok(
                testSessionService.getEnabledTestSessionsForStudent(studentId)
        );
    }
}
