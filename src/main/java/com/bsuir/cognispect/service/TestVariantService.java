package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.TestVariant;
import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import com.bsuir.cognispect.model.test.TestVariantResultModel;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TestVariantService {
    TestVariant getTestVariantForStudent(UUID testSessionId, UUID studentId);

    TestVariant changeTestVariantStatus(UUID testVariantId, TestVariantStatusEnum testVariantStatus);

    TestVariant getTestVariantById(UUID testVariantId);

    Page<TestVariant> getTestVariantsByFilter(UUID studentId, int page, int pageSize);

    TestVariantResultModel countStudentTestVariantPoints(UUID testVariantId);
}
