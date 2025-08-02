package com.bible_quiz_backend.quizgenerate.dto;

public record QuizGenerateRequest(
        String bookTitle,
        String question
) {

}
