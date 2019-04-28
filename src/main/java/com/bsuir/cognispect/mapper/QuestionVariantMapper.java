package com.bsuir.cognispect.mapper;

import com.bsuir.cognispect.dto.QuestionVariantDto;
import com.bsuir.cognispect.entity.QuestionVariant;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;


@Mapper(componentModel = "spring",
        uses = {AnswerVariantMapper.class,
                MatchAnswerVariantMapper.class,
                SortAnswerVariantMapper.class})
public abstract class QuestionVariantMapper {
    public abstract QuestionVariantDto questionVariantToQuestionVariantDto(
            QuestionVariant questionVariant);

    public abstract List<QuestionVariantDto> questionVariantsToQuestionVariantsDto(
            Collection<QuestionVariant> questionVariants);
}
