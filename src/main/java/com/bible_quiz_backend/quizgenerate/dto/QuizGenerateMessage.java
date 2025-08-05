package com.bible_quiz_backend.quizgenerate.dto;

import com.bible_quiz_backend.common.utils.JsonUtils;
import com.bible_quiz_backend.quiz.domain.Quiz;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record QuizGenerateMessage(
        @JsonProperty("request_id") String requestId,
        @JsonProperty("quizzes") List<QuizGenerateResponse> quizzes
) {

    public List<Quiz> toQuizEntities() {
        return quizzes.stream()
                .map(quiz -> Quiz.builder()
                        .question(quiz.question())
                        .options(JsonUtils.toJson(quiz.options()))
                        .correctAnswer(quiz.correctAnswer())
                        .correctAnswerReason(quiz.correctAnswerReason())
                        .reference(quiz.reference())
                        .build())
                .toList();
    }
}
