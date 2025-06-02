package com.bible_quiz_backend.topic.controller.dto;

import com.bible_quiz_backend.topic.domain.Topic;
import lombok.Getter;

import java.util.List;

@Getter
public class TopicResponseList {
    private final List<TopicResponse> topics;

    public TopicResponseList(List<Topic> topics) {
        this.topics = topics.stream()
                .map(TopicResponse::new)
                .toList();
    }
}
