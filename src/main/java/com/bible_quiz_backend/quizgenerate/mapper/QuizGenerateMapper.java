package com.bible_quiz_backend.quizgenerate.mapper;

import com.bible_quiz_backend.common.utils.JsonUtils;
import com.bible_quiz_backend.quiz.domain.Quiz;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateMessage;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateMessageProduceBody;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateResponse;
import com.bible_quiz_backend.topic.domain.Topic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizGenerateMapper {

    public QuizGenerateMessageProduceBody toQuizGenerateMessageBody(Topic topic) {
        return QuizGenerateMessageProduceBody.of(
                topic.getId(),
                topic.getBookTitle(),
                topic.getTitle()
        );
    }

    public List<Quiz> toQuizzes(Topic topic, QuizGenerateMessage message) {
        List<QuizGenerateResponse> quizzes = message.quizzes();

        return quizzes.stream()
                .map(quiz -> Quiz.builder()
                        .topic(topic)
                        .question(quiz.question())
                        .options(JsonUtils.toJson(quiz.options()))
                        .correctAnswer(quiz.correctAnswer())
                        .correctAnswerReason(quiz.correctAnswerReason())
                        .reference(quiz.reference())
                        .build())
                .toList();
    }

}
