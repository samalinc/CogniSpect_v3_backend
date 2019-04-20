package com.bsuir.cognispect.entity;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Builder
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

    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Set<Answer> answers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<MatchAnswer> matchAnswers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<SortAnswer> sortAnswers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Substitution> substitutions;
}
