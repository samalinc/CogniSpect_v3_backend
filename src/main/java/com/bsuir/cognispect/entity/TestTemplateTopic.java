package com.bsuir.cognispect.entity;

import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "test_template_topic")
public class TestTemplateTopic {
    @EmbeddedId
    private TestTemplateTopicId id;

    @ManyToOne
    //@MapsId("testTemplateId")
    @JoinColumn(name = "test_template_id", nullable = false, insertable = false, updatable = false)
    private TestTemplate testTemplate;

    @ManyToOne
    //@MapsId("topicId")
    @JoinColumn(name = "topic_id", nullable = false, insertable = false, updatable = false)
    private Topic topic;
}
