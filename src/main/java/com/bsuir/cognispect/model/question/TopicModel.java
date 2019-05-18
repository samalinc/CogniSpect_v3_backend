package com.bsuir.cognispect.model.question;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopicModel {
    private UUID id;

    @NotBlank
    private String name;

    @Valid
    private SubjectModel subject;
}
