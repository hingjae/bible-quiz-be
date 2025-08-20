package com.bible_quiz_backend.quizgenerate.listener;

import com.bible_quiz_backend.common.utils.JsonUtils;
import com.bible_quiz_backend.quiz.service.QuizService;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateMessage;
import com.bible_quiz_backend.topic.service.DailyTopicService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class QuizGenerateResultListener {

    private final QuizService quizService;

    private final DailyTopicService dailyTopicService;

    @Transactional
    @SqsListener("${aws.queue-name}")
    public void receiveMessage(String message) {
        log.info("message received: {}", message);

        QuizGenerateMessage quizGenerateMessage = JsonUtils.fromJson(message, QuizGenerateMessage.class);

        quizService.saveAllFromMessage(quizGenerateMessage);

        dailyTopicService.save(quizGenerateMessage.topicId());
    }
}
