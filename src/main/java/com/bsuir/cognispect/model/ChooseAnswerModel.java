package com.bsuir.cognispect.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChooseAnswerModel {
    private UUID id;

    private String text;

    boolean isCorrect;
}
