package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.mapper.QuestionMapper;
import com.bsuir.cognispect.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping
    public ResponseEntity<?> createQuestion(
            @Valid @RequestBody final QuestionDto questionDto) {

        return new ResponseEntity<>(questionMapper.questionToQuestionDto(
                questionService.createQuestion(questionDto)),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getQuestionsByFilter(
            @RequestParam(name = "subject", required = false, defaultValue = "")
                    String subject,
            @RequestParam(name = "topic", required = false, defaultValue = "")
                    String topic) {

        return ResponseEntity.ok(questionMapper.questionsToQuestionsDto(
                questionService.getQuestionsByFilter(subject, topic))
        );
    }

    @GetMapping("/topic")
    public ResponseEntity<?> getQuestionsByTopicId(
            @RequestParam(name = "topicId") UUID topicId) {

        return ResponseEntity.ok(questionMapper.questionsToQuestionsDto(
                questionService.getQuestionsByTopicId(topicId)
        ));
    }

    @GetMapping("/subject")
    public ResponseEntity<?> getQuestionsBySubjectId(
            @RequestParam(name = "subjectId") UUID subjectId) {

        return ResponseEntity.ok(questionMapper.questionsToQuestionsDto(
                questionService.getQuestionsBySubjectId(subjectId)
        ));
    }
}
