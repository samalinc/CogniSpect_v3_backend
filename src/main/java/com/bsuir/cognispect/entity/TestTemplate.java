package com.bsuir.cognispect.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "test_template")
public class TestTemplate {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Teacher creator;

    @OneToMany(mappedBy = "testTemplate")
    private List<TestTemplateQuestion> testTemplateQuestions;

    @OneToMany(mappedBy = "testTemplate")
    private List<TestTemplateTopic> testTemplateTopics;
}
