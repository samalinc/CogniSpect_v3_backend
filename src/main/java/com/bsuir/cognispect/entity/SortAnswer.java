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
@Table(name = "sort_answer")
public class SortAnswer extends Answer{
    @Column(columnDefinition = "TEXT")
    private String text;

    private int position;
}
