package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TopicDto;
import com.bsuir.cognispect.entity.Topic;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring",
        uses = {SubjectMapper.class})
public abstract class TopicMapper {
    public abstract TopicDto topicToTopicDto(Topic topic);
}
