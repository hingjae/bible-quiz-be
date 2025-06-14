package com.bible_quiz_backend.quiz.domain;

import com.bible_quiz_backend.topic.domain.Topic;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private String question;

    @Column(nullable = false, columnDefinition = "json")
    private String options;

    private String correctAnswer;

    private String correctAnswerReason;

    private String reference;

    @Column(name = "created_at", columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime createdAt;

    @Builder
    public Quiz(Long id, Topic topic, String question, String options, String correctAnswer, String correctAnswerReason, String reference, LocalDateTime createdAt) {
        this.id = id;
        this.topic = topic;
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.correctAnswerReason = correctAnswerReason;
        this.reference = reference;
        this.createdAt = createdAt;
    }
}
