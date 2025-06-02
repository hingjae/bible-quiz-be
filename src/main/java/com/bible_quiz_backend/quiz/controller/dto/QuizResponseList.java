package com.bible_quiz_backend.quiz.controller.dto;

import com.bible_quiz_backend.quiz.domain.Quiz;
import lombok.Getter;

import java.util.List;

@Getter
public class QuizResponseList {
    private final List<QuizResponse> quizzes;

    public static QuizResponseList from(List<Quiz> quizzes) {
        List<QuizResponse> quizResponseList = quizzes.stream()
                .map(QuizResponse::new)
                .toList();

        return new QuizResponseList(quizResponseList);
    }

    public QuizResponseList(List<QuizResponse> quizResponses) {
        this.quizzes = quizResponses;
    }
}
