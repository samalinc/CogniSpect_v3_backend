package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.TestSession;

import java.util.List;
import java.util.UUID;

public interface TestSessionService {
    List<TestSession> getEnabledTestSessionsForStudent(UUID studentId);
}
