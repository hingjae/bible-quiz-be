package com.bible_quiz_backend.quiz.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class QuizSearch {
    @NotBlank
    private final Long topicId;

    private final Integer size;

    public QuizSearch(Long topicId, Integer size) {
        this.topicId = topicId;
        this.size = size != null ? size : 10;
    }
}
