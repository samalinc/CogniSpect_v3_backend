package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.QuestionDto;
import com.bsuir.cognispect.mapper.QuestionMapper;
import com.bsuir.cognispect.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(
            @Valid @RequestBody final QuestionDto questionDto) {

        return new ResponseEntity<>(
                questionService.createQuestion(
                        questionMapper.questionDtoToQuestion(questionDto)
                ),
                HttpStatus.CREATED
        );
    }
}
