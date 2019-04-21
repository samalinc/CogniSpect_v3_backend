package com.bsuir.cognispect.dto;

import lombok.*;

import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopicDto {
    private UUID id;
    private String name;
    private SubjectDto subject;
}
