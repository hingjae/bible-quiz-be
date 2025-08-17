package com.bible_quiz_backend.topic.service;

import com.bible_quiz_backend.IntegrationTest;
import com.bible_quiz_backend.topic.controller.dto.TopicResponse;
import com.bible_quiz_backend.topic.controller.dto.TopicResponseList;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Disabled
class TopicServiceTest extends IntegrationTest {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @BeforeEach
    void setUp() {
        Topic genesis = Topic.builder()
                .bookTitle("Genesis")
                .build();

        Topic john = Topic.builder()
                .bookTitle("John")
                .build();

        Topic luke = Topic.builder()
                .bookTitle("Luke")
                .build();

        topicRepository.saveAll(List.of(genesis, john, luke));
    }

    @AfterEach
    void tearDown() {
        topicRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("모든 Topic을 조회한다.")
    void findAll() {
        TopicResponseList topicsResponseList = topicService.findAll();

        Assertions.assertThat(topicsResponseList.topics())
                .hasSize(3)
                .extracting(TopicResponse::bookTitle)
                .containsExactly("Genesis", "John", "Luke");
    }
}