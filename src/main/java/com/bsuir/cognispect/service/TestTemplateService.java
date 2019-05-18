package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.test.TestTemplateModel;
import com.bsuir.cognispect.entity.TestTemplate;

import java.util.List;
import java.util.UUID;

public interface TestTemplateService {
    TestTemplate createTestTemplate(TestTemplateModel testTemplateModel);

    List<TestTemplate> getTestTemplates();

    TestTemplate updateTestTemplate(TestTemplateModel testTemplateModel);
    
    TestTemplate deleteTestTemplateById(UUID testTemplateId);
}
