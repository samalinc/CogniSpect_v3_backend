package com.bsuir.cognispect.entity;

import com.bsuir.cognispect.entity.enums.TestVariantStatusEnum;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class TestVariant {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "TEST_VARIANT_STATUS")
    @Type(type = "pgsql_enum")
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
