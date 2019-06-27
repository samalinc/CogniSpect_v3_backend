package com.bsuir.cognispect.service;

import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.model.create.CreateTestTemplateModel;
import com.bsuir.cognispect.model.test.TestTemplateModel;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface TestTemplateService {
    TestTemplate createTestTemplate(CreateTestTemplateModel createTestTemplateModel);

    Page<TestTemplate> getTestTemplates(String name, int page, int pageSize);

    TestTemplate updateTestTemplate(TestTemplateModel testTemplateModel);

    TestTemplate deleteTestTemplateById(UUID testTemplateId);

    TestTemplate getTestTemplateById(UUID testTemplateId);
}
