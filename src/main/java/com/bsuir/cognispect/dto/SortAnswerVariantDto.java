package com.bsuir.cognispect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SortAnswerVariantDto {
    private UUID id;
    private String text;
    private int rightPosition;
    private int listPosition;
    private int studentPosition;
}
