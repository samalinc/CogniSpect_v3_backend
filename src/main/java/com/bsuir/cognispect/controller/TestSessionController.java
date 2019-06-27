package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.Student;
import com.bsuir.cognispect.entity.enums.TestSessionStatusEnum;
import com.bsuir.cognispect.mapper.test.TestSessionMapper;
import com.bsuir.cognispect.model.RestResponsePage;
import com.bsuir.cognispect.model.create.CreateTestSessionModel;
import com.bsuir.cognispect.model.test.TestSessionModel;
import com.bsuir.cognispect.security.details.UserDetailsImpl;
import com.bsuir.cognispect.service.TestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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

    @PostMapping
    public ResponseEntity<TestSessionModel> createTestSession(@Valid @RequestBody CreateTestSessionModel createTestSessionModel) {
        return new ResponseEntity<>(
                testSessionMapper.entityToModel(testSessionService
                        .createTestSession(createTestSessionModel)),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponsePage<TestSessionModel>> getTestSessionsByParams(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "1")
            @Min(1) Integer pageSize) {

        return ResponseEntity.ok(new RestResponsePage<>(
                testSessionService.getTestSessionsByFilter(name, page, pageSize).map(
                        testSessionMapper::entityToModel)));
    }
}
