package com.bible_quiz_backend.topic.controller.dto;

import com.bible_quiz_backend.topic.domain.Topic;
import lombok.Getter;

@Getter
public class TopicResponse {
    private final Long id;

    private final String title;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
    }
}
