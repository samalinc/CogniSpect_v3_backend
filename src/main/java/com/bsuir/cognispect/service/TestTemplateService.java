package com.bsuir.cognispect.service;

import com.bsuir.cognispect.dto.TestTemplateDto;
import com.bsuir.cognispect.entity.TestTemplate;

import java.util.List;

public interface TestTemplateService {
    TestTemplate createTestTemplate(TestTemplateDto testTemplateDto);

    List<TestTemplate> getTestTemplates();
}
