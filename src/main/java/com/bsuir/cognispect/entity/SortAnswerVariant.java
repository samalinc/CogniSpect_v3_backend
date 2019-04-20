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
public class SortAnswerVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(name = "right_position")
    private int rightPosition;

    @Column(name = "list_position")
    private int listPosition;

    @ManyToOne
    @JoinColumn(name = "question_variant_id", nullable = false)
    private QuestionVariant questionVariant;
}
