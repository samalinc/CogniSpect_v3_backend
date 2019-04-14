package com.bsuir.cognispect.entity;

import com.bsuir.cognispect.entity.enums.QuestionEnum;
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
@Table(name = "question_types")
public class QuestionType {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionEnum questionType;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Question> questions;
}
