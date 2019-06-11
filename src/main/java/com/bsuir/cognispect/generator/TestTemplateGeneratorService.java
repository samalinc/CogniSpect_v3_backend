package com.bsuir.cognispect.generator;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.repository.TestVariantRepository;
import com.bsuir.cognispect.service.TestVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TestTemplateGeneratorService {
    @Autowired
    private QuestionVariantGeneratorService questionVariantGeneratorService;
    @Autowired
    private TestVariantRepository testVariantRepository;


    public List<TestVariant> generateTestVariants(TestTemplate testTemplate, long numberOfVariants) {
        List<TestVariant> testVariants = new ArrayList<>();
        List<TestTemplateQuestion> testTemplateQuestions = testTemplate.getTestTemplateQuestions();
        for (int i = 0; i < numberOfVariants; i++) {
            testVariants.add(createTestVariant(testTemplateQuestions));
        }
        return testVariantRepository.saveAll(testVariants);
    }

    private TestVariant createTestVariant(List<TestTemplateQuestion> testTemplateQuestions) {
        List<Question> questions = new ArrayList<>();
        TestVariant testVariant = new TestVariant();

        testTemplateQuestions.forEach(testTemplateQuestion -> questions.add(testTemplateQuestion.getQuestion()));

        List<QuestionVariant> questionVariants = new ArrayList<>();
        questions.forEach(question ->
                questionVariants.add(questionVariantGeneratorService.generateQuestionVariant(question)));

        testVariant.setQuestionVariants(questionVariants);
        return testVariant;
    }

}
