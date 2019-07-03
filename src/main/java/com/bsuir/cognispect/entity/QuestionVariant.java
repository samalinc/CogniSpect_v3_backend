package com.bsuir.cognispect.entity;

import com.bsuir.cognispect.entity.enums.QuestionTypeEnum;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "question_variant")
public class QuestionVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private int cost;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "QUESTION_TYPE")
    @Type(type = "pgsql_enum")
    private QuestionTypeEnum type;

    @Column(name = "is_answered")
    private boolean isAnswered;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "test_variant_id")
    private TestVariant testVariant;

    @OneToMany(mappedBy = "questionVariant", cascade = CascadeType.ALL)
    private List<AnswerVariant> answers;
}
