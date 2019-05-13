package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TopicDto;
import com.bsuir.cognispect.entity.Topic;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {SubjectMapper.class})
public abstract class TopicMapper {
    public abstract TopicDto entityToModel(Topic topic);

    public abstract List<TopicDto> entitiesToModels(Collection<Topic> topics);
}
