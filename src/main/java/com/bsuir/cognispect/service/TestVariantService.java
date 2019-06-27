package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;

import java.util.UUID;

public interface TestVariantService {
    TestVariant getTestVariantForStudent(UUID testSessionId, UUID studentId);

    TestVariant changeTestVariantStatus(UUID testVariantId, TestVariantStatusEnum testVariantStatus);

    TestVariant getTestVariantById(UUID testVariantId);
}
