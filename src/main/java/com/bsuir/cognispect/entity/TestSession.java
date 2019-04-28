package com.bsuir.cognispect.entity;

import com.bsuir.cognispect.entity.enums.TestSessionStatusEnum;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "test_session")
@TypeDefs({
        @TypeDef(
                name = "pgsql_enum",
                typeClass = PostgreSQLEnumType.class
        ),
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
public class TestSession {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "TEST_SESSION_STATUS")
    @Type(type = "pgsql_enum")
    private TestSessionStatusEnum testSessionStatus;

    @Type(type = "string-array")
    @Column(
            name = "routers",
            columnDefinition = "text[]"
    )
    private String[] routers;

    @Column(columnDefinition = "TEXT")
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "testSession")
    private List<TestVariant> testVariants;
}
