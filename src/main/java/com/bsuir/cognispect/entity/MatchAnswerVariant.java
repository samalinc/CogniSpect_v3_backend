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
public class MatchAnswerVariant extends AnswerVariant {
    @Column(columnDefinition = "TEXT")
    private String key;

    @Column(columnDefinition = "TEXT")
    private String value;

    @Column(name = "key_position")
    private int keyPosition;

    @Column(name = "value_position")
    private int valuePosition;

    @Column(columnDefinition = "TEXT")
    private String studentKey;

    @Column(columnDefinition = "TEXT")
    private String studentValue;
}
