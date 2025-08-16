package com.bible_quiz_backend.quizgenerate.dto;

public record QuizGenerateMessageProduceBody(
        Long topicId,
        String bookTitle,
        String question
) {
    public static QuizGenerateMessageProduceBody of (Long topicId, String bookTitle, String question) {
        return new QuizGenerateMessageProduceBody(topicId, bookTitle, question);
    }
}
