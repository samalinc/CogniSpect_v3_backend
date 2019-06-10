package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.entity.enums.TestSessionStatusEnum;
import com.bsuir.cognispect.mapper.test.TestSessionMapper;
import com.bsuir.cognispect.model.test.TestSessionModel;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.service.TestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/testSession")
public class TestSessionController {
    @Autowired
    private TestSessionService testSessionService;
    @Autowired
    private TestSessionMapper testSessionMapper;

    @GetMapping("/getStudentTests")
    public ResponseEntity<List<TestSessionModel>> getTestSessionsForStudent() {
        Student student = ((UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails())
                .getAccount().getStudent();

        if (student == null) {
            return ResponseEntity.noContent().build();
        }
        TestSessionModel a = new TestSessionModel();
        a.setId(UUID.randomUUID());
        a.setName("first");
        a.setRouters(new String[]{"A1", "A2", "A3", "A4"});
        a.setTestSessionStatus(TestSessionStatusEnum.STARTED);

        TestSessionModel b = new TestSessionModel();

        b.setId(UUID.randomUUID());
        b.setName("second");
        b.setRouters(new String[]{"B1", "B2", "B3", "B4"});
        b.setTestSessionStatus(TestSessionStatusEnum.STARTED);
        /*return ResponseEntity.ok(testSessionMapper.entitiesToModels(
                testSessionService
                        .getEnabledTestSessionsForStudent(student.getId())));*/
        return ResponseEntity.ok(Arrays.asList(a, b));
    }
}
