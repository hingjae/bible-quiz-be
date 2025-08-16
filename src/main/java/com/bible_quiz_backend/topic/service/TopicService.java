package com.bible_quiz_backend.topic.service;

import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.mapper.TopicMapper;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    public TopicResponseList findAll() {
        List<Topic> topics = topicRepository.findAll();
        return topicMapper.toTopicResponseList(topics);
    }

    public Topic findById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
