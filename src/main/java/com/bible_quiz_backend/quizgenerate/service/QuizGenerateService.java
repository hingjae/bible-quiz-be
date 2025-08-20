package com.bible_quiz_backend.quizgenerate.service;

import com.bible_quiz_backend.quizgenerate.dto.QuizGenerateProduceMessageBody;
import com.bible_quiz_backend.quizgenerate.mapper.QuizGenerateMapper;
import com.bible_quiz_backend.quizgenerate.producer.QuizGenerateProducer;
import com.bible_quiz_backend.topic.domain.Topic;
import com.bible_quiz_backend.topic.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizGenerateService {

    private final TopicService topicService;
    private final QuizGenerateProducer quizGenerateProducer;
    private final QuizGenerateMapper quizGenerateMapper;

    private static final int TOPIC_LIMIT = 3;
    @Value("${aws.quiz-result-queue-url}")
    private String resultQueueUrl;

    public void generateQuizzes() {
        List<Topic> topics = topicService.findDailyRandomTopics(TOPIC_LIMIT);

        for (Topic topic : topics) {
            QuizGenerateProduceMessageBody quizGenerateMessageBody = quizGenerateMapper.toQuizGenerateMessageBody(resultQueueUrl, topic);
            quizGenerateProducer.send(quizGenerateMessageBody);
        }
    }
}
