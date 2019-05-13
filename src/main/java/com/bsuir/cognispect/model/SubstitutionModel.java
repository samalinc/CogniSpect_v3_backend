package com.bsuir.cognispect.model;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubstitutionModel {
    private UUID id;

    private String text;

    private ChooseAnswerModel rightAnswer;
}
