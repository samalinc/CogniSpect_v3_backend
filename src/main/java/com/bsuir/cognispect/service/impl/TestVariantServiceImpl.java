package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.repository.TestVariantRepository;
import com.bsuir.cognispect.service.TestVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class TestVariantServiceImpl implements TestVariantService {
    @Autowired
    private TestVariantRepository testVariantRepository;

    @Override
    public TestVariant getTestVariantForStudent(UUID testSessionId, UUID studentId) {

        return testVariantRepository.findTestVariantByTestSessionIdAndStudentId(
                testSessionId, studentId).orElseThrow(() -> new ResourceNotFoundException(
                "Question variant in session: " + testSessionId +
                        " for student: " + studentId + " not found"));
    }

    @Override
    public TestVariant changeTestVariantStatus(
            UUID testVariantId, TestVariantStatusEnum testVariantStatus) {
        TestVariant testVariant = testVariantRepository.getOne(testVariantId);
        testVariant.setTestVariantStatus(testVariantStatus);

        return testVariantRepository.save(testVariant);
    }

    @Override
    public TestVariant getTestVariantById(UUID testVariantId) {
        return testVariantRepository.findById(testVariantId).orElseThrow(
                () -> new ResourceNotFoundException("TestVariant", testVariantId));
    }
}
