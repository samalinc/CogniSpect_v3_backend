package com.bsuir.cognispect.dto;

import com.bsuir.cognispect.entity.QuestionType;
import com.bsuir.cognispect.entity.enums.QuestionEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private QuestionEnum type;

    @NotNull
    private List<AnswerDto> answers;

    @NotBlank
    @Size(max = 40)
    private String topic;

    @NotBlank
    @Size(max = 40)
    private String subject;
}
