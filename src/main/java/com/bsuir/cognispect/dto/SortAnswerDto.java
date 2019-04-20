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
public class SortAnswerDto {
    private UUID id;
    private String text;
    private int position;
}
