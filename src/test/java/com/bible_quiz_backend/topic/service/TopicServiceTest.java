package com.bible_quiz_backend.topic.service;

import com.bible_quiz_backend.quizgenerate.dto.SearchRandomTopicParam;
import com.bible_quiz_backend.topic.domain.Testament;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TopicServiceTest {

    @InjectMocks
    private TopicService topicService;

    @Mock
    private TopicRepository topicRepository;

    @Test
    @DisplayName("랜덤 주제를 조회한다.")
    void findRandomTopicTest() {
        String newTestament = Testament.NEW.name();
        String oldTestament = Testament.OLD.name();
        int limit = 3;

        when(topicRepository.findRandomTopicsByTestament(oldTestament, limit)).thenReturn(randomOldTestamentTopics());
        when(topicRepository.findRandomTopicsByTestament(newTestament, limit)).thenReturn(randomNewTestamentTopics());

        List<Topic> randomTopics = topicService.findDailyRandomTopics(limit);

        verify(topicRepository).findRandomTopicsByTestament(oldTestament, limit);
        verify(topicRepository).findRandomTopicsByTestament(newTestament, limit);

        assertThat(randomTopics).hasSize(6)
                .extracting(Topic::getBookTitle)
                .containsExactly("창세기", "민수기", "욥기", "마가복음", "요한복음", "사도행전");
    }

    private List<Topic> randomOldTestamentTopics() {
        return List.of(
                Topic.builder().bookTitle("창세기").testament(Testament.OLD).build(),
                Topic.builder().bookTitle("민수기").testament(Testament.OLD).build(),
                Topic.builder().bookTitle("욥기").testament(Testament.OLD).build()
        );
    }

    private List<Topic> randomNewTestamentTopics() {
        return List.of(
                Topic.builder().bookTitle("마가복음").testament(Testament.NEW).build(),
                Topic.builder().bookTitle("요한복음").testament(Testament.NEW).build(),
                Topic.builder().bookTitle("사도행전").testament(Testament.NEW).build()
        );
    }
}