package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.model.question.QuestionModel;
import com.bsuir.cognispect.mapper.question.QuestionMapper;
import com.bsuir.cognispect.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping
    public ResponseEntity<QuestionModel> createQuestion(
            @Valid @RequestBody final QuestionModel questionModel) {

        return new ResponseEntity<>(questionMapper.entityToModel(
                questionService.createQuestion(questionModel)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionModel> getQuestionById(
            @PathVariable(name = "id") UUID questionId) {

        return ResponseEntity.ok(questionMapper.entityToModel(
                questionService.getQuestionById(questionId)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestionModel> deleteQuestionById(
            @PathVariable(name = "id") UUID questionId) {

        return ResponseEntity.ok(questionMapper.entityToModel(questionService
                .deleteQuestionById(questionId)));
    }

    @PutMapping
    public ResponseEntity<QuestionModel> updateQuestion(
            @Valid @RequestBody final QuestionModel questionModel) {

        return ResponseEntity.ok(questionMapper.entityToModel(
                questionService.updateQuestion(questionModel)));
    }

    @GetMapping
    public ResponseEntity<List<QuestionModel>> getQuestionsByFilter(
            @RequestParam(name = "subject", required = false, defaultValue = "")
                    String subject,
            @RequestParam(name = "topic", required = false, defaultValue = "")
                    String topic) {

        return ResponseEntity.ok(questionMapper.entitiesToModels(
                questionService.getQuestionsByFilter(subject, topic))
        );
    }

    @GetMapping("/topic")
    public ResponseEntity<List<QuestionModel>> getQuestionsByTopicId(
            @RequestParam(name = "topicId") UUID topicId) {

        return ResponseEntity.ok(questionMapper.entitiesToModels(
                questionService.getQuestionsByTopicId(topicId)
        ));
    }

    @GetMapping("/subject")
    public ResponseEntity<List<QuestionModel>> getQuestionsBySubjectId(
            @RequestParam(name = "subjectId") UUID subjectId) {

        return ResponseEntity.ok(questionMapper.entitiesToModels(
                questionService.getQuestionsBySubjectId(subjectId)
        ));
    }
}
