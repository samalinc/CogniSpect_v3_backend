package com.bsuir.cognispect.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "test_template_topic")
public class TestTemplateTopic {
    @ManyToOne
    @JoinColumn(name = "test_template_id")
    private TestTemplate testTemplate;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
}
