package com.bsuir.cognispect.service.impl;

import com.bsuir.cognispect.entity.*;
import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import com.bsuir.cognispect.exception.ResourceNotFoundException;
import com.bsuir.cognispect.mapper.question.QuestionVariantMapper;
import com.bsuir.cognispect.model.test.TestVariantResultModel;
import com.bsuir.cognispect.repository.ChooseAnswerVariantRepository;
import com.bsuir.cognispect.repository.MatchAnswerVariantRepository;
import com.bsuir.cognispect.repository.SortAnswerVariantRepository;
import com.bsuir.cognispect.repository.TestVariantRepository;
import com.bsuir.cognispect.service.TestVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class TestVariantServiceImpl implements TestVariantService {
    @Autowired
    private TestVariantRepository testVariantRepository;
    @Autowired
    private ChooseAnswerVariantRepository chooseAnswerVariantRepository;
    @Autowired
    private MatchAnswerVariantRepository matchAnswerVariantRepository;
    @Autowired
    private SortAnswerVariantRepository sortAnswerVariantRepository;
    @Autowired
    private QuestionVariantMapper questionVariantMapper;

    @Override
    public TestVariant getTestVariantForStudent(UUID testSessionId, UUID studentId) {

        return testVariantRepository.findTestVariantByTestSessionIdAndStudentId(
                testSessionId, studentId).orElseThrow(() -> new ResourceNotFoundException(
                "Question variant in session: " + testSessionId +
                        " for student: " + studentId + " not found"));
    }

    @Override
    public TestVariant changeTestVariantStatus(
            UUID testVariantId, TestVariantStatusEnum testVariantStatus) {
        TestVariant testVariant = testVariantRepository.getOne(testVariantId);
        testVariant.setTestVariantStatus(testVariantStatus);

        return testVariantRepository.save(testVariant);
    }

    @Override
    public TestVariant getTestVariantById(UUID testVariantId) {
        return testVariantRepository.findById(testVariantId).orElseThrow(
                () -> new ResourceNotFoundException("TestVariant", testVariantId));
    }

    @Override
    public Page<TestVariant> getTestVariantsByFilter(UUID studentId, int page, int pageSize) {
        return testVariantRepository.findTestVariantsByStudentId(
                studentId, PageRequest.of(page, pageSize));
    }

    @Override
    public TestVariantResultModel countStudentTestVariantPoints(UUID testVariantId) {
        TestVariant testVariant = testVariantRepository.findById(testVariantId)
                .orElseThrow(() -> new ResourceNotFoundException("TestVariant", testVariantId));
        int maxPoints = 0, studentsPoints = 0;

        for (QuestionVariant questionVariant : testVariant.getQuestionVariants()) {
            maxPoints += questionVariant.getCost();
            if (checkStudentAnswer(questionVariant.getAnswers(), questionVariant.getType())) {
                studentsPoints += questionVariant.getCost();
            }
        }

        return new TestVariantResultModel(
                testVariantId, testVariant.getTestVariantStatus(),
                questionVariantMapper.entitiesToModels(testVariant.getQuestionVariants()),
                maxPoints, studentsPoints);
    }

    private boolean checkStudentAnswer(List<AnswerVariant> answerVariants, QuestionTypeEnum questionTypeEnum) {
        switch (questionTypeEnum) {
            case CHOOSE:
            case MULTICHOOSE:
            case SUBSTITUTION:
                return checkChooseAnswers(answerVariants);
            case MATCH:
                return checkMatchAnswers(answerVariants);
            case SORT:
                return checkSortAnswers(answerVariants);
        }
        return false;
    }

    private boolean checkChooseAnswers(List<AnswerVariant> answerVariants) {
        for (AnswerVariant answerVariant : answerVariants) {
            ChooseAnswerVariant chooseAnswerVariant = chooseAnswerVariantRepository.findById(answerVariant.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("ChooseAnswerVariant", answerVariant.getId()));

            if (chooseAnswerVariant.isCorrect() && chooseAnswerVariant.isStudentChose())
                return true;
        }
        return false;
    }

    private boolean checkMatchAnswers(List<AnswerVariant> answerVariants) {
        for (AnswerVariant answerVariant : answerVariants) {
            MatchAnswerVariant matchAnswerVariant = matchAnswerVariantRepository.findById(answerVariant.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("MatchAnswerVariant"));
            if (!matchAnswerVariant.getValue().equalsIgnoreCase(matchAnswerVariant.getStudentValue()))
                return false;
        }
        return true;
    }

    private boolean checkSortAnswers(List<AnswerVariant> answerVariants) {
        for (AnswerVariant answerVariant : answerVariants) {
            SortAnswerVariant sortAnswerVariant = sortAnswerVariantRepository.findById(answerVariant.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("SortAnswerVariant"));
            if (!(sortAnswerVariant.getRightPosition() == sortAnswerVariant.getStudentPosition()))
                return false;
        }
        return true;
    }
}
