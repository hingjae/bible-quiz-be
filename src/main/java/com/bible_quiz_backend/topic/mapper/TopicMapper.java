package com.bible_quiz_backend.topic.mapper;

import com.bible_quiz_backend.topic.controller.dto.TopicResponse;
import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.domain.DailyTopic;
import com.bible_quiz_backend.topic.domain.Topic;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopicMapper {
    public TopicResponseList toTopicResponseList(List<Topic> topics) {
        List<TopicResponse> topicResponseList = topics.stream()
                .map(topic -> TopicResponse.builder()
                        .id(topic.getId())
                        .testament(topic.getTestament())
                        .question(topic.getQuestion())
                        .bookTitle(topic.getBookTitle())
                        .build())
                .toList();

        return new TopicResponseList(topicResponseList);
    }

    public TopicResponseList toTopicResponseListByDailyTopics(List<DailyTopic> dailyTopics) {
        List<TopicResponse> topicResponseList = dailyTopics.stream()
                .map(dailyTopic -> {
                    Topic topic = dailyTopic.getTopic();

                    return TopicResponse.builder()
                            .id(topic.getId())
                            .testament(topic.getTestament())
                            .question(topic.getQuestion())
                            .bookTitle(topic.getBookTitle())
                            .build();
                })
                .toList();

        return new TopicResponseList(topicResponseList);
    }
}
