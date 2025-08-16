package com.bible_quiz_backend.quizgenerate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record QuizGenerateMessage(
        @JsonProperty("request_id") String requestId,
        @JsonProperty("topic_id") Long topicId,
        @JsonProperty("quizzes") List<QuizGenerateResponse> quizzes
) {
}
