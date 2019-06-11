package com.bsuir.cognispect.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "match_answer")
public class MatchAnswer extends Answer{
    @Column(columnDefinition = "TEXT")
    private String key;

    @Column(columnDefinition = "TEXT")
    private String value;
}
