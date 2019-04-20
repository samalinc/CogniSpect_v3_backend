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
@Table(name = "match_answer_variant")
public class MatchAnswerVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String key;

    private String value;

    @Column(name = "key_position")
    private int keyPosition;

    @Column(name = "value_position")
    private int valuePosition;

    @ManyToOne
    @JoinColumn(name = "question_variant_id", nullable = false)
    private Question question;
}
