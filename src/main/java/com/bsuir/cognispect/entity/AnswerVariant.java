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
@Table(name = "answer_variant")
public class AnswerVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "is_correct")
    boolean isCorrect;

    private int position;

    @Column(name = "is_student_chose")
    private boolean isStudentChose;

    @ManyToOne
    @JoinColumn(name = "question_variant_id", nullable = false)
    private QuestionVariant questionVariant;
}
