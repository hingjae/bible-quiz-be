package com.bible_quiz_backend.topic.controller;

import com.bible_quiz_backend.common.dto.ApiResponse;
import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/api/topics")
    public ApiResponse<TopicResponseList> getAllTopics() {
        TopicResponseList topicResponseList = topicService.findAll();
        return ApiResponse.ok(topicResponseList);
    }
}
