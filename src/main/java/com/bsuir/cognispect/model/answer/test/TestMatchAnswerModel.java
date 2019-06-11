package com.bsuir.cognispect.model.answer.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestMatchAnswerModel {
    private UUID id;
    private String value;
}
