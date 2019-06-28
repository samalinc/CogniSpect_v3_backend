package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.model.create.CreateTestTemplateModel;
import com.bsuir.cognispect.model.test.TestTemplateModel;
import com.bsuir.cognispect.model.test.TestTemplateQuestionModel;
import com.bsuir.cognispect.repository.*;
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
    private TestTemplateTopicRepository testTemplateTopicRepository;
    @Autowired
    private TestTemplateQuestionRepository testTemplateQuestionRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public TestTemplate createTestTemplate(CreateTestTemplateModel createTestTemplateModel) {
        if (createTestTemplateModel.getTestTemplateQuestions().size() == 0) {
            throw new RuntimeException("Test template should have more than 0 questions");
        }

        TestTemplate testTemplate = new TestTemplate();

        Teacher teacher = teacherRepository
                .findTeacherById(createTestTemplateModel.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Teacher", createTestTemplateModel.getCreatorId()));

        testTemplate.setName(createTestTemplateModel.getName());
        testTemplate.setCreator(teacher);
        testTemplateRepository.save(testTemplate);
        testTemplate.setTestTemplateQuestions(createTestTemplateQuestions(
                createTestTemplateModel.getTestTemplateQuestions(), testTemplate));
        testTemplate.setTestTemplateTopics(createTestTemplateTopics(
                createTestTemplateModel.getTopicIds(), testTemplate));

        return testTemplate;
    }

    private List<TestTemplateTopic> createTestTemplateTopics(List<UUID> topicIds, TestTemplate testTemplate) {
        List<TestTemplateTopic> testTemplateTopics = new ArrayList<>();

        for (UUID topicId : topicIds) {
            Topic topic = topicRepository.findTopicById(topicId)
                    .orElseThrow(() -> new ResourceNotFoundException("Topic", topicId));
            TestTemplateTopic testTemplateTopic = new TestTemplateTopic();

            testTemplateTopic.setId(new TestTemplateTopicId(testTemplate.getId(), topic.getId()));
            testTemplateTopic.setTopic(topic);
            testTemplateTopic.setTestTemplate(testTemplate);

            testTemplateTopics.add(testTemplateTopic);
        }

        return testTemplateTopicRepository.saveAll(testTemplateTopics);
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

            testTemplateQuestion.setId(new TestTemplateQuestionId(testTemplate.getId(),
                    question.getId()));

            testTemplateQuestion.setQuestion(question);
            testTemplateQuestion.setTestTemplate(testTemplate);
            testTemplateQuestion.setQuestionCost(testTemplateQuestionModel.getQuestionCost());

            testTemplateQuestions.add(testTemplateQuestion);
        }

        return testTemplateQuestionRepository.saveAll(testTemplateQuestions);
    }

    @Override
    public Page<TestTemplate> getTestTemplates(String name, int page, int pageSize) {
        return testTemplateRepository.findByNameContaining(name, PageRequest.of(page, pageSize));
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
