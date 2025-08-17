package com.bible_quiz_backend.topic.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public record TopicResponse(Long id, String title, String bookTitle) {
    @Builder
    public TopicResponse {
    }
}
