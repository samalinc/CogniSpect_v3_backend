package com.bsuir.cognispect.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectModel {
    private UUID id;

    @NotBlank
    private String name;
}
