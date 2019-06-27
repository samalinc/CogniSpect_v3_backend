package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.TestSession;
import com.bsuir.cognispect.model.create.CreateTestSessionModel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;


public interface TestSessionService {
    List<TestSession> getEnabledTestSessionsForStudent(UUID studentId);

    TestSession createTestSession(CreateTestSessionModel createTestSessionModel);

    Page<TestSession> getTestSessionsByFilter(String name, int page, int pageSize);
}
