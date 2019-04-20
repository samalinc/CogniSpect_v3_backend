package com.bsuir.cognispect.entity;

import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
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
@Table(name = "test_variant")
public class TestVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private TestVariantStatusEnum testVariantStatusEnum;

    @ManyToOne
    @JoinColumn(name = "test_session_id")
    private TestSession testSession;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "testVariant")
    private List<QuestionVariant> questionVariants;
}
