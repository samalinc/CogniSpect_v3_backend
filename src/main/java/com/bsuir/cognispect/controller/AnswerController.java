package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.model.answer.test.UserAnswerModel;
import com.bsuir.cognispect.service.AnswerVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerVariantService answerVariantService;

    @PutMapping("/submit")
    public void submitUserAnswer(@Valid @RequestBody UserAnswerModel userAnswerModel) {
        answerVariantService.submitAnswer(userAnswerModel);
    }
}
