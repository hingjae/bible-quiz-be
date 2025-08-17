package com.bible_quiz_backend.quiz.service;

import com.bible_quiz_backend.IntegrationTest;
import com.bible_quiz_backend.quiz.controller.dto.QuizResponse;
import com.bible_quiz_backend.quiz.controller.dto.QuizResponseList;
import com.bible_quiz_backend.quiz.controller.dto.QuizSearch;
import com.bible_quiz_backend.quiz.domain.Quiz;
import com.bible_quiz_backend.quiz.repository.QuizRepository;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class QuizServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private QuizService quizService;

    @BeforeEach
    void setUp() {
        Topic topic = Topic.builder()
                .bookTitle("Genesis")
                .build();

        Topic savedTopic = topicRepository.save(topic);

        // 샘플 데이터 20개 삽입
        List<Quiz> quizzes = IntStream.rangeClosed(1, 20).mapToObj(i -> Quiz.builder()
                        .topic(savedTopic)
                        .question("question " + i)
                        .options("[\"보기1\", \"보기2\", \"보기3\", \"보기4\"]")
                        .correctAnswer("보기")
                        .reference("창1:" + i)
                        .build())
                .toList();

        quizRepository.saveAll(quizzes);
    }

    @AfterEach
    void clear() {
        quizRepository.deleteAllInBatch();
        topicRepository.deleteAllInBatch();
    }

    @DisplayName("요청한 개수만큼 퀴즈를 조회한다.")
    @Test
    void findByParam_요청한_개수만큼_조회된다() {
        Topic topic = topicRepository.findByQuestion("Genesis").orElseThrow(EntityNotFoundException::new);

        // given
        QuizSearch search = new QuizSearch(topic.getId(), 5);

        // when
        QuizResponseList result = quizService.findByParam(search);

        // then
        assertThat(result.getQuizzes()).hasSize(5)
                .extracting(QuizResponse::getQuestion)
                .containsExactly("question 20", "question 19", "question 18", "question 17", "question 16");
    }

    @DisplayName("요청한 개수가 null 이면 퀴즈 10개를 기본값으로 조회한다.")
    @Test
    void findByParam_개수가_null() {
        Topic topic = topicRepository.findByQuestion("Genesis").orElseThrow(EntityNotFoundException::new);
        // given
        QuizSearch search = new QuizSearch(topic.getId(), null);

        // when
        QuizResponseList result = quizService.findByParam(search);

        // then
        assertThat(result.getQuizzes()).hasSize(10)
                .extracting(QuizResponse::getQuestion)
                .containsExactly("question 20", "question 19", "question 18", "question 17", "question 16", "question 15", "question 14", "question 13", "question 12", "question 11");
    }
}