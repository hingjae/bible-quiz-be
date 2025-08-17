package com.bible_quiz_backend.quiz.mapper;

import com.bible_quiz_backend.quiz.controller.dto.QuizResponse;
import com.bible_quiz_backend.quiz.controller.dto.QuizResponseList;
import com.bible_quiz_backend.quiz.domain.Quiz;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizMapper {

    public QuizResponseList toQuizResponseList(List<Quiz> quizzes) {
        List<QuizResponse> quizResponseList = quizzes.stream()
                .map(QuizResponse::new)
                .toList();

        return new QuizResponseList(quizResponseList);
    }
}
