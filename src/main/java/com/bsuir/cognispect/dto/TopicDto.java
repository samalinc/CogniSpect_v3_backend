package com.bsuir.cognispect.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopicDto {
    private UUID id;

    @NotBlank
    private String name;

    @Valid
    private SubjectDto subject;
}
