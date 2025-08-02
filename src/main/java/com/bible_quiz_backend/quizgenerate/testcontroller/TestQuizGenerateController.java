package com.bible_quiz_backend.quizgenerate.testcontroller;

import com.bible_quiz_backend.common.dto.ApiResponse;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateRequest;
import com.bible_quiz_backend.quizgenerate.service.QuizGenerateInvoker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz-generate")
public class TestQuizGenerateController {

    private final QuizGenerateInvoker quizGenerateInvoker;

    @PostMapping
    public String generateQuiz(@RequestBody QuizGenerateRequest request) {
        quizGenerateInvoker.invoke(request);
        return "success";
    }
}
