package com.bible_quiz_backend.quizgenerate.dto;

public record QuizGenerateMessageProduceBody(
        Long topicId,
        String bookFileName,
        String question
) {
    public static QuizGenerateMessageProduceBody of(Long topicId, String bookFileName, String question) {
        return new QuizGenerateMessageProduceBody(topicId, bookFileName, question);
    }
}
