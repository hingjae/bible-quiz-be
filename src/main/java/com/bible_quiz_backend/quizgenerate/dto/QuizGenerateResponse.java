package com.bible_quiz_backend.quizgenerate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record QuizGenerateResponse(
        @JsonProperty("question") String question,
        @JsonProperty("options") List<String> options,
        @JsonProperty("correct_answer") String correctAnswer,
        @JsonProperty("correct_answer_reason") String correctAnswerReason,
        @JsonProperty("reference") String reference
) {
}
