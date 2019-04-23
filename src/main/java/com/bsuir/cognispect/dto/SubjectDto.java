package com.bsuir.cognispect.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDto {

    private UUID id;

    @NotBlank
    private String name;
}
