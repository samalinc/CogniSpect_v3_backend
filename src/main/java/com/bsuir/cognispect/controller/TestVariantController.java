package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import com.bsuir.cognispect.mapper.test.TestVariantMapper;
import com.bsuir.cognispect.model.answer.ChooseAnswerVariantForTestModel;
import com.bsuir.cognispect.model.answer.test.UserAnswerModel;
import com.bsuir.cognispect.model.question.QuestionVariantForTestModel;
import com.bsuir.cognispect.model.test.TestVariantForTestModel;
import com.bsuir.cognispect.service.AnswerVariantService;
import com.bsuir.cognispect.service.TestVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@RestController
@RequestMapping("/api/testVariant")
public class TestVariantController {
    @Autowired
    private TestVariantService testVariantService;
    @Autowired
    private TestVariantMapper testVariantMapper;
    @Autowired
    private AnswerVariantService answerVariantService;

    @GetMapping
    public ResponseEntity<TestVariantForTestModel> getTestVariantForStudent(
            @RequestParam(name = "testSessionId") UUID testSessionId) {
        /*Student student = ((UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getDetails())
                .getAccount().getStudent();

        if (student == null) {
            throw new IllegalArgumentException("Student not found");
        }

        return ResponseEntity.ok(testVariantMapper.entityToModelForTest(
                testVariantService.getTestVariantForStudent(testSessionId, student.getId())));*/
        Random random = new Random();
        TestVariantForTestModel testVariantForTestModel = new TestVariantForTestModel();
        testVariantForTestModel.setId(UUID.randomUUID());
        testVariantForTestModel.setTestVariantStatus(TestVariantStatusEnum.STARTED);
        List<QuestionVariantForTestModel> b = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            QuestionVariantForTestModel questionVariantForTestModel = new QuestionVariantForTestModel();
            questionVariantForTestModel.setId(UUID.randomUUID());
            questionVariantForTestModel.setDescription("TEST TEST TEST: " + i);
            questionVariantForTestModel.setType(QuestionTypeEnum.MULTICHOOSE);
            List<ChooseAnswerVariantForTestModel> a = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                ChooseAnswerVariantForTestModel chooseAnswerVariantForTestModel = new ChooseAnswerVariantForTestModel();
                chooseAnswerVariantForTestModel.setId(UUID.randomUUID());
                chooseAnswerVariantForTestModel.setText("ANSWER TEST TEST: " + j);
                chooseAnswerVariantForTestModel.setPosition(random.nextInt(6));
                a.add(chooseAnswerVariantForTestModel);
            }
            questionVariantForTestModel.setChooseAnswers(a);
            b.add(questionVariantForTestModel);
        }
        testVariantForTestModel.setQuestionVariants(b);

        return ResponseEntity.ok(testVariantForTestModel);
    }

    @PutMapping("/submitAnswer")
    public ResponseEntity<?> submitTestVariantAnswer(
            @RequestBody UserAnswerModel userAnswerModel) {

        answerVariantService.submitAnswer(userAnswerModel);
        return ResponseEntity.ok().build();
    }
}
