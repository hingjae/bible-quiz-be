package com.bible_quiz_backend.quizgenerate.testcontroller;

import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateRequest;
import com.bible_quiz_backend.quizgenerate.service.QuizGenerateProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz-generate")
public class TestQuizGenerateController {

    private final QuizGenerateProducer quizGenerateProducer;

    @PostMapping
    public String generateQuiz(@RequestBody QuizGenerateRequest request) {
        quizGenerateProducer.send(request);
        return "success";
    }
}
