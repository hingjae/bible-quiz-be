package com.bible_quiz_backend.topic.domain;

import com.bible_quiz_backend.common.config.jpa.BaseTimeEntity;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Testament testament;

    private String question;

    private String bookFileName;

    private String bookTitle;

    @Builder
    public Topic(Testament testament, String question, String bookFileName, String bookTitle) {
        this.testament = testament;
        this.question = question;
        this.bookFileName = bookFileName;
        this.bookTitle = bookTitle;
    }
}
