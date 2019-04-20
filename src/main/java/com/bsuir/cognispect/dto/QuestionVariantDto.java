package com.bsuir.cognispect.dto;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionVariantDto {
    private UUID id;
    private String description;
    private QuestionTypeEnum type;
    private List<AnswerVariantDto> answers;
    private List<MatchAnswerVariantDto> matchAnswers;
    private List<SortAnswerVariantDto> sortAnswers;
}
