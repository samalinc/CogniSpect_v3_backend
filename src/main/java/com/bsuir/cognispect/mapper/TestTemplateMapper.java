package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.TestTemplateDto;
import com.bsuir.cognispect.entity.TestTemplate;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {TestTemplateQuestionMapper.class,
                UserMapper.class})
public abstract class TestTemplateMapper {
    public abstract TestTemplateDto entityToModel(TestTemplate testTemplate);

    public abstract List<TestTemplateDto> entitiesToModels(
            Collection<TestTemplate> testTemplates);
}
