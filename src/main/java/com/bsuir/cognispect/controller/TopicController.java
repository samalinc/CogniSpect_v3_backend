package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.dto.TopicDto;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.mapper.TopicMapper;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicMapper topicMapper;

    @GetMapping("/filter")
    public ResponseEntity<?> getTopicsByFiler(
            @RequestParam(name = "topicName", required = false, defaultValue = "")
                    String topicName,
            @RequestParam(name = "subjectName", required = false, defaultValue = "")
                    String subjectName) {

        return ResponseEntity.ok(
                topicMapper.topicsToTopicsDto(topicService
                        .getTopicsByFilter(topicName, subjectName)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTopic(
            @RequestBody @Valid final TopicDto topicDto) {
        try {
            Topic topic = topicService.createTopic(topicDto);

            return new ResponseEntity<>(
                    topicMapper.topicToTopicDto(
                            topic),
                    HttpStatus.CREATED);
        } catch (UniqueException | IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSubject(
            @RequestBody TopicDto topicDto) {
        try {
            Topic topic = topicService.updateExistingTopic(topicDto);

            return new ResponseEntity<>(
                    topicMapper.topicToTopicDto(
                            topic),
                    HttpStatus.OK);
        } catch (UniqueException | IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
