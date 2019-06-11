package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.Question;
import com.bsuir.cognispect.entity.Teacher;
import com.bsuir.cognispect.entity.TestTemplate;
import com.bsuir.cognispect.entity.TestTemplateQuestion;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.model.test.TestTemplateModel;
import com.bsuir.cognispect.model.test.TestTemplateQuestionModel;
import com.bsuir.cognispect.repository.QuestionRepository;
import com.bsuir.cognispect.repository.TeacherRepository;
import com.bsuir.cognispect.repository.TestTemplateRepository;
import com.bsuir.cognispect.service.TestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public TestTemplate createTestTemplate(TestTemplateModel testTemplateModel) {
        TestTemplate testTemplate = new TestTemplate();

        Teacher teacher = teacherRepository
                .findTeacherById(testTemplateModel.getCreator().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher", testTemplateModel.getCreator().getId()));

        testTemplate.setCreator(teacher);
        testTemplate.setTestTemplateQuestions(createTestTemplateQuestions(
                testTemplateModel.getTestTemplateQuestions(), testTemplate));

        testTemplateRepository.save(testTemplate);

        return testTemplate;
    }

    private List<TestTemplateQuestion> createTestTemplateQuestions(
            List<TestTemplateQuestionModel> testTemplateQuestionModelList, TestTemplate testTemplate) {
        List<TestTemplateQuestion> testTemplateQuestions = new ArrayList<>();

        for (TestTemplateQuestionModel testTemplateQuestionModel : testTemplateQuestionModelList) {
            Question question = questionRepository
                    .findQuestionById(testTemplateQuestionModel.getQuestionId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Question", testTemplateQuestionModel.getQuestionId()));

            TestTemplateQuestion testTemplateQuestion = new TestTemplateQuestion();

            testTemplateQuestion.setQuestion(question);
            testTemplateQuestion.setTestTemplate(testTemplate);
            testTemplateQuestion.setQuestionCost(testTemplateQuestionModel.getQuestionCost());

            testTemplateQuestions.add(testTemplateQuestion);
        }

        return testTemplateQuestions;
    }

    @Override
    public Page<TestTemplate> getTestTemplates(String name, int page, int pageSize) {
        return testTemplateRepository.findByName(name, PageRequest.of(page, pageSize));
    }

    @Override
    public TestTemplate updateTestTemplate(TestTemplateModel testTemplateModel) {
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

    @Override
    public TestTemplate getTestTemplateById(UUID testTemplateId) {
        return testTemplateRepository.findById(testTemplateId).orElseThrow(
                () -> new ResourceNotFoundException("TestTemplate", testTemplateId));
    }
}
