package com.bible_quiz_backend.topic.service;

import com.bible_quiz_backend.quizgenerate.dto.SearchRandomTopicParam;
import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.domain.Testament;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.mapper.TopicMapper;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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

    public List<Topic> findDailyRandomTopics(int limit) {
        List<Topic> randomTopics = new ArrayList<>();

        randomTopics.addAll(topicRepository.findRandomTopicsByTestament(Testament.OLD.name(), limit));
        randomTopics.addAll(topicRepository.findRandomTopicsByTestament(Testament.NEW.name(), limit));

        randomTopics.forEach(topic -> log.info(topic.getBookTitle()));

        return randomTopics;
    }
}
