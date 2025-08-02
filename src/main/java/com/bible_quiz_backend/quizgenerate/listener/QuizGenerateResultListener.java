package com.bible_quiz_backend.quizgenerate.listener;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QuizGenerateResultListener {

    @SqsListener("quiz-result-queue")
    public void receiveMessage(String message) {
        log.info("Received SQS message: {}", message);
    }
}
