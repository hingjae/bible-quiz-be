package com.bible_quiz_backend.topic.controller.dto;

import com.bible_quiz_backend.topic.domain.Testament;
import lombok.Builder;

public record TopicResponse(Long id, Testament testament, String question, String bookTitle) {
    @Builder
    public TopicResponse {
    }
}
