package com.bible_quiz_backend.topic.controller.dto;

import lombok.Getter;

import java.util.List;

public record TopicResponseList(List<TopicResponse> topics) {
}
