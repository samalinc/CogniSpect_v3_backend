package com.bsuir.cognispect.entity;

import com.bsuir.cognispect.entity.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String login;

    @Column(name = "hashed_password", columnDefinition = "TEXT")
    private String hashedPassword;

    @Email
    private String email;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @OneToOne(mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(mappedBy = "account",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Teacher teacher;
}
