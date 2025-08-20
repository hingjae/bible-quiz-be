package com.bible_quiz_backend.topic.domain;

import com.bible_quiz_backend.common.config.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyTopic extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Topic topic;

    private LocalDate date;

    @Builder
    public DailyTopic(Topic topic, LocalDate date) {
        this.topic = topic;
        this.date = date;
    }
}
