package com.bible_quiz_backend.quizgenerate.testcontroller;

import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateMessageProduceBody;
import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateRequest;
import com.bible_quiz_backend.quizgenerate.mapper.QuizGenerateMapper;
import com.bible_quiz_backend.quizgenerate.service.QuizGenerateProducer;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz-generate")
public class TestQuizGenerateController {

    private final TopicService topicService;
    private final QuizGenerateMapper quizGenerateMapper;
    private final QuizGenerateProducer quizGenerateProducer;

    @PostMapping
    public String generateQuiz(@RequestBody QuizGenerateRequest request) {
        Topic topic = topicService.findById(request.topicId());
        QuizGenerateMessageProduceBody quizGenerateMessageProduceBody = quizGenerateMapper.toQuizGenerateMessageBody(topic);
        quizGenerateProducer.send(quizGenerateMessageProduceBody);
        return "success";
    }
}
