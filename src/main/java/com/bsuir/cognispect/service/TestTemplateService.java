package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.TestTemplateDto;
import com.bsuir.cognispect.entity.TestTemplate;

import java.util.List;
import java.util.UUID;

public interface TestTemplateService {
    TestTemplate createTestTemplate(TestTemplateDto testTemplateDto);

    List<TestTemplate> getTestTemplates();

    TestTemplate updateTestTemplate(TestTemplateDto testTemplateDto);
    
    TestTemplate deleteTestTemplateById(UUID testTemplateId);
}
