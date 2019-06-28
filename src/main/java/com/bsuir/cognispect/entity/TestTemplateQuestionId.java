package com.bsuir.cognispect.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestTemplateQuestionId implements Serializable {
    @Column(name = "test_template_id")
    private UUID testTemplateId;

    @Column(name = "question_id")
    private UUID questionId;
}
