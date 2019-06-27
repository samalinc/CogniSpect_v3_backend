package com.bsuir.cognispect.generator;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.repository.AccountRepository;
import com.bsuir.cognispect.repository.TestVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class TestTemplateGeneratorService {
    @Autowired
    private QuestionVariantGeneratorService questionVariantGeneratorService;
    @Autowired
    private TestVariantRepository testVariantRepository;
    @Autowired
    private AccountRepository accountRepository;

    public List<TestVariant> generateTestVariants(TestTemplate testTemplate, List<UUID> userIds) {
        if (userIds.size() == 0) {
            throw new RuntimeException("Юзеров для генерации не может быть 0");
        }
        List<TestVariant> testVariants = new ArrayList<>();
        List<TestTemplateQuestion> testTemplateQuestions = testTemplate.getTestTemplateQuestions();
        for (UUID userId : userIds) {
            TestVariant testVariant = createTestVariant(testTemplateQuestions);
            Student student = accountRepository.findById(userId).get().getStudent();
            if (student == null) {
                throw new ResourceNotFoundException("Student", userId);
            }
            testVariant.setStudent(student);
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
