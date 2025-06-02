package com.bible_quiz_backend.topic.service;

import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicResponseList findAll() {
        List<Topic> topics = topicRepository.findAll();

        return new TopicResponseList(topics);
    }
}
