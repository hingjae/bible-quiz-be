package com.bible_quiz_backend.topic.service;

import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.domain.DailyTopic;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.mapper.TopicMapper;
import com.bible_quiz_backend.topic.repository.DailyTopicRepository;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.bible_quiz_backend.common.config.LocalDateZoneId.ZONE_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DailyTopicService {
    private final DailyTopicRepository dailyTopicRepository;
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    @Transactional
    public void save(Long topicId) {
        Topic topic = topicRepository.getReferenceById(topicId);
        LocalDate today = LocalDate.now(ZONE_ID);

        DailyTopic dailyTopic = DailyTopic.builder()
                .topic(topic)
                .date(today)
                .build();

        dailyTopicRepository.save(dailyTopic);

        log.info("Saved dailyTopic {}", dailyTopic);
    }

    @Transactional(readOnly = true)
    public TopicResponseList findByDate(LocalDate date) {
        List<DailyTopic> dailyTopics = dailyTopicRepository.findByDate(date);
        return topicMapper.toTopicResponseListByDailyTopics(dailyTopics);
    }
}
