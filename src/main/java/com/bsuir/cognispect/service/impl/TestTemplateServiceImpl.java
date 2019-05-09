package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.dto.TestTemplateDto;
import com.bsuir.cognispect.dto.TestTemplateQuestionDto;
import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.Teacher;
import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.entity.TestTemplateQuestion;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.repository.QuestionRepository;
import com.bsuir.cognispect.repository.TeacherRepository;
import com.bsuir.cognispect.repository.TestTemplateRepository;
import com.bsuir.cognispect.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class TestTemplateServiceImpl implements TestTemplateService {
    @Autowired
    private TestTemplateRepository testTemplateRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public TestTemplate createTestTemplate(TestTemplateDto testTemplateDto) {
        TestTemplate testTemplate = new TestTemplate();

        Teacher teacher = teacherRepository
                .findTeacherById(testTemplateDto.getCreator().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher", testTemplateDto.getCreator().getId()));

        testTemplate.setCreator(teacher);
        testTemplate.setTestTemplateQuestions(createTestTemplateQuestions(
                testTemplateDto.getTestTemplateQuestions(), testTemplate));

        testTemplateRepository.save(testTemplate);

        return testTemplate;
    }

    private List<TestTemplateQuestion> createTestTemplateQuestions(
            List<TestTemplateQuestionDto> testTemplateQuestionDtoList, TestTemplate testTemplate) {
        List<TestTemplateQuestion> testTemplateQuestions = new ArrayList<>();

        for (TestTemplateQuestionDto testTemplateQuestionDto : testTemplateQuestionDtoList) {
            Question question = questionRepository
                    .findQuestionById(testTemplateQuestionDto.getQuestion().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Question", testTemplateQuestionDto.getQuestion().getId()));

            TestTemplateQuestion testTemplateQuestion = new TestTemplateQuestion();

            testTemplateQuestion.setQuestion(question);
            testTemplateQuestion.setTestTemplate(testTemplate);
            testTemplateQuestion.setQuestionCost(testTemplateQuestionDto.getQuestionCost());

            testTemplateQuestions.add(testTemplateQuestion);
        }

        return testTemplateQuestions;
    }

    @Override
    public List<TestTemplate> getTestTemplates() {
        return testTemplateRepository.findAll();
    }

    @Override
    public TestTemplate updateTestTemplate(TestTemplateDto testTemplateDto) {
        return null;
    }

    @Override
    public TestTemplate deleteTestTemplateById(UUID testTemplateId) {
        TestTemplate testTemplate = testTemplateRepository.findById(testTemplateId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Test template", testTemplateId));
        testTemplateRepository.delete(testTemplate);

        return testTemplate;
    }
}
