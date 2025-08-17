package com.bible_quiz_backend.topic.controller.dto;

import lombok.Builder;

public record TopicResponse(Long id, String question, String bookTitle) {
    @Builder
    public TopicResponse {
    }
}
