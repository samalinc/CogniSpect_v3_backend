package com.bsuir.cognispect.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "choose_answer_variant")
public class ChooseAnswerVariant extends AnswerVariant {
    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "is_correct")
    boolean isCorrect;

    private int position;

    @Column(name = "is_student_chose")
    private boolean isStudentChose;
}
