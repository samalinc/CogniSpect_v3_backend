package com.bsuir.cognispect.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_type", nullable = false)
    private QuestionType type;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "id_topic", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "id_subject", nullable = false)
    private Subject subject;
}
