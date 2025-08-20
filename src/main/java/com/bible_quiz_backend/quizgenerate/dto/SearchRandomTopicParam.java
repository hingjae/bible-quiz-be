package com.bible_quiz_backend.quizgenerate.dto;

public record SearchRandomTopicParam(
        String testament,
        int limit
) {
}
