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
@Table(name = "sort_answer_variant")
public class SortAnswerVariant extends AnswerVariant {
    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "right_position")
    private int rightPosition;

    @Column(name = "list_position")
    private int listPosition;

    @Column(name = "student_position")
    private int studentPosition;
}
