package com.bsuir.cognispect.model.answer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SortAnswerVariantForTestModel {
    private UUID id;

    private String text;

    private int listPosition;
}
