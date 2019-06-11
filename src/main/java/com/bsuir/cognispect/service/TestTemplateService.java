package com.bsuir.cognispect.service;

import com.bsuir.cognispect.model.test.TestTemplateModel;
import com.bsuir.cognispect.entity.TestTemplate;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TestTemplateService {
    TestTemplate createTestTemplate(TestTemplateModel testTemplateModel);

    Page<TestTemplate> getTestTemplates(String name, int page, int pageSize);

    TestTemplate updateTestTemplate(TestTemplateModel testTemplateModel);
    
    TestTemplate deleteTestTemplateById(UUID testTemplateId);

    TestTemplate getTestTemplateById(UUID testTemplateId);
}
