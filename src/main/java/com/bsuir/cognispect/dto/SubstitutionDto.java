package com.bsuir.cognispect.dto;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubstitutionDto {
    private UUID id;

    private String text;

    private AnswerDto rightAnswer;
}
