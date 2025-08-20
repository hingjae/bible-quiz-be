package com.bible_quiz_backend.quiz.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record QuizSearch(@NotBlank Long topicId, Integer size) {
    public QuizSearch(Long topicId, Integer size) {
        this.topicId = topicId;
        this.size = size != null ? size : 5;
    }
}
