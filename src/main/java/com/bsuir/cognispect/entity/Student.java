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
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "first_name", columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "study_group", columnDefinition = "TEXT")
    private String studyGroup;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToMany(mappedBy = "student")
    private List<TestVariant> testVariants;
}
