package com.bsuir.cognispect.controller;

import com.bsuir.cognispect.model.TopicModel;
import com.bsuir.cognispect.entity.Topic;
import com.bsuir.cognispect.mapper.TopicMapper;
import com.bsuir.cognispect.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicMapper topicMapper;

    @GetMapping
    public ResponseEntity<List<TopicModel>> getTopicsByFiler(
            @RequestParam(name = "topicName", required = false, defaultValue = "")
                    String topicName,
            @RequestParam(name = "subjectName", required = false, defaultValue = "")
                    String subjectName) {

        return ResponseEntity.ok(
                topicMapper.entitiesToModels(topicService
                        .getTopicsByFilter(topicName, subjectName)));
    }

    @PostMapping
    public ResponseEntity<TopicModel> createTopic(
            @RequestBody @Valid final TopicModel topicModel) {
        Topic topic = topicService.createTopic(topicModel);

        return new ResponseEntity<>(topicMapper.entityToModel(topic),
                HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TopicModel> updateTopic(
            @RequestBody TopicModel topicModel) {
        Topic topic = topicService.updateExistingTopic(topicModel);

        return ResponseEntity.ok(topicMapper.entityToModel(topic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicModel> deleteTopic(
            @PathVariable(name = "id") UUID topicId) {
        Topic topic = topicService.deleteTopicById(topicId);

        return ResponseEntity.ok(topicMapper.entityToModel(topic));
    }
}
