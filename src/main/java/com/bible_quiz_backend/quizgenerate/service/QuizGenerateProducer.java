package com.bible_quiz_backend.quizgenerate.service;

import com.bible_quiz_backend.common.utils.JsonUtils;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizGenerateProducer {
    private final SqsAsyncClient sqs;

    @Value("${aws.quiz-request-queue-url}")
    private String queueUrl;

    public void send(QuizGenerateRequest request) {
        String messageBody = JsonUtils.toJson(request);
        sqs.sendMessage(builder -> builder.queueUrl(queueUrl).messageBody(messageBody))
                .thenAccept(response -> log.info("SQS accepted message: {}", response.messageId()))
                .exceptionally(ex -> {
                    log.error("SQS send failed", ex);
                    return null;
                });
    }
}
