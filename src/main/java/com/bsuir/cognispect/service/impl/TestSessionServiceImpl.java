package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Teacher;
import com.bsuir.cognispect.entity.TestSession;
import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.mapper.test.TestSessionMapper;
import com.bsuir.cognispect.model.create.CreateTestSessionModel;
import com.bsuir.cognispect.repository.TeacherRepository;
import com.bsuir.cognispect.repository.TestSessionRepository;
import com.bsuir.cognispect.repository.TestVariantRepository;
import com.bsuir.cognispect.service.TestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class TestSessionServiceImpl implements TestSessionService {
    @Autowired
    private TestVariantRepository testVariantRepository;
    @Autowired
    private TestSessionRepository testSessionRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TestSession> getEnabledTestSessionsForStudent(UUID studentId) {
        List<TestSession> testSessionList = new ArrayList<>();

        for (TestVariant testVariant : testVariantRepository
                .findAllTestVariantsByStudentId(studentId)) {
            testSessionList.add(testVariant.getTestSession());
        }

        return testSessionList;
    }

    @Override
    public TestSession createTestSession(CreateTestSessionModel createTestSessionModel) {
        Teacher teacher = teacherRepository.findTeacherById(createTestSessionModel.getCreatorId()).orElseThrow(
                () -> new ResourceNotFoundException("Teacher", createTestSessionModel.getCreatorId()));
        TestSession testSession = new TestSession();

        testSession.setName(createTestSessionModel.getName());
        testSession.setRouters(createTestSessionModel.getRouters());
        testSession.setTestSessionStatus(createTestSessionModel.getStatus());
        testSession.setTeacher(teacher);

        return testSessionRepository.save(testSession);
    }

    @Override
    public Page<TestSession> getTestSessionsByFilter(String name, int page, int pageSize) {
        return testSessionRepository.findByNameContaining(name, PageRequest.of(page, pageSize));
    }
}
