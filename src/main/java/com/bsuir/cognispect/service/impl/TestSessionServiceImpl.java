package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.TestSession;
import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.repository.TestVariantRepository;
import com.bsuir.cognispect.service.TestSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class TestSessionServiceImpl implements TestSessionService {
    @Autowired
    private TestVariantRepository testVariantRepository;

    @Override
    public List<TestSession> getEnabledTestSessionsForStudent(UUID studentId) {
        List<TestSession> testSessionList = new ArrayList<>();

        for (TestVariant testVariant : testVariantRepository
                .findAllTestVariantsByStudentId(studentId)) {
            testSessionList.add(testVariant.getTestSession());

        }

        return testSessionList;
    }
}
