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
public class MatchAnswerVariantModel {
    private UUID id;

    private String key;

    private String value;

    private int keyPosition;

    private int valuePosition;

    private String studentKey;

    private String studentValue;
}
