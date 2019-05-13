package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.model.TopicModel;
import com.bsuir.cognispect.entity.Topic;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {SubjectMapper.class})
public abstract class TopicMapper {
    public abstract TopicModel entityToModel(Topic topic);

    public abstract List<TopicModel> entitiesToModels(Collection<Topic> topics);
}
