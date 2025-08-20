package com.bible_quiz_backend.topic.controller;

import com.bible_quiz_backend.common.dto.ApiResponse;
import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.service.DailyTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.bible_quiz_backend.common.config.LocalDateZoneId.ZONE_ID;

@RequiredArgsConstructor
@RestController
public class DailyTopicController {

    private final DailyTopicService dailyTopicService;

    @GetMapping("/api/daily-topics")
    public ApiResponse<TopicResponseList> getDailyTopics() {
        TopicResponseList topicResponseList = dailyTopicService.findByDate(LocalDate.now(ZONE_ID));
        return ApiResponse.ok(topicResponseList);
    }
}
