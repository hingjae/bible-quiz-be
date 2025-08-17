package com.bible_quiz_backend.topic.domain;

import com.bible_quiz_backend.common.config.jpa.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Topic extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String bookFileName;

    private String bookTitle;

    @Builder
    public Topic(Long id, String question, String bookFileName, String bookTitle) {
        this.id = id;
        this.question = question;
        this.bookFileName = bookFileName;
        this.bookTitle = bookTitle;
    }
}
