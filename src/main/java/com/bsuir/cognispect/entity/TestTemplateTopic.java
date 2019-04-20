package com.bsuir.cognispect.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    // @JoinColumn(name = "test_template_id")
    @MapsId("testTemplateId")
    private TestTemplate testTemplate;

    @ManyToOne
    @MapsId("topicId")
    // @JoinColumn(name = "topic_id")
    private Topic topic;
}
