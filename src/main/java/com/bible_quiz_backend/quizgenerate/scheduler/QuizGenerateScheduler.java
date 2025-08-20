package com.bible_quiz_backend.quizgenerate.scheduler;

import com.bible_quiz_backend.quizgenerate.service.QuizGenerateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Profile("prod")
@Slf4j
@Component
@RequiredArgsConstructor
public class QuizGenerateScheduler {

    private final QuizGenerateService quizGenerateService;

    private static final String ZONE_ID = "Asia/Seoul";

    @Scheduled(cron = "0 0 0 * * *", zone = ZONE_ID)
    public void generateDailyQuiz() {
        log.info("Starting daily quiz generation job...");

        quizGenerateService.generateQuizzes();
    }
}
