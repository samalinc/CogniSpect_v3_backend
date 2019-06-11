package com.bsuir.cognispect.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "choose_answer")
public class ChooseAnswer extends Answer {
    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "is_correct")
    boolean isCorrect;

    @OneToOne(mappedBy = "rightChooseAnswer")
    private Substitution substitution;
}
