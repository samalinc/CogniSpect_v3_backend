package com.bsuir.cognispect.generator;

import com.bsuir.cognispect.entity.*;

import java.util.ArrayList;
import java.util.List;

public class TestTemplateGeneratorService {

    public List<TestVariant> generateTestVariants(TestTemplate testTemplate, long numberOfVariants) {
        List<TestVariant> testVariants = new ArrayList<>();
        List<TestTemplateQuestion> testTemplateQuestions = testTemplate.getTestTemplateQuestions();
        for (int i = 0; i < numberOfVariants; i++) {
            testVariants.add(createTestVariant(testTemplateQuestions));
        }
        return testVariants;
    }

    private TestVariant createTestVariant(List<TestTemplateQuestion> testTemplateQuestions) {
        List<Question> questions = new ArrayList<>();
        QuestionVariantGeneratorService questionVariantGeneratorService = new QuestionVariantGeneratorService();
        TestVariant testVariant = new TestVariant();

        testTemplateQuestions.forEach(testTemplateQuestion -> questions.add(testTemplateQuestion.getQuestion()));

        List<QuestionVariant> questionVariants = new ArrayList<>();
        questions.forEach(question ->
                questionVariants.add(questionVariantGeneratorService.generateQuestionVariant(question)));

        testVariant.setQuestionVariants(questionVariants);
        return testVariant;
    }

}
