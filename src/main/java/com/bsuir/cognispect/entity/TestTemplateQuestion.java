package com.bsuir.cognispect.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "test_template_question")
public class TestTemplateQuestion {
    @EmbeddedId
    private TestTemplateQuestionId testTemplateQuestionId;

    @ManyToOne
    @MapsId("testTemplateId")
    private TestTemplate testTemplate;

    @ManyToOne
    @MapsId("questionId")
    private Question question;

    @Column(name = "question_cost")
    private int questionCost;
}
