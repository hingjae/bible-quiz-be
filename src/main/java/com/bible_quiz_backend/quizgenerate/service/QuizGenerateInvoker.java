package com.bible_quiz_backend.quizgenerate.service;

import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuizGenerateInvoker {

    private final WebClient webClient;
    @Value("${bible-quiz.quiz-generator-endpoint}")
    private String quizGeneratorEndpoint;

    @Async("quizGenerateRequestExecutor")
    public void invoke(QuizGenerateRequest request) {
        webClient.post()
                .uri(quizGeneratorEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity() // 응답 바디 무시
                .doOnError(error -> log.error("Error occurred ", error))
                .subscribe();
    }
}
