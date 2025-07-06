package com.bible_quiz_backend.quiz.controller;

import com.bible_quiz_backend.common.dto.ApiResponse;
import com.bible_quiz_backend.quiz.controller.dto.QuizResponseList;
import com.bible_quiz_backend.quiz.controller.dto.QuizSearch;
import com.bible_quiz_backend.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/api/quizzes")
    public ApiResponse<QuizResponseList> getAllQuizzes(@ModelAttribute QuizSearch quizSearch){
        QuizResponseList quizResponseList = quizService.findByParam(quizSearch);
        log.info("Quizzes found with search: {}", quizSearch.getTopicId());
        return ApiResponse.ok(quizResponseList);
    }

}
