package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.TestVariant;

import java.util.UUID;

public interface TestVariantService {
    TestVariant getTestVariantForStudent(UUID testSessionId, UUID studentId);
}
