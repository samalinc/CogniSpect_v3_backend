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
    private TestTemplateTopicId testTemplateTopicId;

    @ManyToOne
    @MapsId("testTemplateId")
    private TestTemplate testTemplate;

    @ManyToOne
    @MapsId("topicId")
    private Topic topic;
}
