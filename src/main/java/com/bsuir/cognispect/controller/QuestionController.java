package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.mapper.question.QuestionMapper;
import com.bsuir.cognispect.model.RestResponsePage;
import com.bsuir.cognispect.model.question.CreateQuestionModel;
import com.bsuir.cognispect.model.question.QuestionModel;
import com.bsuir.cognispect.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
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
            @Valid @RequestBody final CreateQuestionModel createQuestionModel) {

        return new ResponseEntity<>(questionMapper.entityToModel(
                questionService.createQuestion(createQuestionModel)),
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
    public ResponseEntity<RestResponsePage<QuestionModel>> getQuestionsByFilter(
            @RequestParam(name = "subject", required = false, defaultValue = "")
                    String subject,
            @RequestParam(name = "topic", required = false, defaultValue = "")
                    String topic,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(0) Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "1")
            @Min(1) Integer pageSize) {

        return ResponseEntity.ok(new RestResponsePage<>(
                questionService.getQuestionsByFilter(subject, topic, page, pageSize).map(
                        questionMapper::entityToModel)));
    }

    @GetMapping("/topic/{id}")
    public ResponseEntity<List<QuestionModel>> getQuestionsByTopicId(
            @PathVariable(name = "id") UUID topicId) {

        return ResponseEntity.ok(questionMapper.entitiesToModels(
                questionService.getQuestionsByTopicId(topicId)
        ));
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<List<QuestionModel>> getQuestionsBySubjectId(
            @PathVariable(name = "id") UUID subjectId) {

        return ResponseEntity.ok(questionMapper.entitiesToModels(
                questionService.getQuestionsBySubjectId(subjectId)
        ));
    }
}
