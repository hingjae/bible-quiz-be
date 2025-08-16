package com.bible_quiz_backend.topic.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TopicResponse {
    private final Long id;

    private final String title;

    private final String bookTitle;

    @Builder
    public TopicResponse(Long id, String title, String bookTitle) {
        this.id = id;
        this.title = title;
        this.bookTitle = bookTitle;
    }
}
