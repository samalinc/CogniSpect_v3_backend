package com.bsuir.cognispect.dto;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class QuestionDto {
    private UUID id;

    @NotBlank
    @Size(max = 40)
    private String description;

    private QuestionTypeEnum type;

    private List<AnswerDto> answers;

    private List<MatchAnswerDto> matchAnswers;

    private List<SortAnswerDto> sortAnswers;

    private List<SubstitutionDto> substitutions;

    @NotBlank
    @Size(max = 40)
    private String topic;

    @NotBlank
    @Size(max = 40)
    private String subject;
}
