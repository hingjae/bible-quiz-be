//package com.bible_quiz_backend.quizgenerate.service;
//
//import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Profile;
//import org.springframework.http.MediaType;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.scheduler.Scheduler;
//import reactor.core.scheduler.Schedulers;
//import reactor.util.retry.Retry;
//
//import java.time.Duration;
//
//@Profile("local")
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class QuizGenerateInvokerLocal implements QuizGenerateInvoker {
//
//    private final WebClient webClient;
//    @Value("${aws.lambda.endpoint}")
//    private String quizGeneratorEndpoint;
//    private final ThreadPoolTaskExecutor quizGenerateRequestExecutor;
//
//    @Override
//    public void invoke(QuizGenerateRequest request) {
//        Scheduler scheduler = Schedulers.fromExecutor(quizGenerateRequestExecutor);
//
//        webClient.post()
//                .uri(quizGeneratorEndpoint)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(request)
//                .retrieve()
//                .toBodilessEntity() // 응답 바디 무시
//                .timeout(Duration.ofSeconds(100))
//                .retryWhen(Retry.backoff(3, Duration.ofMillis(200))
//                        .filter(this::isTransientError))
//                .doOnSubscribe(s -> log.info("Invoking local lambda, endpoint={}", quizGeneratorEndpoint))
//                .doOnError(error -> log.error("Error occurred ", error))
//                .subscribeOn(scheduler)
//                .subscribe();
//    }
//
//    private boolean isTransientError(Throwable e) {
//        return true;
//    }
//}
