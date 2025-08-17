package com.bible_quiz_backend.quizgenerate.dto;

public record QuizGenerateProduceMessageBody(
        String resultQueueUrl,
        Long topicId,
        String bookFileName,
        String question
) {
    public static QuizGenerateProduceMessageBody of(String resultQueueUrl, Long topicId, String bookFileName, String question) {
        return new QuizGenerateProduceMessageBody(resultQueueUrl, topicId, bookFileName, question);
    }
}
