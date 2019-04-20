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
@Table(name = "match_answer")
public class MatchAnswer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String key;

    @Column(columnDefinition = "TEXT")
    private String value;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
