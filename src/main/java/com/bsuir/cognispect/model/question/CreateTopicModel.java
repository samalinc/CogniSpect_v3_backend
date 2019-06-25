package com.bsuir.cognispect.model.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTopicModel {
    @NotBlank
    private String name;
    @NotNull
    private UUID subjectId;
}
