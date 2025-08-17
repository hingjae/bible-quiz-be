package com.bible_quiz_backend.quiz.controller.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class QuizResponseList {
    private final List<QuizResponse> quizzes;

    public QuizResponseList(List<QuizResponse> quizResponses) {
        this.quizzes = quizResponses;
    }
}
