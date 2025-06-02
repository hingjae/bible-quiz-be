package com.bible_quiz_backend.quiz.controller.dto;

import com.bible_quiz_backend.common.utils.JsonUtils;
import com.bible_quiz_backend.quiz.domain.Quiz;
import lombok.Getter;

import java.util.List;

@Getter
public class QuizResponse {
    private final Long id;

    private final String question;

    private final List<String> options;

    private final String correctAnswer;

    private final String correctAnswerReason;

    private final String reference;

    public QuizResponse(Quiz quiz) {
        this.id = quiz.getId();
        this.question = quiz.getQuestion();
        this.options = JsonUtils.parseOptions(quiz.getOptions());
        this.correctAnswer = quiz.getCorrectAnswer();
        this.correctAnswerReason = quiz.getCorrectAnswerReason();
        this.reference = quiz.getReference();
    }
}
