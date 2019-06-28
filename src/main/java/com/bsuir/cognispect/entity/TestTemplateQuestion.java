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
    private TestTemplateQuestionId id;

    @ManyToOne
    //@MapsId("testTemplateId")
    @JoinColumn(name = "test_template_id", nullable = false, insertable = false, updatable = false)
    private TestTemplate testTemplate;

    @ManyToOne
    //@MapsId("questionId")
    @JoinColumn(name = "question_id", nullable = false, insertable = false, updatable = false)
    private Question question;

    @Column(name = "question_cost")
    private int questionCost;
}
